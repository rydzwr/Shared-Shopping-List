package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.model.JsonDoc;
import com.rydzwr.sharedShoppingList.service.DbUserService;
import com.rydzwr.sharedShoppingList.service.DeviceAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController
{
    private final DbUserService service;

    public UserController(DbUserService service)
    {
        this.service = service;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<UserDto> getLogin(@RequestHeader("Authorization") String auth)
    {
        if (!service.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);
        return ResponseEntity.ok(service.getByDeviceId(deviceId));
    }

    @GetMapping(value = "/name/{userId}")
    public ResponseEntity<String> getName(@PathVariable int userId, @RequestHeader("Authorization") String auth)
    {
        if (!service.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.getName(userId));
    }

    @GetMapping(value = "/products/{userId}")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable int userId, @RequestHeader("Authorization") String auth)
    {
        if (!service.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.getAllProducts(userId));
    }

    @GetMapping(value = "/getInviteCode")
    public ResponseEntity<JsonDoc> getInviteCode(@RequestHeader("Authorization") String auth)
    {
        if (!service.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);
        return ResponseEntity.ok(service.getInviteCode(deviceId));
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto, @RequestHeader("Authorization") String auth)
    {
        if (service.authorizeDevice(auth))
            return ResponseEntity.status(409).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);
        return ResponseEntity.ok(service.createUser(deviceId, userDto));
    }



    @PostMapping(value = "/removeAll/{userId}")
    public ResponseEntity<List<ProductDto>> removeAllProductsWhereBoughtTrue(@PathVariable int userId, @RequestHeader("Authorization") String auth)
    {
        if (!service.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        List<ProductDto> products = service.removeAllProductsWhereBoughtIsTrue(userId);

        return ResponseEntity.ok(products);
    }

    @DeleteMapping(value = "/removeById/{productId}")
    public ResponseEntity<List<ProductDto>> deleteProductById(@PathVariable int productId, @RequestHeader("Authorization") String auth)
    {
        if (!service.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.deleteProductById(productId));
    }
}
