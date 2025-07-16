package com.ILSI.TouristeProject.AncillaryServices.Service;

import com.ILSI.TouristeProject.AncillaryServices.dto.BankDto;
import com.ILSI.TouristeProject.AncillaryServices.model.Bank;

import java.util.List;
import java.util.Optional;

public interface IBankService {

    List<Bank> findAllBanks();

    Optional<Bank> findBankByName(String name);

    String addBank(BankDto bankDto);

    String updateBank(Long bank_id, BankDto bankDto);

    String deleteBank(Long bank_id);
}
