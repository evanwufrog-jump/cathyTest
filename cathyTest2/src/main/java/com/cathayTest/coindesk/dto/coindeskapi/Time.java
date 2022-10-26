package com.cathayTest.coindesk.dto.coindeskapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class Time {
//	@JsonFormat(pattern = "MMM dd, yyyy HH:mm:ss zzz")
//	private Date updated;

	private String updated;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private Date updatedISO;

	private String updateduk;

}
