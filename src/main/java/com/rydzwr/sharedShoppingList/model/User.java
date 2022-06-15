package com.rydzwr.sharedShoppingList.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "User's name can not be empty!")
    private String name;

    @NotNull
    @NotBlank(message = "User's device ID can not be empty!")
    @Column(unique = true)
    private String deviceId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @OrderBy("bought")
    private List<Product> productsList;

    @Embedded
    Audit audit = new Audit();

    public User() {}

    public User(String name, String deviceId)
    {
        this.name = name;
        this.deviceId = deviceId;
    }

    public User(int id, String name, String deviceId, House house, List<Product> productsList)
    {
        this.id = id;
        this.name = name;
        this.deviceId = deviceId;
        this.house = house;
        this.productsList = productsList;
    }

    public User(String name)
    {
        this.name = name;
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

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
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
