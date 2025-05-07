package com.shinhan.service;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class StockSDTO {

	String stock_code;
	String stock_name;
}
