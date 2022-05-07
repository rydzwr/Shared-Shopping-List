package com.rydzwr.sharedShoppingList.adapter;

import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.ProductGroup;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Integer>
{
}
