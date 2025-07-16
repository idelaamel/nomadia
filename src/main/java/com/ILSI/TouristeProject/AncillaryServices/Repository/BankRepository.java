package com.ILSI.TouristeProject.AncillaryServices.Repository;

import com.ILSI.TouristeProject.AncillaryServices.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findBankByName(String name);

}
