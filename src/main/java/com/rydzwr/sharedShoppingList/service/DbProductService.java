package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.ProductGroup;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;

import java.util.List;

public class DbProductService
{
    private final ProductRepository repository;
    private final ProductMapper productMapper;
    private final DbProductGroupService productGroupService;

    public DbProductService(ProductRepository repository, ProductMapper productMapper, DbProductGroupService productGroupService)
    {
        this.repository = repository;
        this.productMapper = productMapper;
        this.productGroupService = productGroupService;
    }

    public List<Product> getAllProducts()
    {
        return repository.findAll();
    }

    public Product getProductById(final int id)
    {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Product saveProduct(final Product product)
    {
        return repository.save(product);
    }

    public void deleteProduct(final int id)
    {
        repository.deleteProductById(id);
    }

    public List<Product> findAllByGroup(ProductGroup productGroup)
    {
        return repository.findAllByGroup(productGroup);
    }

    public void addProductToGroup(ProductDto productDto, int groupId)
    {
        ProductGroup productGroup = productGroupService.getGroup(groupId);
        Product product = productMapper.mapToProduct(productDto);
        product.setGroup(productGroup);
        saveProduct(product);
    }
}
