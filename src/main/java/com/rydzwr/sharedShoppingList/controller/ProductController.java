package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.service.DbProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
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
    public ResponseEntity<Boolean> setImportant(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.setImportant(productId));
    }

    @PatchMapping(value = "/setBought/{productId}")
    public ResponseEntity<Boolean> setBought(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.setBought(productId));
    }
}
