package com.poc.demo.controller;

import com.poc.demo.dto.CampaignsDto;
import com.poc.demo.model.CampaignsModel;
import com.poc.demo.model.CategoriesModel;
import com.poc.demo.serviceImpl.CampaignsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CampaignsControllerTest {

    @Mock
    private CampaignsServiceImpl campaignService;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private CampaignsController campaignsController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCampaigns_Success() {
        CampaignsModel validCampaign = new CampaignsModel();
        validCampaign.setCampaignId(1);
        validCampaign.setTitle("New Campaign Title");
        validCampaign.setDescription("New Campaign Description");
        validCampaign.setStatus(1);

        CategoriesModel validCategories = new CategoriesModel();
        validCategories.setCategoryId(1);
        validCategories.setCategoryName("Tamamlayıcı Sağlık Sigortası(TSS)");
        validCampaign.setCategory(validCategories);

        when(bindingResult.hasErrors()).thenReturn(false);

        String result = campaignsController.addCampaigns(validCampaign, redirectAttributes, bindingResult);

        assertEquals("redirect:/campaigns/add", result);
    }

    @Test
    public void testUpdateCampaigns_Success() {
        CampaignsModel validCampaign = new CampaignsModel();
        validCampaign.setCampaignId(1);
        validCampaign.setTitle("New Campaign Title");
        validCampaign.setDescription("New Campaign Description");
        validCampaign.setStatus(1);

        CategoriesModel validCategories = new CategoriesModel();
        validCategories.setCategoryId(1);
        validCategories.setCategoryName("Tamamlayıcı Sağlık Sigortası(TSS)");
        validCampaign.setCategory(validCategories);

        when(bindingResult.hasErrors()).thenReturn(false);

        String result = campaignsController.update(validCampaign, redirectAttributes, bindingResult);

        assertEquals("redirect:/campaigns/update", result);
    }

    @Test
    public void testListCampaigns() {
        // Mock veri oluşturma
        List<CampaignsDto> mockCampaignList = new ArrayList<>();
        mockCampaignList.add(new CampaignsDto());
        mockCampaignList.add(new CampaignsDto());

        // campaignService.findCampaignsWithDuplicates() çağrısında mock veriyi döndürme
        when(campaignService.findCampaignsWithDuplicates()).thenReturn(mockCampaignList);

        // Test metodu çağrılıyor
        String viewName = campaignsController.list(model);

        // model.addAttribute ile doğru değerlerin eklendiğini kontrol etme
        verify(model, times(1)).addAttribute("pageTitle", "Kampanyalar | Liste");
        verify(model, times(1)).addAttribute("campaignList", mockCampaignList);

        // Doğru view adının döndüğünü kontrol etme
        assertEquals("campaigns/view-campaign", viewName);
    }

}
