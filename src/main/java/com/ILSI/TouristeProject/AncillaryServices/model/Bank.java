package com.ILSI.TouristeProject.AncillaryServices.model;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Bank extends AncillaryService {

    private String bankName;
    private boolean currencyExchange;
}
