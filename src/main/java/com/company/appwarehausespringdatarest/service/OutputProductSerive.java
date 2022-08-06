package com.company.appwarehausespringdatarest.service;

import com.company.appwarehausespringdatarest.entity.Output;
import com.company.appwarehausespringdatarest.entity.OutputProduct;
import com.company.appwarehausespringdatarest.entity.Product;
import com.company.appwarehausespringdatarest.payload.OutputProductDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.repasitory.OutputProductRepository;
import com.company.appwarehausespringdatarest.repasitory.OutputRepository;
import com.company.appwarehausespringdatarest.repasitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutputProductSerive {
    @Autowired
    private OutputProductRepository outputProductRepository;
    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private ProductRepository productRepository;

    public Result getOutputProduct(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) return new Result("Output-Product not found",false);

        return new Result("Output-Prodcut found",true,optionalOutputProduct.get());
    }

    public Page<OutputProduct> getOutputProducts(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return outputProductRepository.findAll(pageable);
    }

    public Result editOutputProduct(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) return new Result("Output-Product not found",false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) return new Result("Output not found",false);

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) return new Result("Product not found",false);

        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);

        return new Result("Output Prodcut seccessfully edited",true);
    }

    public Result deleteOutputProduct(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) return new Result("Output-Product not found",false);

        outputProductRepository.deleteById(id);

        return new Result("Output Prodcut seccessfully deleted",true);
    }
}
