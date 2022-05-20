package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.ProductDto;
import com.rydzwr.sharedShoppingList.model.Product;
import com.rydzwr.sharedShoppingList.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper
{
    public Product mapToProduct(final ProductDto productDto)
    {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.isImportant(),
                productDto.isBought()
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

    public List<ProductDto> mapToProductDtoList(final List<Product> products)
    {
        List<ProductDto> productDtos = new ArrayList<>();

        for (int i = 0; i < products.size(); i++)
        {
            productDtos.add(mapToProductDto(products.get(i)));
        }

        return productDtos;
    }
}
