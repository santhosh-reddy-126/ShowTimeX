package com.movie.ShowTimeX.Service;


import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.*;
import com.movie.ShowTimeX.Repository.PersonRepository;
import com.movie.ShowTimeX.Utilities.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }


    public Response signUp(String name, String username, String password) {

        try {
            User currUser = new User(name, username, PasswordUtil.hashPassword(password));
            personRepository.save(currUser);
            return new Response(true,"Sign up Successful");
        } catch (Exception e) {
            return new Response(false,e.getMessage());
        }

    }

    public Response signUpAdmin(String personName, String username, String password) {
        try {
            Admin currAdmin = new Admin(personName, username, PasswordUtil.hashPassword(password));
            personRepository.save(currAdmin);
            return new Response(true,"Sign up Successful");
        } catch (Exception e) {
            return new Response(false,e.getMessage());
        }
    }



    public Response login(String username, String password) {
        try {
            Optional<Person> personOpt = personRepository.findByUsername(username);
            if (personOpt.isPresent() && personOpt.get() instanceof User) {
                User user = (User) personOpt.get();
                boolean passwordCorrect = PasswordUtil.matchPassword(password, user.getPassword());
                return new Response(passwordCorrect,passwordCorrect ? "Login Successful":"Wrong Password");
            }
            return new Response(false,"User not found!");
        }catch (Exception e){
            return new Response(false,e.getMessage());
        }
    }

    public Response loginAdmin(String username, String password) {
        try {
            Optional<Person> personOpt = personRepository.findByUsername(username);
            if (personOpt.isPresent() && personOpt.get() instanceof Admin) {
                Admin admin = (Admin) personOpt.get();
                boolean passwordCorrect = PasswordUtil.matchPassword(password, admin.getPassword());
                return new Response(passwordCorrect,passwordCorrect ? "Login Successful":"Wrong Password");
            }
            return new Response(false,"Admin not found!");
        }catch (Exception e){
            return new Response(false,e.getMessage());
        }

    }


    public Optional<User> getUserById(Integer userId) {
        return personRepository.findById(userId)
                .filter(p -> p instanceof User)
                .map(p -> (User) p);
    }


    public Response setUPI(Integer userId, UPIPayment paymentMethod) {
        try{
            Optional<User> user = getUserById(userId);
            if(user.isPresent()){
                user.get().setPaymentMethod(paymentMethod);
                personRepository.save(user.get());
                return new Response(true,"UPI Details Updated");
            }else{
                return new Response(false,"Unable to set Payment Details");
            }
        }catch(Exception e){
            return new Response(false,e.getMessage());
        }
    }

    public Response setCard(Integer userId, CardPayment paymentMethod) {
        try{
            Optional<User> user = getUserById(userId);
            if(user.isPresent()){
                user.get().setPaymentMethod(paymentMethod);
                personRepository.save(user.get());
                return new Response(true,"Card Details Updated");
            }else{
                return new Response(false,"Unable to set Payment Details");
            }
        }catch(Exception e){
            return new Response(false,e.getMessage());
        }
    }
}
