package com.rydzwr.sharedShoppingList.mapper;

import com.rydzwr.sharedShoppingList.dto.ProductGroupDto;
import com.rydzwr.sharedShoppingList.model.ProductGroup;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupMapper
{
    public ProductGroup mapToProductGroup(final ProductGroupDto productGroupDto)
    {
        return new ProductGroup(
                productGroupDto.getId(),
                productGroupDto.getName(),
                productGroupDto.getProducts(),
                productGroupDto.getUser()
        );
    }

    public ProductGroupDto mapToProductGroupDto(final ProductGroup productGroup)
    {
        return new ProductGroupDto(
                productGroup.getId(),
                productGroup.getName(),
                productGroup.getProducts(),
                productGroup.getUser()
        );
    }
}
