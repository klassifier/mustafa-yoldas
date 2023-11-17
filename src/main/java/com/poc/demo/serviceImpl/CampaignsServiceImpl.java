package com.poc.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import com.poc.demo.dto.CampaignsDto;
import com.poc.demo.dto.CampaignsLogsDto;
import com.poc.demo.dto.StatisticsDto;
import com.poc.demo.model.CampaignsLogsModel;
import com.poc.demo.repository.CampaignsLogsRepository;
import com.poc.demo.repository.CampaignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.demo.model.CampaignsModel;

@Service
public class CampaignsServiceImpl {

	@Autowired
	private CampaignsRepository campaignRepo;

	@Autowired
	private CampaignsLogsRepository campaignLogsRepo;

	public CampaignsModel save(CampaignsModel campaign) {
		return campaignRepo.save(campaign);
	}

	public CampaignsModel findByTitle(String title) {
		return campaignRepo.findByTitle(title);
	}

	public CampaignsModel findByCampaignId(Integer campaignId) {
		return campaignRepo.findByCampaignId(campaignId);
	}

	public List<CampaignsModel> getList() {
		return campaignRepo.findAll();
	}

	public void deleteByCampaignId(Integer campaignId) {
		CampaignsModel campaign = campaignRepo.findByCampaignId(campaignId);
		campaignRepo.delete(campaign);
	}

	public List<CampaignsDto> findCampaignsWithDuplicates() {
		return campaignRepo.findCampaignsWithDuplicates();
	}

	public List<StatisticsDto> countByStatus() {
		return campaignRepo.countByStatus();
	}

	public List<CampaignsLogsDto> findCampaignsLogsByCampaignId(Integer campaignId) {
		return campaignLogsRepo.findCampaignsLogsByCampaignId(campaignId);
	}

	public CampaignsLogsModel saveLog(CampaignsModel campaign) {
		CampaignsLogsModel campaignsLogsModel = new CampaignsLogsModel();
		campaignsLogsModel.setCampaign(campaign);
		campaignsLogsModel.setStatus(campaign.getStatus());
		campaignsLogsModel.setLogDateTime(LocalDateTime.now());
		return campaignLogsRepo.save(campaignsLogsModel);
	}

}
