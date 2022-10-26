package com.cathayTest.coindesk.dto.coindeskapi;

import lombok.Data;

import java.util.Map;
    @Data
    public class CoinDeskApiData {
        String disclaimer = "";
        String chartName = "";
        Time time = new Time();
        Map<String, CurrencyData> bpi;

}
