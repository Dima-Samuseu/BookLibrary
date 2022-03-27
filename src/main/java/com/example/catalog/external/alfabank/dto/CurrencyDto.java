package com.example.catalog.external.alfabank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * Курс валюты в альфа банке
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    @JsonProperty("sellRate")
    private BigDecimal sellRate;
    @JsonProperty("sellIso")
    private String sellIso;
    @JsonProperty("sellCode")
    private Integer sellCode;
    @JsonProperty("buyRate")
    private BigDecimal buyRate;
    @JsonProperty("buyIso")
    private String buyIso;
    @JsonProperty("buyCode")
    private Integer buyCode;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("name")
    private String name;
    @JsonProperty("date")
    private String date;
}
