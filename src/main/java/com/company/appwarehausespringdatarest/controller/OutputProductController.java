package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.entity.OutputProduct;
import com.company.appwarehausespringdatarest.payload.OutputProductDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.service.OutputProductSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output-product")
public class OutputProductController {
    @Autowired
    private OutputProductSerive outputProductSerive;

    @GetMapping("/{id}")
    public Result getOutputProduct(@PathVariable Integer id){
        return outputProductSerive.getOutputProduct(id);
    }

    @GetMapping
    public Page<OutputProduct> getOutputProdcuts(@RequestParam int page){
        return outputProductSerive.getOutputProducts(page);
    }

    @PutMapping("/{id}")
    public Result editOutputProdcut(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductSerive.editOutputProduct(id,outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id){
        return outputProductSerive.deleteOutputProduct(id);
    }
}
