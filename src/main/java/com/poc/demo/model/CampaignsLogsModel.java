package com.poc.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class CampaignsLogsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	private Integer logId;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "campaign_id")
	private CampaignsModel campaign;

	@NotNull(message = "Campaign status can 't be empty")
	@Column(name = "status")
	private Integer status;

	@Column(name = "log_date_time")
	private LocalDateTime logDateTime;

	public CampaignsLogsModel() {
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public CampaignsModel getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignsModel campaign) {
		this.campaign = campaign;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(LocalDateTime logDateTime) {
		this.logDateTime = logDateTime;
	}

}
