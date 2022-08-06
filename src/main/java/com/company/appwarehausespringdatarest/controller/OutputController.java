package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.entity.Output;
import com.company.appwarehausespringdatarest.payload.OutputDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    private OutputService outputService;

    @GetMapping("/{id}")
    public Result getOutputById(@PathVariable Integer id){
        return outputService.getOutputBydId(id);
    }

    @GetMapping
    public Page<Output> getOutputs(@RequestParam int page){
        return outputService.getOutputs(page);
    }

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }

    @PutMapping("/{id}")
    public Result editOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        return outputService.editOutput(id,outputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        return outputService.deleteOutput(id);
    }
}
