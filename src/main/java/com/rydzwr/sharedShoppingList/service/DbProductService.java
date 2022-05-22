package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DbProductService
{
    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public DbProductService(ProductRepository repository, ProductMapper productMapper)
    {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    public ProductDto getDetails(int id)
    {
        Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
        return productMapper.mapToProductDto(product);
    }

    public ProductDto updateDetails(int productId, ProductDto productDto)
    {
        Product product = repository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
        Product source = productMapper.mapToProduct(productDto);
        product.updateFrom(source);
        repository.save(product);
        return productDto;
    }

    public ProductDto setImportant(int productId)
    {
        Product product = repository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
        product.setImportant(!product.isImportant());
        repository.save(product);
        return productMapper.mapToProductDto(product);
    }

    public ProductDto setBought(int productId)
    {
        Product product = repository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product with given id not found"));
        product.setBought(!product.isBought());
        repository.save(product);
        return productMapper.mapToProductDto(product);
    }
}
