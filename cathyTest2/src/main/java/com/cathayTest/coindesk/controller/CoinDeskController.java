package com.cathayTest.coindesk.controller;


import com.cathayTest.coindesk.entity.CurrencyName;
import com.cathayTest.coindesk.service.CurrencyNameService;
import com.cathayTest.coindesk.dto.CurrencyNameDbResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/currencyNameApi")
public class CoinDeskController {

private final String REQUESTMAPPINGPRODUCES="application/json; charset=utf-8";

	@Autowired
    CurrencyNameService currencyNameService;

    //取得coindeskAPi

    @RequestMapping(value = "/getCoindeskApi", produces = REQUESTMAPPINGPRODUCES)
    public Object getCoindeskApi(){
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();
        try{
            currencyNameDbResponse.setResponseData(currencyNameService.getCoindeskApiData());
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            currencyNameDbResponse.setResponseMsg("自coindesk api取得資料");
            log.info(currencyNameDbResponse.getResponseMsg());

        }catch (Exception e){
            currencyNameDbResponse.setResponseMsg("取得coindesk api資訊失敗");
            currencyNameDbResponse.setDatabaseResponseStatus(false);
            log.error(currencyNameDbResponse.getResponseMsg());
        }
        return currencyNameDbResponse.getResponseData();
    }
    //取得coindeskAPi
    @RequestMapping(value = "/getMyApi", produces = REQUESTMAPPINGPRODUCES)
    public Object getMyApi(){
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();
        try{
            currencyNameDbResponse.setResponseData(currencyNameService.getMyApiData());
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            currencyNameDbResponse.setResponseMsg("自coindesk api取得資料");
            log.info(currencyNameDbResponse.getResponseMsg());

        }catch (Exception e){
            currencyNameDbResponse.setResponseMsg("取得coindesk api資訊失敗");
            currencyNameDbResponse.setDatabaseResponseStatus(false);
            log.error(currencyNameDbResponse.getResponseMsg());
        }
        return currencyNameDbResponse.getResponseData();
    }
    //查詢資料庫內所有api資料
    @RequestMapping(value = "/findCurrencyAll", produces = REQUESTMAPPINGPRODUCES)
    public Object findCurrencyAll() {
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();
        try{
            currencyNameDbResponse.setResponseData(currencyNameService.findCurrencyNameAll());
            currencyNameDbResponse.setResponseMsg("查詢所有資料成功");
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            log.info(currencyNameDbResponse.getResponseMsg());
        }catch (Exception e){
            currencyNameDbResponse.setResponseMsg("查詢所有資料失敗");
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            log.error(currencyNameDbResponse.getResponseMsg());
            System.out.println(e.getMessage());
        }
        return currencyNameDbResponse.getResponseData();
    }
    //查詢每個幣別的資料
    @RequestMapping(value = "/getCurrencyDataByCode/{code}", produces = REQUESTMAPPINGPRODUCES)
    public Object getCurrencyDataList(@PathVariable String code) {
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();

//            currencyNameDbResponse.setResponseData(currencyNameService.findCurrencyDataByCode(code));

        try{
            System.out.println("code==================="+code);
            currencyNameDbResponse.setResponseData(currencyNameService.findCurrencyDataByCode(code));

            System.out.println("data======================"+currencyNameDbResponse.getResponseData());
            currencyNameDbResponse.setResponseMsg("查詢"+code+"幣別資料成功");
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            log.info(currencyNameDbResponse.getResponseMsg());
        }catch (Exception e){
            currencyNameDbResponse.setResponseMsg("查詢"+code+"幣別資料失敗");
            currencyNameDbResponse.setDatabaseResponseStatus(false);
            log.error(currencyNameDbResponse.getResponseMsg());
            System.out.println(e.getMessage());
        }

        return currencyNameDbResponse;
    }
    //新增幣別資料
    @RequestMapping(value = "/createCurrencyData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object create(@Valid @RequestBody CurrencyName currencyName) {
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();
        CurrencyName currencyNameNew = (CurrencyName) currencyNameService.createCurrencyName(currencyName).getResponseData();
        return currencyNameDbResponse;
    }

    @RequestMapping(value = "/updateCurrencyData",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object update(@RequestBody CurrencyName currencyName) {
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();
        CurrencyName currencyNameNew = (CurrencyName) currencyNameService.updateCurrencyName(currencyName).getResponseData();
        return currencyNameDbResponse;
    }

    @RequestMapping(value = "/deleteCurrencyData/{code}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable String code) {
        CurrencyNameDbResponse currencyNameDbResponse = currencyNameService.deleteCurrencyNameByCode(code);
        return currencyNameDbResponse;
    }










}
