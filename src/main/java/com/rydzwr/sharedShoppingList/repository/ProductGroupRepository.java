package com.rydzwr.sharedShoppingList.repository;

import com.rydzwr.sharedShoppingList.model.ProductGroup;

import java.util.List;
import java.util.Optional;

public interface ProductGroupRepository
{
    List<ProductGroup> findAll();

    Optional<ProductGroup> findById(Integer id);

    ProductGroup save(ProductGroup entity);
}
