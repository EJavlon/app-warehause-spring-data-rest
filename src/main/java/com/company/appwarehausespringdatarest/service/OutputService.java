package com.company.appwarehausespringdatarest.service;

import com.company.appwarehausespringdatarest.entity.Client;
import com.company.appwarehausespringdatarest.entity.Currency;
import com.company.appwarehausespringdatarest.entity.Output;
import com.company.appwarehausespringdatarest.entity.Warehause;
import com.company.appwarehausespringdatarest.payload.OutputDto;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.repasitory.ClientRepository;
import com.company.appwarehausespringdatarest.repasitory.CurrencyRepository;
import com.company.appwarehausespringdatarest.repasitory.OutputRepository;
import com.company.appwarehausespringdatarest.repasitory.WarehauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {
    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private WarehauseRepository warehauseRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    public Result getOutputBydId(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);

        return optionalOutput.isPresent() ? new Result("Output found",true,optionalOutput.get()):
                new Result("Output not found",false);
    }

    public Page<Output> getOutputs(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return outputRepository.findAll(pageable);
    }

    public Result addOutput(OutputDto outputDto) {
        Optional<Warehause> optionalWarehause = warehauseRepository.findById(outputDto.getWarehauseId());
        if (!optionalWarehause.isPresent()) return new Result("Warehause not found",false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) return new Result("Client not found",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new Result("Currency not found",false);

        Output output = new Output();
        output.setWarehause(optionalWarehause.get());
        output.setClient(optionalClient.get());
        output.setCurruncy(optionalCurrency.get());
        output.setTimestamp(outputDto.getDate());
        output.setFacuturNumber(UUID.randomUUID().toString());
        output.setCode(String.valueOf(outputRepository.countAllOutput()+1));
        outputRepository.save(output);
        return new Result("Output seccessfully added",true);
    }

    public Result editOutput(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new Result("Output not found",false);

        Optional<Warehause> optionalWarehause = warehauseRepository.findById(outputDto.getWarehauseId());
        if (!optionalWarehause.isPresent()) return new Result("Warehause not found",false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) return new Result("Client not found",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) return new Result("Currency not found",false);

        Output output = optionalOutput.get();
        output.setWarehause(optionalWarehause.get());
        output.setCurruncy(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setTimestamp(outputDto.getDate());
        outputRepository.save(output);
        return new Result("Output seccessfully edited",true);
    }

    public Result deleteOutput(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) return new Result("Output not found",false);

        outputRepository.deleteById(id);
        return new Result("Output seccessfully deleted",true);
    }
}
