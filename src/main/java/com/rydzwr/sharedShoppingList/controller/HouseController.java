package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.dto.HouseDto;
import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.HouseMapper;
import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.service.DbHouseService;
import com.rydzwr.sharedShoppingList.service.DbProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController
{
    private final DbHouseService service;
    private final HouseMapper mapper;
    private final UserMapper userMapper;

    public HouseController(DbHouseService service, HouseMapper mapper, UserMapper userMapper)
    {
        this.service = service;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/getName/{houseId}")
    public ResponseEntity<String> getHouseName(@PathVariable int houseId)
    {
        return ResponseEntity.ok(service.getHouseName(houseId));
    }

    @GetMapping(value = "/getUsers/{houseId}")
    public ResponseEntity<List<User>> getAllUsersFromHouse(@PathVariable int houseId)
    {
        return ResponseEntity.ok(service.getUsers(houseId));
    }

    @GetMapping(value = "/getPass/{houseId}")
    public ResponseEntity<String> getPassword(@PathVariable int houseId)
    {
        return ResponseEntity.ok(service.getPassword(houseId));
    }

    @PostMapping
    public ResponseEntity<HouseDto> createHouse(@RequestBody HouseDto houseDto)
    {
        return ResponseEntity.ok(service.createHouse(houseDto));
    }

    @PostMapping(value = "/{houseId}")
    public ResponseEntity<UserDto> addUser(@PathVariable int houseId, UserDto userDto)
    {
        User user = userMapper.mapToUser(userDto);
        service.addUser(houseId, user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{houseId}")
    public ResponseEntity<Void> clearHouse(@PathVariable int houseId)
    {
        service.clearHouse(houseId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable int userId)
    {
        service.removeUser(userId);
        return ResponseEntity.ok().build();
    }
}
