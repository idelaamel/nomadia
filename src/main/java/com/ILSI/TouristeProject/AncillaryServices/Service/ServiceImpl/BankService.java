package com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl;

import com.ILSI.TouristeProject.AncillaryServices.Repository.BankRepository;
import com.ILSI.TouristeProject.AncillaryServices.Service.IBankService;
import com.ILSI.TouristeProject.AncillaryServices.dto.BankDto;
import com.ILSI.TouristeProject.AncillaryServices.model.Bank;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.GuestHouseDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.GuestHouse;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceNotFound;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService implements IBankService {

    private final BankRepository bankRepository;
    private final AncillaryServicesService ancillaryServicesService;
    @Override
    public List<Bank> findAllBanks() {return bankRepository.findAll();}

    @Override
    public Optional<Bank> findBankByName(String name) {return bankRepository.findBankByName(name);}

    @Override
    public String addBank(BankDto bankDto) {
        Optional<Bank> existingBank = bankRepository.findBankByName(bankDto.getName());
        if(existingBank.isPresent()){
            throw new AncillaryServiceAlreadyExistException("Bank with Name "+bankDto.getName()+" Already Exist");
        }
        this.getBank(bankDto, new Bank());
        return "Success! Bank Created successfully";
    }

    @Override
    public String updateBank(Long bank_id, BankDto bankDto) {
        Bank existingBank = bankRepository.findById(bank_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Bank with ID = "+bank_id+" Not Found"));
        this.getBank(bankDto, existingBank);
        return "Success! Bank Updated successfully";
    }

    @Override
    public String deleteBank(Long bank_id) {
        Bank existingBank = bankRepository.findById(bank_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Bank with ID = "+bank_id+" Not Found"));
        bankRepository.delete(existingBank);
        return "Success! Bank deleted successfully";
    }


    private void getBank(BankDto bankDto, Bank newBank){
        ancillaryServicesService.getAncillaryService(bankDto, newBank);
        newBank.setBankName(bankDto.getName());
        newBank.setCurrencyExchange(bankDto.isCurrencyExchange());
        bankRepository.save(newBank);
    }
}
