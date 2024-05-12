package com.note.taker.controller;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
public class NoteTakerController {
    @GetMapping("/test/{id}")
    void testGet(@PathVariable Long id) {
        System.out.println("id " +id);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PATCH})
    public Map<String,String> createUser(@RequestBody Map<String,String> input) {
        System.out.println("input " +input);
        Map<String, String> resp = new HashMap<>();
        try {
            System.out.println("User Name " + input.get("username"));
            System.out.println("Password " + input.get("password"));

            String dbURL = "jdbc:postgresql://172.17.0.2:5432/app_note";
            Properties parameters = new Properties();
            parameters.put("user", "app_user");
            parameters.put("password", "notetaker@123");
            Connection conn = DriverManager.getConnection(dbURL, parameters);
            Statement statement = conn.createStatement();
            int response = statement.executeUpdate("insert into app_note_taker.app_user(user_name,user_password,is_active) " +
                    "values ('" + input.get("username") + "', '" + input.get("password") + "', TRUE); ");
            if(response == 1) {
                resp.put("Message", "User created successfully.");
            } else {
                resp.put("Message", "Failed to create user.");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/validate")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PATCH})
    public Map<String,String> validateLogin(@RequestBody Map<String,String> input) {
        System.out.println("input " +input);
        Map<String, String> resp = new HashMap<>();
        try {
            System.out.println("User Name " + input.get("username"));
            System.out.println("Password " + input.get("password"));
            String name = null;
            String password = null;

                    String dbURL = "jdbc:postgresql://172.17.0.2:5432/app_note";
            Properties parameters = new Properties();
            parameters.put("user", "app_user");
            parameters.put("password", "notetaker@123");
            Connection conn = DriverManager.getConnection(dbURL, parameters);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from app_note_taker.app_user where user_name = '" +input.get("username")+ "' and user_password = '"+input.get("password")+ "'");
            while (rs.next()) {
                name = rs.getString("user_name");
                password = rs.getString("user_password");
                System.out.println(name + ", " + name + ", " + password);
            }
            if(name != null && password != null && name.equals(input.get("username")) && password.equals(input.get("password"))) {
                resp.put("Message", "Logged In Successfully.");
            } else {
                resp.put("Message", "Failed to login.");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
