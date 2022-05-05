package com.rydzwr.sharedShoppingList.adapter;

import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Integer>
{
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from products where id=:id")
    boolean existsById(@Param("id") Integer id);
}
