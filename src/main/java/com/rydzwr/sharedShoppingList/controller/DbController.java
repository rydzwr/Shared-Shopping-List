package com.rydzwr.sharedShoppingList.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class DbController
{
    @GetMapping
    public ResponseEntity<Boolean> checkConnection()
    {
        return ResponseEntity.ok(true);
    }
}
