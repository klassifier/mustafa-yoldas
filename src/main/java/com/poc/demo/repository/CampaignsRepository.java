package com.poc.demo.repository;

import com.poc.demo.dto.CampaignsDto;
import com.poc.demo.dto.StatisticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.demo.model.CampaignsModel;

import java.util.List;

@Repository
public interface CampaignsRepository extends JpaRepository<CampaignsModel, Integer>{

	CampaignsModel findByTitle(String title);
	
	CampaignsModel findByCampaignId(Integer campaignId);

	@Query("SELECT new com.poc.demo.dto.CampaignsDto(c.campaignId, c.title, c.description,  c.category.categoryName, c.status," +
			"CASE WHEN (SELECT COUNT(c2.campaignId) FROM CampaignsModel c2 WHERE c2.category.categoryId = c.category.categoryId AND c2.title = c.title AND c2.description = c.description) > 1 THEN 1 ELSE 0 END) " +
			"FROM CampaignsModel c")
	List<CampaignsDto> findCampaignsWithDuplicates();

	@Query("SELECT new com.poc.demo.dto.StatisticsDto(c.status, COUNT(c.status)) FROM CampaignsModel c GROUP BY c.status")
	List<StatisticsDto> countByStatus();
}
