package com.rydzwr.sharedShoppingList.adapter;

import com.rydzwr.sharedShoppingList.model.ProductGroup;
import com.rydzwr.sharedShoppingList.model.ProductGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SqlProductGroupRepository extends ProductGroupRepository, JpaRepository<ProductGroup, Integer>
{
    @Override
    @Query("select distinct g from ProductGroup g join fetch g.products")
    List<ProductGroup> findAll();
}
