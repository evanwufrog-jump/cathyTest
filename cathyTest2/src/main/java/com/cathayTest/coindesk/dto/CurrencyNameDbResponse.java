package com.cathayTest.coindesk.dto;

import lombok.Data;

//DTO
@Data
public class CurrencyNameDbResponse {
    private boolean databaseResponseStatus;
    private String responseMsg= "";
    private Object responseData;


}
