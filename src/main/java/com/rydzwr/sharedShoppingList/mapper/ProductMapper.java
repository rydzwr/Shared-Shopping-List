package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper
{
    public Product mapToProduct(final ProductDto productDto)
    {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.isImportant()
        );
    }

    public ProductDto mapToProductDto(final Product product)
    {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.isImportant(),
                product.isBought()
        );
    }
}
