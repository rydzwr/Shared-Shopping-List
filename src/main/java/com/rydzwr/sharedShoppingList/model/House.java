package com.rydzwr.sharedShoppingList.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "houses")
public class House
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Set House name!")
    private String name;

    private String password;

    @OneToMany(mappedBy = "house")
    private List<User> users;

    @Embedded
    Audit audit = new Audit();

    public House() {}

    public House(int id , String name)
    {
        this.id = id;
        this.name = name;
    }

    public House(int id, String name, String password, List<User> users)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.users = users;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public void updateName(House source)
    {
        name = source.getName();
    }

    public void updateFrom(House source)
    {
        password = source.getPassword();
        users = source.getUsers();
    }
}
