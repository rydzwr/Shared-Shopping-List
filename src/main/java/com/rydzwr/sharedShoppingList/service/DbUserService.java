package com.rydzwr.sharedShoppingList.service;

import com.rydzwr.sharedShoppingList.dto.UserDto;
import com.rydzwr.sharedShoppingList.mapper.UserMapper;
import com.rydzwr.sharedShoppingList.model.User;
import com.rydzwr.sharedShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbUserService
{
    private final UserRepository repository;
    private final UserMapper userMapper;

    public DbUserService(UserRepository repository, UserMapper userMapper)
    {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers()
    {
        return repository.findAll();
    }

    public Optional<User> findByUserName(String username)
    {
        return repository.findByUserName(username);
    }

    public User addUser(final User user)
    {
        return repository.save(user);
    }

    public Optional<User> getUserWithId(final int id)
    {
        if (repository.existsById(id))
            return repository.findById(id);

        else throw new IllegalArgumentException("User with given id not found!");
    }

    public void createNewUser(final UserDto userDto)
    {
        User user = userMapper.mapToUser(userDto);
        repository.save(user);
    }

}
