package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String getDetails(int id)
    {
        Product product = repository.findById(id).get();

        String result =
                        "name: " + product.getName() +
                        "description: " + product.getDescription() +
                        "important: " + product.isImportant() +
                        "bought: " + product.isBought();

        return result;
    }

    public void updateDetails(int productId, ProductDto productDto)
    {
        Product product = repository.findById(productId).get();
        Product source = productMapper.mapToProduct(productDto);
        product.updateFrom(source);
        repository.save(product);
    }

    public boolean setImportant(int productId)
    {
        Product product = repository.findById(productId).get();
        product.setImportant(!product.isImportant());
        repository.save(product);
        return product.isImportant();
    }

    public boolean setBought(int productId)
    {
        Product product = repository.findById(productId).get();
        product.setBought(!product.isBought());
        repository.save(product);
        return product.isBought();
    }
}
