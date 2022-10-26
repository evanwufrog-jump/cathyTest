package com.cathayTest.coindesk;

import com.cathayTest.coindesk.entity.CurrencyName;
import com.cathayTest.coindesk.repository.CurrencyNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

@SpringBootApplication
public class MainApplication {

//    @Autowired
//    CurrencyNameRepository currencyNameRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class,args);
//        Map<String,String> currencyNameData = new HashMap<>();
//        currencyNameData.put("USD","美金");
//        currencyNameData.put("GBP","英鎊");
//        currencyNameData.put("EUR","歐元");
//
//        Set<String> set = currencyNameData.keySet();
//        Iterator<String> iterator = set.iterator();
//        CurrencyName currencyName = new CurrencyName();
//        while(iterator.hasNext()){
//            String key = iterator.next();
//            currencyName.setCode(key);
//            currencyName.setName(currencyNameData.get(key));
//            currencyNameRepository.save(currencyName);
//        }

    }

}
