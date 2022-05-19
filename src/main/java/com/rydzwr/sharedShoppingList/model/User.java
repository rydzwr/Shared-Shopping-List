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

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Product> productsList;

    @Embedded
    Audit audit = new Audit();

    public User() {}

    public User(int id, String name, List<Product> productsList)
    {
        this.id = id;
        this.name = name;
        this.productsList = productsList;
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

    public List<Product> getProductsList()
    {
        return productsList;
    }

    public void setProductsList(List<Product> productsList)
    {
        this.productsList = productsList;
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
