package com.rydzwr.sharedShoppingList.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Product's name can not be empty!")
    private String name;

    private String description;

    private boolean important;

    private boolean bought;

    @ManyToOne
    private User user;

    @Embedded
    Audit audit = new Audit();

    public Product() {}

    public Product(int id, String name, String description, boolean important, boolean productDtoImportant) {}

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
        description = source.getDescription();
        important = source.isImportant();
        bought = source.isBought();
    }
}
