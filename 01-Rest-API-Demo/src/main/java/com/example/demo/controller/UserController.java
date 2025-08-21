package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
@GetMapping
    public String getAllUsers()
    {
      return "list of users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id)
    {
        return "GetUserById method-"+id;
    }

    @GetMapping("/search")
    public  String searchUser(@RequestParam(name="key") String key)
    {
        return "Search method->"+key;
    }

    @PostMapping
    public String createUser(@RequestBody String user)
    {
        return "User create method-"+user;
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id,@RequestBody String editUser)
    {
        return "update user id-"+id+"updated with request body-"+editUser;
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id)
    {

        return "Delete user with id-"+id;
    }

}
