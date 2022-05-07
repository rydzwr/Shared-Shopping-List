package com.rydzwr.sharedShoppingList.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products_groups")
public class ProductGroup
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Product> products;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usersGroup")
    private User user;

    public ProductGroup() {}

    public ProductGroup(int id, String name, List<Product> products, User user)
    {
        this.id = id;
        this.name = name;
        this.products = products;
        this.user = user;
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

    public List<Product> getProducts()
    {
        return products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void updateFrom(ProductGroup source)
    {
        name = user.getName();
        products = source.getProducts();
    }
}
