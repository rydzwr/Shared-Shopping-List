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

    @PostMapping(value = "/removeWhereBoughtTrue/")
    public ResponseEntity<Void> removeAllProductsWhereBoughtTrue(@RequestHeader("Authorization") String auth)
    {
        if (!service.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);
        service.removeAllProductsWhereBoughtIsTrue(deviceId);
        return ResponseEntity.ok().build();
    }
}
