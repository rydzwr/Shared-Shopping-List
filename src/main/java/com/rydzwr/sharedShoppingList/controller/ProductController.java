package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.service.DbProductService;
import com.rydzwr.sharedShoppingList.service.DbUserService;
import com.rydzwr.sharedShoppingList.service.DeviceAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping (value = "/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);

        return ResponseEntity.ok(service.addProduct(deviceId, productDto));
    }

    @PatchMapping(value = "/toggleImportant/{productId}")
    public ResponseEntity<Void> setImportant(@PathVariable int productId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        service.toggleImportant(productId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/toggleBought/{productId}")
    public ResponseEntity<Void> setBought(@PathVariable int productId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        service.toggleBought(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/remove/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int productId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        service.deleteProductById(productId);
        return ResponseEntity.ok().build();
    }
}
