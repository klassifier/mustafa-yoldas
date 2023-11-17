package com.poc.demo.repository;

import com.poc.demo.dto.CampaignsDto;
import com.poc.demo.dto.CampaignsLogsDto;
import com.poc.demo.model.CampaignsLogsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignsLogsRepository extends JpaRepository<CampaignsLogsModel, Integer>{

	@Query("SELECT new com.poc.demo.dto.CampaignsLogsDto(c.logId, c.campaign.campaignId, c.campaign.title, c.status,  c.logDateTime) " +
 			"FROM CampaignsLogsModel c "+
			"WHERE c.campaign.campaignId = :campaignId")
	List<CampaignsLogsDto> findCampaignsLogsByCampaignId(@Param("campaignId") Integer campaignId);


}
