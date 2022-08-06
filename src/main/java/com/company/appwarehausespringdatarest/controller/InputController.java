package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.entity.Input;
import com.company.appwarehausespringdatarest.payload.InputDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    private InputService inputService;

    @GetMapping("/{id}")
    public Result getInputById(@PathVariable Integer id){
        return inputService.getInputBydId(id);
    }

    @GetMapping
    public Page<Input> getInputs(@RequestParam int page){
        return inputService.getInputs(page);
    }

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.editInput(id,inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }
}
