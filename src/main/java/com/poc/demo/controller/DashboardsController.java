package com.poc.demo.controller;

import com.poc.demo.serviceImpl.CampaignsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "dashboard")
@Controller
public class DashboardsController {
	@Autowired
	private CampaignsServiceImpl campaignService;

	@GetMapping(value = "classifieds/statistics")
	public String list(Model model) {
		model.addAttribute("pageTitle", "Kampanyalar | İstatistikler");
		model.addAttribute("statisticsList", campaignService.countByStatus());
		return "dashboard/classifieds/statistics";
	}
}
