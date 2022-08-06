package com.company.appwarehausespringdatarest.service;

import com.company.appwarehausespringdatarest.entity.Currency;
import com.company.appwarehausespringdatarest.entity.Input;
import com.company.appwarehausespringdatarest.entity.Supplier;
import com.company.appwarehausespringdatarest.entity.Warehause;
import com.company.appwarehausespringdatarest.payload.InputDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.repasitory.CurrencyRepository;
import com.company.appwarehausespringdatarest.repasitory.InputRepository;
import com.company.appwarehausespringdatarest.repasitory.SupplierRepository;
import com.company.appwarehausespringdatarest.repasitory.WarehauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private WarehauseRepository warehauseRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    public Result getInputBydId(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);

        return optionalInput.isPresent() ? new Result("Input found",true,optionalInput.get()):
                new Result("Input not found",false);
    }

    public Page<Input> getInputs(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return inputRepository.findAll(pageable);
    }

    public Result addInput(InputDto inputDto) {
        Optional<Warehause> optionalWarehause = warehauseRepository.findById(inputDto.getWarehauseId());
        if (!optionalWarehause.isPresent()) return new Result("Warehause not found",false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) return new Result("Supplier not found",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new Result("Currency not found",false);

        Input input = new Input();
        input.setWarehause(optionalWarehause.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurruncy(optionalCurrency.get());
        input.setTimestamp(inputDto.getDate());
        input.setFacuturNumber(UUID.randomUUID().toString());
        input.setCode(String.valueOf(inputRepository.countAllInput()+1));
        inputRepository.save(input);
        return new Result("Input seccessfully added",true);
    }

    public Result editInput(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return new Result("Input not found",false);

        Optional<Warehause> optionalWarehause = warehauseRepository.findById(inputDto.getWarehauseId());
        if (!optionalWarehause.isPresent()) return new Result("Warehause not found",false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) return new Result("Supplier not found",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new Result("Currency not found",false);

        Input input = optionalInput.get();
        input.setWarehause(optionalWarehause.get());
        input.setCurruncy(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setTimestamp(inputDto.getDate());
        inputRepository.save(input);
        return new Result("Input seccessfully edited",true);
    }

    public Result deleteInput(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) return new Result("Input not found",false);

        inputRepository.deleteById(id);
        return new Result("Input seccessfully deleted",true);
    }
}
