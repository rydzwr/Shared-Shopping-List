package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbUserService
{
    private final UserRepository repository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final UserMapper userMapper;

    public DbUserService(UserRepository repository, ProductMapper productMapper, ProductRepository productRepository, UserMapper userMapper)
    {
        this.repository = repository;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.userMapper = userMapper;
    }

    public String getName(int id)
    {
        return repository.findById(id).get().getName();
    }

    public List<ProductDto> getAllProducts(int id)
    {
        return productMapper.mapToProductDtoList(productRepository.findAllByUser_Id(id));
    }

    public UserDto createUser(UserDto userDto)
    {
        User user = userMapper.mapToUser(userDto);
        repository.save(user);
        return userDto;
    }

    public List<ProductDto> addProduct(int userId, ProductDto productDto)
    {
        Product newProduct = productMapper.mapToProduct(productDto);
        User user = repository.findById(userId).get();

        newProduct.setUser(user);
        productRepository.save(newProduct);

        user.getProductsList().add(newProduct);
        repository.save(user);

        List<Product> products = user.getProductsList();
        List<ProductDto> productDtos = new ArrayList<>();

        for (int i = 0; i < products.size(); i++)
        {
            productDtos.add(productMapper.mapToProductDto(products.get(i)));
        }

        return productDtos;
    }

    @Transactional
    public List<ProductDto> removeAllProductsWhereBoughtIsTrue(int userId)
    {
        if (!repository.existsById(userId))
            throw new IllegalArgumentException("User with given ID doesn't exists!");
        else
        {
            User user = repository.findById(userId).get();
            List<Product> products = productRepository.findAllByUser_Id(userId);

            for (Product product : products)
            {
                if (product.isBought())
                {
                    productRepository.deleteProductById(product.getId());
                }
            }

            repository.save(user);

            return productMapper.mapToProductDtoList(products);
        }
    }
    @Transactional
    public List<ProductDto> deleteProductById(int productId)
    {
        if (!productRepository.existsById(productId))
            throw new IllegalArgumentException("Product with given ID doesn't exists!");

        else
        {
            Product product = productRepository.findById(productId).get();
            User user = product.getUser();
            List<Product> products = user.getProductsList();

            productRepository.deleteProductById(productId);

            repository.save(user);

            return productMapper.mapToProductDtoList(products);
        }
    }
}
