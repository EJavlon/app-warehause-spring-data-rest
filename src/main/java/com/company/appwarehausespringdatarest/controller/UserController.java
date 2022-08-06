package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.entity.User;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.payload.UserWarehauseDto;
import com.company.appwarehausespringdatarest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @GetMapping
    public Page<User> getUsers(@RequestParam int page){
        return userService.getUsers(page);
    }

    @PostMapping
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody User user){
        return userService.editUser(id,user);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

    @GetMapping("/warehauses/{userId}")
    public Result getUserWarehause(@PathVariable Integer userId){
        return userService.getUserWarehauses(userId);
    }

    @PostMapping("/warehause")
    public Result addWarehauseToUser(@RequestBody UserWarehauseDto userWarehauseDto){
        return userService.addWarehauseToUser(userWarehauseDto);
    }

    @PutMapping("warehause/{warehauseId}")
    public Result edtiUserWarehause(@PathVariable Integer warehauseId,@RequestBody UserWarehauseDto userWarehauseDto){
        return userService.editUserWarehause(warehauseId,userWarehauseDto);
    }

    @DeleteMapping("/warehause")
    public Result deleteWarehause(@RequestBody UserWarehauseDto userWarehauseDto){
        return userService.deleteUserWarehause(userWarehauseDto);
    }
}
