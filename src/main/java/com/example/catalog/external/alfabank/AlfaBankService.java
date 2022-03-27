package com.example.catalog.external.alfabank;

import com.example.catalog.external.alfabank.dto.CurrencyDto;
import com.example.catalog.external.alfabank.dto.CurrencyListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AlfaBankService {

    /**
     * The Rest template .
     */
    @Autowired
    @Qualifier("alfaBank")
    private RestTemplate restTemplate;

    @Value("${alfa-bank.url}")
    private String url;

    public Map<String, BigDecimal> getCurrencyPrice(BigDecimal priceByn) {

        Map<String, BigDecimal> priceCurrencyMap = new HashMap<>();
        priceCurrencyMap.put("BYN", priceByn);
        List<CurrencyDto> currencyDtoList = getRateList().getCurrencyRates();
        currencyDtoList.removeIf(currencyDto -> currencyDto.getBuyCode() != 933);
        for (CurrencyDto currencyDto : currencyDtoList) {
            BigDecimal result = priceByn
                    .divide(currencyDto.getBuyRate(), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(currencyDto.getQuantity()));
            priceCurrencyMap.put(currencyDto.getSellIso(), result);
        }
        log.debug("Get price Byn = {}, result = {}", priceByn, priceCurrencyMap);
        return priceCurrencyMap;
    }

    private CurrencyListDto getRateList() {

        return restTemplate.getForObject(url, CurrencyListDto.class);
    }
}
