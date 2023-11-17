package com.poc.demo.serviceImpl;

import java.util.List;

import com.poc.demo.model.CategoriesModel;
import com.poc.demo.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl {

	@Autowired
	private CategoriesRepository categoriesRepo;
	
	
	public List<CategoriesModel> getList(){
		return categoriesRepo.findAll();
	}
}
