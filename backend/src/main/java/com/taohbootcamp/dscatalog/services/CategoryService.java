package com.taohbootcamp.dscatalog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taohbootcamp.dscatalog.dto.CategoryDTO;
import com.taohbootcamp.dscatalog.entities.Category;
import com.taohbootcamp.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		List<CategoryDTO> listDto = new ArrayList<>();
		
		for (Category cat : list) {
			listDto.add(new CategoryDTO(cat));
		}
		return listDto;
	}
}
