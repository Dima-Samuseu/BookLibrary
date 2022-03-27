package com.example.catalog.external.alfabank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyListDto {

    @JsonProperty("rates")
    @Valid
    private List<CurrencyDto> currencyRates;
}
