package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper
{
    public Product mapToProduct(final ProductDto productDto)
    {
        return new Product(
                productDto.getName(),
                productDto.isImportant(),
                productDto.isBought()
        );
    }

    public ProductDto mapToProductDto(final Product product)
    {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.isImportant(),
                product.isBought()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products)
    {
        return products.stream().map(product -> mapToProductDto(product)).collect(Collectors.toList());
    }
}
