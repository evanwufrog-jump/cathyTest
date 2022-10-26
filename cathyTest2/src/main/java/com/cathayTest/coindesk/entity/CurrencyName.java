package com.cathayTest.coindesk.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

//建立資料表SQL
//CREATE TABLE `currency_name` (
//        `id` INT NOT NULL AUTO_INCREMENT,
//        `name` VARCHAR(45) NULL,
//        `code` VARCHAR(45) NULL,
//        PRIMARY KEY (`id`));
//INSERT INTO `currency_name` (1,"USD","美金");
//INSERT INTO `currency_name` (2,"GBP","英鎊");
//INSERT INTO `currency_name` (3,"EUR","歐元");

//@Builder
@Data
@Entity
@Table(name="currency_name")
public class CurrencyName {

    //自增ID for PK
    @Id
    @Column(name = "id")
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //貨幣英文名稱
    @Column(name = "code",length = 10)
    @NotEmpty
    private String code;

    //貨幣中文名稱
    @Column(name = "name",length = 10)
    @NotEmpty
    private String name;
}
