package com.poc.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poc.demo.model.CampaignsModel;
import com.poc.demo.serviceImpl.CategoriesServiceImpl;
import com.poc.demo.serviceImpl.CampaignsServiceImpl;

@RequestMapping(value = "campaigns")
@Controller
public class CampaignsController {

	@Autowired
	private CategoriesServiceImpl categoryService;
	@Autowired
	private CampaignsServiceImpl campaignService;

	@GetMapping(value = { "/add", "/" })
	public String addCampaigns(Model model) {
		model.addAttribute("pageTitle", "Campaigns | Add");
		model.addAttribute("categoryList", categoryService.getList());
		return "campaigns/add-campaign";
	}

	@PostMapping(value = "/add")
	public String addCampaigns(@Valid @ModelAttribute CampaignsModel campaigns, RedirectAttributes redirectAttr,
							  BindingResult result) {

		switch (campaigns.getCategory().getCategoryId())
		{
			case 3:
				campaigns.setStatus(1);
				break;
			default:
				campaigns.setStatus(2);
				break;
		}

		String msg = "";
		if (result.hasErrors()) {
			FieldError error = null;
			for (Object obj : result.getAllErrors()) {
				error = (FieldError) obj;
				msg += error.getDefaultMessage();
			}
		}
		if (msg.length() == 0) {
			try {
				String title = campaigns.getTitle();
				char firstChar = title.charAt(0);
				if (Character.isDigit(firstChar)) {
					CampaignsModel camp = campaignService.save(campaigns);

					campaignService.saveLog(camp);

					redirectAttr.addFlashAttribute("status", "success");
					redirectAttr.addFlashAttribute("message", "Kampanya bilgileri başarıyla kaydedildi");
				} else {
					redirectAttr.addFlashAttribute("status", "error");
					redirectAttr.addFlashAttribute("message", "Başlık bir sayı ile başlamıyor");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			redirectAttr.addFlashAttribute("status", "error");
			redirectAttr.addFlashAttribute("message", msg);
		}
		return "redirect:/campaigns/add";
	}

	@GetMapping(value = "view")
	public String list(Model model) {
		model.addAttribute("pageTitle", "Kampanyalar | Liste");
		//model.addAttribute("campaignList", campaignService.getList());
		model.addAttribute("campaignList", campaignService.findCampaignsWithDuplicates());
		return "campaigns/view-campaign";
	}

	@GetMapping(value = "/edit/{campaignId}")
	public String edit(@PathVariable Integer campaignId, Model model, RedirectAttributes redirectAttr) {
		try {
			if (campaignId > 0) {
				CampaignsModel campaign = campaignService.findByCampaignId(campaignId);
				if (campaign != null) {
					model.addAttribute("campaign", campaign);
					model.addAttribute("categoryList", categoryService.getList());
				} else {
					redirectAttr.addFlashAttribute("message", "Kampanya bulunamadı");
					return "redirect:/campaigns/view";
				}
			} else {
				redirectAttr.addFlashAttribute("message", "Kampanya bulunamadı");
				return "redirect:/campaigns/view";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("pageTitle", "Kampanya | Güncelleme");
		return "campaigns/edit-campaign";
	}

	@PostMapping(value = "/update")
	public String update(@Valid @ModelAttribute CampaignsModel campaign, RedirectAttributes redirectAttr,
						 BindingResult result) {
		String msg = "";
		redirectAttr.addFlashAttribute("status", "error");
		try {
			if (campaign != null && campaign.getCampaignId() > 0) {
				if (result.hasErrors()) {
					FieldError error = null;
					for (Object obj : result.getAllErrors()) {
						error = (FieldError) obj;
						msg += error.getDefaultMessage();
					}
				}
				if (msg.length() == 0) {
					CampaignsModel oldModel = campaignService.findByCampaignId(campaign.getCampaignId());
					if (!oldModel.getTitle().equalsIgnoreCase(campaign.getTitle())) {

						String title = campaign.getTitle();
						char firstChar = title.charAt(0);
						if (!Character.isDigit(firstChar)){
							redirectAttr.addFlashAttribute("message", "Başlık bir sayı ile başlamıyor");
							return "redirect:/campaigns/edit/" + campaign.getCampaignId();
						}
					}
					campaignService.save(campaign);
					campaignService.saveLog(campaign);

					redirectAttr.addFlashAttribute("status", "success");
					redirectAttr.addFlashAttribute("message", "Kampanya Bilgileri Başarıyla Güncellendi");
					return "redirect:/campaigns/view";
				} else {
					redirectAttr.addFlashAttribute("message", msg);
					return "redirect:/campaigns/edit/" + campaign.getCampaignId();
				}
			} else {
				redirectAttr.addFlashAttribute("message", "Kampanya bulunamadı");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/campaigns/update";
	}

	@GetMapping(value = "/delete/{campaignId}")
	public String delete(@PathVariable Integer campaignId, RedirectAttributes redirectAttr) {
		if (campaignId != null) {
			try {
				campaignService.deleteByCampaignId(campaignId);
				redirectAttr.addFlashAttribute("status", "success");
				redirectAttr.addFlashAttribute("message", "Kampanya Bilgileri başarıyla silindi");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "redirect:/campaigns/view";
	}

	@GetMapping(value = "/logs/{campaignId}")
	public String logs(@PathVariable Integer campaignId, Model model) {
		model.addAttribute("pageTitle", "Kampanya Logu");
		model.addAttribute("campaignLogList", campaignService.findCampaignsLogsByCampaignId(campaignId));
		return "campaigns/logs-campaign";
	}
}
