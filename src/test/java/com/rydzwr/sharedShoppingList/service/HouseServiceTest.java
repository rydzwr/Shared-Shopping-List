package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.HouseDto;
import com.rydzwr.sharedShoppingList.exceptions.IdNotFoundException;
import com.rydzwr.sharedShoppingList.exceptions.UserAlreadyAssignedToHouseException;
import com.rydzwr.sharedShoppingList.exceptions.UserNotAssignedToHouseException;
import com.rydzwr.sharedShoppingList.mapper.HouseMapper;
import com.rydzwr.sharedShoppingList.model.House;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.HouseRepository;
import com.rydzwr.sharedShoppingList.repository.ProductRepository;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HouseServiceTest
{
    @Test
    @DisplayName("User is already assigned to house")
    void createHouse_user_house_is_not_null()
    {
        //given
        HouseDto houseDto = mock(HouseDto.class);
        HouseMapper houseMapper = new HouseMapper();
        HouseRepository mockHouseRepository = getMockHouseRepository();
        UserValidation mockUserValidation = mockUserValidationReturnsUserWithHouse();
        //system under test
        var toTest = new DbHouseService(mockHouseRepository,null,houseMapper,null,null,mockUserValidation);
        //when
        var exception = catchThrowable(() -> toTest.createHouse(houseDto, "auth"));
        //then
        assertThat(exception).isInstanceOf(UserAlreadyAssignedToHouseException.class).hasMessageContaining("already assigned");
    }

    @Test
    @DisplayName("Join House When User Is Already Assigned To House")
    void join_house_with_user_already_assigned_to_house()
    {
        //given
        HouseRepository mockHouseRepository = getMockHouseRepositoryExistsByPasswordReturns(true);
        UserValidation mockUserValidation = mockUserValidationReturnsUserWithHouse();
        //system under test
        var toTest = new DbHouseService(mockHouseRepository,null,null,null,null,mockUserValidation);
        //when
        var exception = catchThrowable(() -> toTest.join("1234", "auth"));
        //then
        assertThat(exception).isInstanceOf(UserAlreadyAssignedToHouseException.class).hasMessageContaining("already assigned");
    }

    @Test
    @DisplayName("Update when user is not assigned to house")
    void update_when_user_is_not_assigned_to_house()
    {
        //given
        HouseRepository mockHouseRepository = getMockHouseRepository();
        UserValidation mockUserValidation = mockUserValidationReturnsUserWithoutHouse();
        HouseDto houseDto = new HouseDto("newName");
        //system under test
        var toTest = new DbHouseService(mockHouseRepository,null,null,null,null,mockUserValidation);
        //when
        var exception = catchThrowable(() -> toTest.update("auth", houseDto));
        //then
        assertThat(exception).isInstanceOf(UserNotAssignedToHouseException.class).hasMessageContaining("Not Assigned");
    }

    @Test
    @DisplayName("Complete products list when user is not assigned to house")
    void completeProductsList_when_user_has_no_house()
    {
        //given
        HouseRepository mockHouseRepository = getMockHouseRepository();
        UserValidation mockUserValidation = mockUserValidationReturnsUserWithoutHouse();
        //system under test
        var toTest = new DbHouseService(mockHouseRepository,null,null,null,null,mockUserValidation);
        //when
        var exception = catchThrowable(() -> toTest.completeProductsList("auth"));
        //then
        assertThat(exception).isInstanceOf(UserNotAssignedToHouseException.class).hasMessageContaining("Not Assigned");
    }

    @Test
    @DisplayName("Clear house when user is not assigned to any")
    void clearHouse_when_user_has_no_house()
    {
        //given
        HouseRepository mockHouseRepository = getMockHouseRepository();
        UserValidation mockUserValidation = mockUserValidationReturnsUserWithoutHouse();
        //system under test
        var toTest = new DbHouseService(mockHouseRepository,null,null,null,null,mockUserValidation);
        //when
        var exception = catchThrowable(() -> toTest.clearHouse("auth"));
        //then
        assertThat(exception).isInstanceOf(UserNotAssignedToHouseException.class).hasMessageContaining("Not Assigned");
    }

    @Test
    @DisplayName("Remove user from house")
    void removeUser_from_house()
    {
        //given
        HouseRepository mockHouseRepository = getMockHouseRepository();
        UserValidation mockUserValidation = mockUserValidationReturnsUserWithHouse();
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        UserRepository mockUserRepository = mock(UserRepository.class);
        //and
        User user = mockUserValidation.validate("auth");
        //system under test
        var toTest = new DbHouseService(mockHouseRepository,mockUserRepository,null,mockProductRepository,null,mockUserValidation);
        //when
        toTest.removeUser("auth");
        //then
        assertThat(user.getHouse() == null);
    }

    private UserValidation mockUserValidationReturnsUserWithoutHouse()
    {
        var mockUserValidation = mock(UserValidation.class);
        User user = new User("test");
        when(mockUserValidation.validate(anyString())).thenReturn(user);
        return mockUserValidation;
    }

    private UserValidation mockUserValidationReturnsUserWithHouse()
    {
        var mockUserValidation = mock(UserValidation.class);
        House mockHouse = getMockHouseWithId();
        User user = new User("test", mockHouse);
        when(mockUserValidation.validate(anyString())).thenReturn(user);
        return mockUserValidation;
    }

    private House getMockHouseWithId()
    {
        var mockHouse = mock(House.class);
        when(mockHouse.getId()).thenReturn(1);
        return mockHouse;
    }

    private HouseRepository getMockHouseRepositoryExistsByPasswordReturns(boolean b)
    {
        var mockHouseRepository = mock(HouseRepository.class);
        when(mockHouseRepository.existsByPassword(anyString())).thenReturn(b);
        return mockHouseRepository;
    }

    private HouseRepository getMockHouseRepository()
    {
        var mockHouseRepository = mock(HouseRepository.class);
        return mockHouseRepository;
    }
}
