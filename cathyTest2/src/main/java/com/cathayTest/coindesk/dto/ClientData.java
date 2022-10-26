package com.cathayTest.coindesk.dto;

import com.cathayTest.coindesk.dto.coindeskapi.CurrencyData;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//依照client Api需要
@Data
public class ClientData {

    //題目要求時間
    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy/MMM/dd HH:mm:ss",locale = "English")
    Date updateTime;
    //各幣別資料
    List<CurrencyData> currencyData = new ArrayList<CurrencyData>();

}