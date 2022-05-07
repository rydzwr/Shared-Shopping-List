package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.ProductGroup;
import com.rydzwr.sharedShoppingList.repository.ProductGroupRepository;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbProductGroupService
{
    private final ProductGroupRepository repository;
    private final ProductRepository productRepository;

    public DbProductGroupService(ProductGroupRepository repository, ProductRepository productRepository)
    {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public List<ProductGroup> getAllGroups()
    {
        return repository.findAll();
    }

    public ProductGroup getGroup(int id)
    {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public ProductGroup saveGroup(final ProductGroup productGroup)
    {
        return repository.save(productGroup);
    }

    public List<Product> getAllProductsFromGroup(ProductGroup productGroup)
    {
        return productRepository.findAllByGroup(productGroup);
    }

    public void deleteAllProductsFromGroup(ProductGroup productGroup)
    {
        productRepository.deleteAllByGroup(productGroup);
    }
}
