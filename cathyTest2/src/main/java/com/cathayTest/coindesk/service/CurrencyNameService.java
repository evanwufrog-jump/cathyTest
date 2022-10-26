package com.cathayTest.coindesk.service;


import com.cathayTest.coindesk.entity.CurrencyName;
import com.cathayTest.coindesk.repository.CurrencyNameRepository;
import com.cathayTest.coindesk.dto.CurrencyNameDbResponse;
import com.cathayTest.coindesk.dto.ClientData;
import com.cathayTest.coindesk.dto.coindeskapi.CoinDeskApiData;
import com.cathayTest.coindesk.dto.coindeskapi.CurrencyData;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
public class CurrencyNameService {

    @Autowired
    CurrencyNameRepository currencyNameRepository;

    //查詢所有幣別
    public List<CurrencyName> findCurrencyNameAll() {
        return currencyNameRepository.findAll();
    }

    //使用英文幣別名查詢幣別
    public CurrencyName findCurrencyDataByCode(String code) {
        return currencyNameRepository.findByCode(code);
    }

    //新增幣別名稱對應
    public CurrencyNameDbResponse createCurrencyName(CurrencyName currencyName) {
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();
        String msg = null;
        if (null!=currencyNameRepository.findByCode(currencyName.getCode())) {
            msg = "貨幣資料新增失敗";
            log.error(msg);
            currencyNameDbResponse.setDatabaseResponseStatus(false);
        } else {
            currencyNameDbResponse.setResponseData(currencyName);
            msg = "貨幣資料新增成功";
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            currencyNameDbResponse.setResponseMsg(msg);
            log.info(msg);
            currencyNameRepository.save(currencyName);
        }
        return currencyNameDbResponse;
    }

    //修改
    public CurrencyNameDbResponse updateCurrencyName(CurrencyName currencyName) {
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();
        String msg = null;

        if (null == currencyNameRepository.findByCode(currencyName.getCode())) {
            msg = "修改失敗!! 無法找到需要修改的貨幣資料!!";
            log.error(msg);
            currencyNameDbResponse.setDatabaseResponseStatus(false);
        } else {
            msg = "貨幣資料修改成功";
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            currencyNameDbResponse.setResponseMsg(msg);
            log.info(msg);
        }

        currencyNameRepository.save(currencyName);
        return currencyNameDbResponse;
    }

    //刪除
    public CurrencyNameDbResponse deleteCurrencyNameByCode(String code) {
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();

        if (null == currencyNameRepository.findByCode(code)) {
            currencyNameDbResponse.setResponseMsg("刪除失敗!! 貨幣不存在!!");
            log.error(currencyNameDbResponse.getResponseMsg());
            currencyNameDbResponse.setDatabaseResponseStatus(false);
        } else {
            currencyNameDbResponse.setResponseMsg("貨幣資料刪除成功");
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            currencyNameDbResponse.setResponseMsg(currencyNameDbResponse.getResponseMsg());
            log.info(currencyNameDbResponse.getResponseMsg());
        }
        currencyNameRepository.deleteByCode(code);
        return currencyNameDbResponse;
    }

    //依照題目格式API重定義原coindesk api格式
    public Object getMyApiData() throws Exception {
        HttpURLConnection connection = null;
        URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();

        try {
            //取得連線並將api內容存入字串apiDataString
            URLConnection urlConnection = url.openConnection();
            connection = (HttpURLConnection) urlConnection;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
            String apiDataString = "";
            String apiDataLine;
            while ((apiDataLine = in.readLine()) != null) {
                apiDataString += apiDataLine;
            }
            //處理取的到的字串
            ClientData clientData = new ClientData();
            //從api取得資料   序列化問題??
            CoinDeskApiData coinDeskApiData = new Gson().fromJson(apiDataString, CoinDeskApiData.class);

            //繼承上方資料取得api內的時間資訊
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss", Locale.ENGLISH);
            Date updatedISO = coinDeskApiData.getTime().getUpdatedISO();
//                    clientData.setUpdateTime(format.format(updatedISO));

            clientData.setUpdateTime(coinDeskApiData.getTime().getUpdatedISO());

            Map<String, CurrencyData> bpiMap = coinDeskApiData.getBpi();

            for (String s : bpiMap.keySet()) {
                CurrencyData currencyData = bpiMap.get(s);
                //自table中取得
                CurrencyName currencyName = currencyNameRepository.findByCode(currencyData.getCode());
                currencyData.setName(currencyName==null?currencyData.getCode():currencyName.getName());
                clientData.getCurrencyData().add(currencyData);

                currencyNameDbResponse.setResponseData(clientData);
            }
            currencyNameDbResponse.setDatabaseResponseStatus(true);
            currencyNameDbResponse.setResponseMsg("api success");
            log.info(currencyNameDbResponse.getResponseMsg());
            return clientData;
        } catch (Exception e) {
            currencyNameDbResponse.setResponseMsg("api fail");
            currencyNameDbResponse.setDatabaseResponseStatus(false);
            log.error(currencyNameDbResponse.getResponseMsg());
            log.error(e.getMessage());
        }
        //注意資料在此物件的clientData內
        return currencyNameDbResponse;
    }

    public Object getCoindeskApiData() throws Exception {
        HttpURLConnection connection = null;
        URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
        CurrencyNameDbResponse currencyNameDbResponse = new CurrencyNameDbResponse();

        try {
            //取得連線並將api內容存入字串apiDataString
            URLConnection urlConnection = url.openConnection();
            connection = (HttpURLConnection) urlConnection;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
            String apiDataString = "";
            String apiDataLine;
            while ((apiDataLine = in.readLine()) != null) {
                apiDataString += apiDataLine;
            }
            //從api取得資料   序列化問題??
            CoinDeskApiData coinDeskApiData = new Gson().fromJson(apiDataString, CoinDeskApiData.class);


            currencyNameDbResponse.setDatabaseResponseStatus(true);
            currencyNameDbResponse.setResponseMsg("api success");
            log.info(currencyNameDbResponse.getResponseMsg());
            return coinDeskApiData;
        } catch (Exception e) {
            currencyNameDbResponse.setResponseMsg("api fail");
            currencyNameDbResponse.setDatabaseResponseStatus(false);
            log.error(currencyNameDbResponse.getResponseMsg());
            log.error(e.getMessage());
        }
        //注意資料在此物件的clientData內
        return currencyNameDbResponse;
    }

    public void setCurrencyNameRepository(CurrencyNameRepository currencyNameRepository) {
        this.currencyNameRepository = currencyNameRepository;
    }
}
