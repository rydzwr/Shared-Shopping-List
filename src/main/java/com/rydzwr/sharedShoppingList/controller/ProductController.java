package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.service.DbProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController
{
    private final DbProductService service;

    public ProductController(DbProductService service)
    {
        this.service = service;
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<String> getDetails(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.getDetails(productId));
    }

    @PatchMapping(value = "/updateDetails/{productId}")
    public ResponseEntity<String> updateDetails(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.getDetails(productId));
    }

    @PatchMapping(value = "/setImportant/{productId}")
    public ResponseEntity<String> setImportant(@PathVariable int productId)
    {
        service.setImportant(productId);
        return ResponseEntity.ok("Important Has Been Set");
    }

    @PatchMapping(value = "/setBought/{productId}")
    public ResponseEntity<String> setBought(@PathVariable int productId)
    {
        service.setBought(productId);
        return ResponseEntity.ok("Bought Has Been Set");
    }
}
