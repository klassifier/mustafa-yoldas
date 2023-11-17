package com.poc.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CampaignsDto {
	private Integer campaignId;
	private String title;
	private String description;
	private String categoryName;

	private Integer status;
	private Integer mukerrer;
	private String strStatus;

	public CampaignsDto(Integer campaignId,String title,String description, String categoryName,Integer status, Integer mukerrer)
	{
		this.campaignId=campaignId;
		this.title=title;
		this.description=description;
		this.categoryName=categoryName;
		this.status=status;
		this.mukerrer=mukerrer;

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
