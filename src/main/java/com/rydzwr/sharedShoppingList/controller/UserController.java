package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.service.DbUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
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

    @PostMapping(value = "/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        return ResponseEntity.ok(service.createUser(userDto));
    }

    @PostMapping (value = "/addProduct/{userId}")
    public ResponseEntity<List<ProductDto>> addProduct(@PathVariable int userId, @RequestBody ProductDto productDto)
    {
       return ResponseEntity.ok(service.addProduct(userId, productDto));
    }

    @PostMapping(value = "/removeAll/{userId}")
    public ResponseEntity<List<Product>> removeAllProductsWhereBoughtTrue(@PathVariable int userId)
    {
        return ResponseEntity.ok(service.removeAllProductsWhereBoughtIsTrue(userId));
    }

    // Not working when user is null id DB!!

    @DeleteMapping(value = "/removeById/{productId}")
    public ResponseEntity<List<Product>> deleteProductById(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.deleteProductById(productId));
    }
}
