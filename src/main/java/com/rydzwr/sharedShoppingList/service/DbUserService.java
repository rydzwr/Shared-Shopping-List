package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbUserService
{
    private final UserRepository repository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public DbUserService(UserRepository repository, ProductMapper productMapper, ProductRepository productRepository)
    {
        this.repository = repository;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public String getName(int id)
    {
        return repository.findById(id).get().getName();
    }

    public List<Product> getAllProducts(int id)
    {
        return repository.findById(id).get().getProductsList();
    }

    public void addProduct(int userId, ProductDto productDto)
    {
        Product newProduct = productMapper.mapToProduct(productDto);
        productRepository.save(newProduct);

        User user = repository.findById(userId).get();
        user.getProductsList().add(newProduct);
        repository.save(user);
    }

    public void removeAllProductsWhereBoughtIsTrue(int userId)
    {
        User user = repository.findById(userId).get();
        List<Product> products = user.getProductsList();

        for (Product product : products)
        {
            if (product.isBought() == true)
                products.remove(product);
        }

        repository.save(user);
    }

    public void deleteProductById(int productId)
    {
        Product product = productRepository.findById(productId).get();
        User user = product.getUser();
        List<Product> products = user.getProductsList();

        for (Product productFromList : products)
        {
            if (productFromList.getId() == productId)
                products.remove(productFromList);
        }

        repository.save(user);
    }
}
