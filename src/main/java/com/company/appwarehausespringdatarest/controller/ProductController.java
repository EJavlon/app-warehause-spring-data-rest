package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.entity.Product;
import com.company.appwarehausespringdatarest.payload.ProductDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping("/{id}")
    public Result getProduct(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @GetMapping
    public Page<Product> getProducts(@RequestParam int page){
        return productService.getProducts(page);
    }

    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.editProduct(id,productDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
}
