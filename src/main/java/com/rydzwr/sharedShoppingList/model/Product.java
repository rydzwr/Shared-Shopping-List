package com.rydzwr.sharedShoppingList.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @NotBlank(message = "Product's name can not be empty!")
    private String name;

    private boolean important;

    private boolean bought;

    @ManyToOne
    private User user;

    @Embedded
    Audit audit = new Audit();

    public Product() {}

    public Product(String name, boolean important, boolean bought)
    {
        this.name = name;
        this.important = important;
        this.bought = bought;
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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isImportant()
    {
        return important;
    }

    public void setImportant(boolean important)
    {
        this.important = important;
    }

    public boolean isBought()
    {
        return bought;
    }

    public void setBought(boolean bought)
    {
        this.bought = bought;
    }

    public void updateFrom(Product source)
    {
        name = source.getName();
        important = source.isImportant();
        bought = source.isBought();
    }
}
