package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.entity.InputProduct;
import com.company.appwarehausespringdatarest.payload.InputProductDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.service.InputProductSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/input-product")
public class InputProductController {
    @Autowired
    private InputProductSerive inputProductSerive;

    @GetMapping("/{id}")
    public Result getInputProduct(@PathVariable Integer id){
        return inputProductSerive.getInputProduct(id);
    }

    @GetMapping
    public Page<InputProduct> getInputProducts(@RequestParam int page){
        return inputProductSerive.getInputProducts(page);
    }

    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductSerive.editInputProduct(id,inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        return inputProductSerive.deleteInputProduct(id);
    }
}
