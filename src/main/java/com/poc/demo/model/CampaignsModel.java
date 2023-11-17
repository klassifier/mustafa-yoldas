package com.poc.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "campaigns")
public class CampaignsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "campaign_id")
	private Integer campaignId;
	@NotNull(message = "Campaign title can 't be empty")
	private String title;
	@NotNull(message = "Campaign description can 't be empty")
	@Column(name = "description")
	private String description;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CategoriesModel category;

	@NotNull(message = "Campaign status can 't be empty")
	@Column(name = "status")
	private Integer status;

	public CampaignsModel() {
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public CategoriesModel getCategory() {
		return category;
	}

	public void setCategory(CategoriesModel category) {
		this.category = category;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CampaignsModel [campaignId=" + campaignId + ", title=" + title + ", description=" + description
				+ ", category=" + category + ", status=" + status + "]";
	}

}
