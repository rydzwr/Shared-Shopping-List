package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.service.DbProductService;
import com.rydzwr.sharedShoppingList.service.DbUserService;
import com.rydzwr.sharedShoppingList.service.DeviceAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8080")
public class ProductController
{
    private final DbProductService service;
    private final DbUserService userService;

    public ProductController(DbProductService service, DbUserService userService)
    {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> getDetails(@PathVariable int productId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.getDetails(productId));
    }

    @PostMapping (value = "/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);

        return ResponseEntity.ok(service.addProduct(deviceId, productDto));
    }

    @PatchMapping(value = "/updateDetails/{productId}")
    public ResponseEntity<ProductDto> updateDetails(@PathVariable int productId, @RequestBody ProductDto productDto, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.updateDetails(productId, productDto));
    }

    @PatchMapping(value = "/setImportant/{productId}")
    public ResponseEntity<ProductDto> setImportant(@PathVariable int productId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.setImportant(productId));
    }

    @PatchMapping(value = "/setBought/{productId}")
    public ResponseEntity<ProductDto> setBought(@PathVariable int productId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.setBought(productId));
    }
}
