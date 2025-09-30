package com.movie.ShowTimeX.Controller;


import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.*;
import com.movie.ShowTimeX.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService=personService;
    }


    @PostMapping("/signup-user")
    public ResponseEntity<?> signup(@RequestBody User user){
        Response response = personService.signUp(user.getPersonName(), user.getUsername(), user.getPassword());
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PostMapping("/login-user")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Response response =  personService.login(username, password);

        if(response.isStatus()){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }


    @PostMapping("/signup-admin")
    public ResponseEntity<?> signupAdmin(@RequestBody Admin admin){
        Response response = personService.signUpAdmin(admin.getPersonName(), admin.getUsername(), admin.getPassword());

        if(response.isStatus()){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PostMapping("/login-admin")
    public ResponseEntity<?> loginAdmin(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        Response response = personService.loginAdmin(username, password);

        if(response.isStatus()){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
        }
    }

    @PostMapping("/setPaymentMethodUPI/{userId}")
    public ResponseEntity<?> setUPI(@PathVariable Integer userId,@RequestBody UPIPayment paymentMethod) {

        Response response = personService.setUPI(userId,paymentMethod);

        return response.isStatus() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);

    }

    @PostMapping("/setPaymentMethodCard/{userId}")
    public ResponseEntity<?> setUPI(@PathVariable Integer userId,@RequestBody CardPayment paymentMethod) {

        Response response = personService.setCard(userId,paymentMethod);

        return response.isStatus() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);

    }



}
