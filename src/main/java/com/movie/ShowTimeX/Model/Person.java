package com.movie.ShowTimeX.Model;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer personId;
    private String personName;
    private String username;
    private String password;

    public Person(String personName,String username,String password){
        this.personName = personName;
        this.username = username;
        this.password = password;
    }

    public Person() {
    }

    public Integer getPersonId(){
        return personId;
    }

    public String getPersonName(){
        return personName;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }


    public void setPersonName(String personName){
        this.personName=personName;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }


}
