package com.poc.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticsDto {
	private Integer status;
	private String statusName;

	private Long count;

	public StatisticsDto(Integer status, Long count)
	{
		this.status=status;
		this.count=count;

		switch (status) {
			case 0:
				this.statusName = "DEAKTİF";
				break;
			case 1:
				this.statusName = "AKTİF";
				break;
			case 2:
				this.statusName = "ONAY BEKLİYOR";
				break;
			default:
				break;
		}
	}
}
