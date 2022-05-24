package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.HouseDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.HouseMapper;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.JsonDoc;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.service.DbHouseService;
import com.rydzwr.sharedShoppingList.service.DbProductService;
import com.rydzwr.sharedShoppingList.service.DbUserService;
import com.rydzwr.sharedShoppingList.service.DeviceAuthorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
@CrossOrigin(origins = "http://localhost:8080")
public class HouseController
{
    private final DbHouseService service;
    private final HouseMapper mapper;
    private final UserMapper userMapper;
    private final DbUserService userService;

    public HouseController(DbHouseService service, HouseMapper mapper, UserMapper userMapper, DbUserService userService)
    {
        this.service = service;
        this.mapper = mapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping(value = "/getName/{houseId}")
    public ResponseEntity<String> getHouseName(@PathVariable int houseId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.getHouseName(houseId));
    }

    @GetMapping(value = "/getUsers/{houseId}")
    public ResponseEntity<List<UserDto>> getAllUsersFromHouse(@PathVariable int houseId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.getUsers(houseId));
    }

    @PostMapping
    public ResponseEntity<HouseDto> createHouse(@RequestBody HouseDto houseDto, @RequestHeader("Authorization") String auth)
    {
       if (!userService.authorizeDevice(auth))
           return ResponseEntity.status(401).build();

        return ResponseEntity.ok(service.createHouse(houseDto, auth));
    }

    @PostMapping(value = "/join/{inviteCode}")
    public ResponseEntity<Void> join(@PathVariable String inviteCode, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);
        service.join(inviteCode, deviceId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/completeProductsList")
    public ResponseEntity<JsonDoc> completeProductsList(@RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        String deviceId = DeviceAuthorization.getInstance().deviceIdFromAuthHeader(auth);
        return ResponseEntity.ok(service.completeProductsList(deviceId));
    }

    @PatchMapping(value = "/clear/{houseId}")
    public ResponseEntity<Void> clearHouse(@PathVariable int houseId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        service.clearHouse(houseId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/removeUser/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable int userId, @RequestHeader("Authorization") String auth)
    {
        if (!userService.authorizeDevice(auth))
            return ResponseEntity.status(401).build();

        service.removeUser(userId);
        return ResponseEntity.ok().build();
    }
}
