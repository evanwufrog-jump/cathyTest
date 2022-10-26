package com.cathayTest.coindesk.repository;



import com.cathayTest.coindesk.entity.CurrencyName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyNameRepository extends JpaRepository<CurrencyName, Integer> {
    //使用Code查詢
	CurrencyName findByCode(String code);
    //使用Code刪除
    void deleteByCode(String code);
    //使用name
    List<CurrencyName> findAll();
}