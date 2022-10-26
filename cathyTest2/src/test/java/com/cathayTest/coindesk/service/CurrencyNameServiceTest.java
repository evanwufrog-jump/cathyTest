package com.cathayTest.coindesk.service;

import com.cathayTest.coindesk.entity.CurrencyName;
import com.cathayTest.coindesk.repository.CurrencyNameRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyNameServiceTest {


    @Mock(lenient = true)
    CurrencyNameRepository currencyNameRepository;

    CurrencyNameService currencyNameService;

    @BeforeEach
    public void testInitial() throws Exception{
        currencyNameService = new CurrencyNameService();
        currencyNameService.setCurrencyNameRepository(currencyNameRepository);
        //===========虛擬資料庫內資料===========
        CurrencyName USDCurrencyData = new CurrencyName();
        USDCurrencyData.setName("美金");
        USDCurrencyData.setCode("USD");
        USDCurrencyData.setId(1);

        CurrencyName GBPCurrencyData = new CurrencyName();
        GBPCurrencyData.setName("英鎊");
        GBPCurrencyData.setCode("GBP");
        GBPCurrencyData.setId(2);

        CurrencyName EURCurrencyData = new CurrencyName();
        EURCurrencyData.setName("歐元");
        EURCurrencyData.setCode("EUR");
        EURCurrencyData.setId(3);

        List<CurrencyName> currencyNameList= new ArrayList<CurrencyName>();
        currencyNameList.add(USDCurrencyData);
        currencyNameList.add(GBPCurrencyData);
        currencyNameList.add(EURCurrencyData);



        //=========測試存入的entity=========
        CurrencyName updatedCurrency = new CurrencyName();
        updatedCurrency.setName("美元");
        updatedCurrency.setCode("USD");
        updatedCurrency.setId(1);
        //========測試新增的entity=========
        CurrencyName createCurrencyName = new CurrencyName();
        createCurrencyName.setName("台幣");
        createCurrencyName.setCode("NTD");
        createCurrencyName.setId(4);

        //findCurrencyNameAll
        when(currencyNameRepository.findAll()).thenReturn(currencyNameList);
        when(currencyNameRepository.findByCode("USD")).thenReturn(USDCurrencyData);

        when(currencyNameRepository.save(Mockito.any())).thenReturn(createCurrencyName);
        when(currencyNameRepository.findByCode("USD")).thenReturn(USDCurrencyData);
        when(currencyNameRepository.findByCode("NTD")).thenReturn(null);
        when(currencyNameRepository.save(updatedCurrency)).thenReturn(updatedCurrency);
        doNothing().when(currencyNameRepository).deleteByCode("USD");

    }
    //1. 測試呼叫查詢幣別對應表資料 API,並顯示其內容。
    @Test
    void findCurrencyNameAll() throws Exception{
        List<CurrencyName> currencyNames = currencyNameService.findCurrencyNameAll();
        verify(currencyNameRepository,only()).findAll();
        for (CurrencyName c : currencyNames) {
            System.out.println(c);
        }
    }

    @Test
    void findCurrencyDataByCode() {
        CurrencyName currencyName = currencyNameService.findCurrencyDataByCode("USD");
        verify(currencyNameRepository,only()).findByCode("USD");
        System.out.println(currencyName);
    }

    @Test
    void createCurrencyName(){
        CurrencyName currencyName = new CurrencyName();
        currencyName.setName("台幣");
        currencyName.setCode("NTD");
        currencyName.setId(4);
        currencyNameService.createCurrencyName(currencyName);
        verify(currencyNameRepository,times(1)).findByCode(Mockito.any());
    }

    @Test
    void updateCurrencyName(){
        CurrencyName currencyName = new CurrencyName();
        currencyName.setName("美元");
        currencyName.setCode("USD");
        currencyName.setId(1);
        CurrencyName updatedCurrencyName =(CurrencyName) currencyNameService.updateCurrencyName(currencyName).getResponseData();
        verify(currencyNameRepository,times(1)).save(currencyName);
    }

    @Test
    void deleteCurrencyNameByCode(){
        currencyNameRepository.deleteByCode("USD");
        verify(currencyNameRepository,times(1)).deleteByCode("USD");
    }

    @Test
    void getMyApiData() throws Exception {
        Object APIData = currencyNameService.getMyApiData();
        System.out.println(APIData);
        verify(currencyNameRepository,times(3)).findByCode(Mockito.any());
    }



}
