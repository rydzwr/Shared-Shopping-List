package com.rydzwr.sharedShoppingList.controller;

import com.rydzwr.sharedShoppingList.mapper.ProductMapper;
import com.rydzwr.sharedShoppingList.service.DbProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseController
{
    private final DbProductService productService;
    private final ProductMapper productMapper;

    public HouseController(DbProductService productService, ProductMapper productMapper)
    {
        this.productService = productService;
        this.productMapper = productMapper;
    }


}
