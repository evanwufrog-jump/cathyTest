package com.cathayTest.coindesk.dto.coindeskapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
//coindesk API內的幣別資料
@Data
public class CurrencyData {

    String code = "";
//    String symbol="";
//    String rate="";
//    String description = "";
    String name = "";

    @JsonProperty("rate_float")
    @SerializedName(value = "rate_float")
    double rate_float = 0;


}
