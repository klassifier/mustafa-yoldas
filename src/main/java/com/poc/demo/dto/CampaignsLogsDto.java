package com.poc.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class CampaignsLogsDto {
	private Integer logId;
	private Integer campaignId;
	private String campaignTitle;
	private Integer status;
	private LocalDateTime logDateTime;
	private String strStatus;

	private String strLogDateTime;
	public CampaignsLogsDto(Integer logId, Integer campaignId, String campaignTitle, Integer status, LocalDateTime logDateTime)
	{
		this.logId=logId;
		this.campaignId=campaignId;
		this.campaignTitle=campaignTitle;
		this.status=status;
		this.logDateTime=logDateTime;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateTime = logDateTime.format(formatter);
		this.strLogDateTime= formattedDateTime;

		switch (status) {
			case 0:
				this.strStatus = "DEAKTİF";
				break;
			case 1:
				this.strStatus = "AKTİF";
				break;
			case 2:
				this.strStatus = "ONAY BEKLİYOR";
				break;
			default:
				break;
		}
	}
}
