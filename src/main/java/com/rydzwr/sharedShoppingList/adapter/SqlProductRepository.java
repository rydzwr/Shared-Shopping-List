package com.rydzwr.sharedShoppingList.adapter;

import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Integer>
{
    // TO DO

    // DELETE FROM products WHERE product_group_id = PARAM Group ID;


}
