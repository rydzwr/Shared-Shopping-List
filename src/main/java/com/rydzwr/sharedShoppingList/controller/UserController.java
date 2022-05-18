package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.service.DbUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    private final DbUserService service;

    public UserController(DbUserService service)
    {
        this.service = service;
    }

    @GetMapping(value = "/name/{userId}")
    public ResponseEntity<String> getName(@PathVariable int userId)
    {
        return ResponseEntity.ok(service.getName(userId));
    }

    @GetMapping(value = "/products/{userId}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable int userId)
    {
        return ResponseEntity.ok(service.getAllProducts(userId));
    }

    @PostMapping(value = "/addProduct/{userId}")
    public ResponseEntity<Void> addProduct(@PathVariable int userId, @RequestBody ProductDto productDto)
    {
        service.addProduct(userId, productDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/removeAll/{userId}")
    public ResponseEntity<List<Product>> removeAllProductsWhereBoughtTrue(@PathVariable int userId)
    {
        return ResponseEntity.ok(service.removeAllProductsWhereBoughtIsTrue(userId));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<List<Product>> deleteProductById(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.deleteProductById(productId));
    }
}
