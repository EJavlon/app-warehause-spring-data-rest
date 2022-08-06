package com.company.appwarehausespringdatarest.service;

import com.company.appwarehausespringdatarest.entity.User;
import com.company.appwarehausespringdatarest.entity.Warehause;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.payload.UserWarehauseDto;
import com.company.appwarehausespringdatarest.repasitory.UserRepository;
import com.company.appwarehausespringdatarest.repasitory.WarehauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WarehauseRepository warehauseRepository;

    public Result getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new Result("User not found",false);

        return new Result("User found",true,optionalUser.get());
    }

    public Page<User> getUsers(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return userRepository.findAll(pageable);
    }

    public Result addUser(User user) {
        boolean exists = userRepository.existsByPhoneNumber(user.getPhoneNumber());
        if (exists) return new Result("This phone number is registered",false);

        user.setCode(String.valueOf(userRepository.countAllUser()+1));
        user = userRepository.save(user);

        return new Result("User seccessfully added",true,user);
    }

    public Result editUser(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new Result("User not found",false);

        boolean exists = userRepository.existsByPhoneNumber(user.getPhoneNumber());
        if (exists) return new Result("This phone number is registered",false);

        user.setId(optionalUser.get().getId());
        userRepository.save(user);

        return new Result("User seccessfully added",true,user);
    }

    public Result deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new Result("User not found",false);

        userRepository.delete(optionalUser.get());
        return new Result("User seccessfully deleted",true);
    }

    public Result getUserWarehauses(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return new Result("User not found",false);

        return new Result("all warehouses of the user",true,optionalUser.get().getWarehauses());
    }

    public Result addWarehauseToUser(UserWarehauseDto userWarehauseDto) {
        Optional<User> optionalUser = userRepository.findById(userWarehauseDto.getUserId());
        if (!optionalUser.isPresent()) return new Result("User not found",false);

        Optional<Warehause> optionalWarehause = warehauseRepository.findById(userWarehauseDto.getWarehauseId());
        if (!optionalWarehause.isPresent()) return new Result("Warehause not found",false);

        User user = optionalUser.get();
        Set<Warehause> warehauses = user.getWarehauses();
        warehauses.add(optionalWarehause.get());
        user.setWarehauses(warehauses);
        userRepository.save(user);

        return new Result("Warehause seccessfully added",true);
    }

    public Result editUserWarehause(Integer warehauseId, UserWarehauseDto userWarehauseDto) {
        Optional<Warehause> optionalWarehause = warehauseRepository.findById(warehauseId);
        if (!optionalWarehause.isPresent()) return new Result("Warehause not found",false);

        Optional<User> optionalUser = userRepository.findById(userWarehauseDto.getUserId());
        if (!optionalUser.isPresent()) return new Result("User not found",false);

        Optional<Warehause> newWarehause = warehauseRepository.findById(userWarehauseDto.getWarehauseId());
        if (!optionalWarehause.isPresent())
            return new Result(String.format("Repository with Id %s not found",userWarehauseDto.getWarehauseId()),false);

        if (warehauseId.equals(userWarehauseDto.getWarehauseId()))
            return new Result("Warehause secessfully edited",true);

        User user = optionalUser.get();
        Set<Warehause> warehauses = user.getWarehauses();
        warehauses.remove(optionalWarehause.get());
        warehauses.add(newWarehause.get());
        user.setWarehauses(warehauses);
        userRepository.save(user);

        return new Result("Warehause seccessfully edited",true);
    }

    public Result deleteUserWarehause(UserWarehauseDto userWarehauseDto) {
        Optional<User> optionalUser = userRepository.findById(userWarehauseDto.getUserId());
        if (!optionalUser.isPresent()) return new Result("User not found",false);

        Optional<Warehause> optionalWarehause = warehauseRepository.findById(userWarehauseDto.getWarehauseId());
        if (!optionalWarehause.isPresent()) return new Result("Warehause not found",false);

        User user = optionalUser.get();
        Set<Warehause> warehauses = user.getWarehauses();
        warehauses.remove(optionalWarehause.get());
        user.setWarehauses(warehauses);
        userRepository.save(user);

        return new Result("Warehause seccessfullu deleted",true);
    }
}
