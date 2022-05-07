package com.rydzwr.sharedShoppingList.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Product's name can not be empty!")
    private String name;

    @OneToOne
    @JoinColumn(name = "group_id")
    private ProductGroup usersGroup;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    public User() {}

    public User(int id, String name, ProductGroup usersGroup, House house)
    {
        this.id = id;
        this.name = name;
        this.usersGroup = usersGroup;
        this.house = house;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ProductGroup getUsersGroup()
    {
        return usersGroup;
    }

    public void setUsersGroup(ProductGroup usersGroup)
    {
        this.usersGroup = usersGroup;
    }

    public House getHouse()
    {
        return house;
    }

    public void setHouse(House house)
    {
        this.house = house;
    }

    public void updateFrom(User source)
    {
        name = source.getName();
    }
}
