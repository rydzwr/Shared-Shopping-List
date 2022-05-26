package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.exceptions.RequestException;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DbProductService
{
    private final ProductRepository repository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    public DbProductService(ProductRepository repository, ProductMapper productMapper, UserRepository userRepository)
    {
        this.repository = repository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    public ProductDto addProduct(String deviceId, ProductDto productDto)
    {
        Product newProduct = productMapper.mapToProduct(productDto);

        if (newProduct.getName() == null || newProduct.getName() == "") throw new RequestException("Empty Name");

        User user = userRepository.getUserByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("User with given id not found"));

        newProduct.setUser(user);
        newProduct = repository.save(newProduct);
        return productMapper.mapToProductDto(newProduct);
    }

    public void toggleImportant(int productId)
    {
        Product product = repository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
        product.setImportant(!product.isImportant());
        repository.save(product);
        productMapper.mapToProductDto(product);
    }

    public void toggleBought(int productId)
    {
        Product product = repository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
        product.setBought(!product.isBought());
        repository.save(product);
        productMapper.mapToProductDto(product);
    }

    @Transactional
    public void deleteProductById(int productId)
    {
        if (!repository.existsById(productId))
            throw new IllegalArgumentException("Product with given ID doesn't exists!");

        else
        {
            Product product = repository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
            User user = product.getUser();

            repository.deleteProductById(productId);
            userRepository.save(user);
        }
    }
}
