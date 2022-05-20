package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
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
    public ResponseEntity<ProductDto> getDetails(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.getDetails(productId));
    }

    @PatchMapping(value = "/updateDetails/{productId}")
    public ResponseEntity<ProductDto> updateDetails(@PathVariable int productId, @RequestBody ProductDto productDto)
    {
        return ResponseEntity.ok(service.updateDetails(productId, productDto));
    }

    @PatchMapping(value = "/setImportant/{productId}")
    public ResponseEntity<ProductDto> setImportant(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.setImportant(productId));
    }

    @PatchMapping(value = "/setBought/{productId}")
    public ResponseEntity<ProductDto> setBought(@PathVariable int productId)
    {
        return ResponseEntity.ok(service.setBought(productId));
    }
}
