package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper
{
    public Product mapToProduct(final ProductDto productDto)
    {
        int id = productDto.getId();
        String name = productDto.getName();
        String description = productDto.getDescription();
        boolean important = productDto.isImportant();
        boolean bought = productDto.isBought();

        return new Product(id, name, description, important, bought);
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
