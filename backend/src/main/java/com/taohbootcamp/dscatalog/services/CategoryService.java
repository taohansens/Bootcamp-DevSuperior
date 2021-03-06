package com.taohbootcamp.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taohbootcamp.dscatalog.dto.CategoryDTO;
import com.taohbootcamp.dscatalog.entities.Category;
import com.taohbootcamp.dscatalog.repositories.CategoryRepository;
import com.taohbootcamp.dscatalog.services.exceptions.DatabaseException;
import com.taohbootcamp.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> showAll() {
		List<Category> list = repository.findAll();

		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
		Category entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
		}
		catch (EntityNotFoundException e){
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
	}	catch (EmptyResultDataAccessException e) {
		String error = String.format("Erro. ID %s não encontrado", id);
		throw new ResourceNotFoundException(error);
	}catch (DataIntegrityViolationException e) {
		throw new DatabaseException("Integrity violation");
	}
}
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
	Page<Category> list = repository.findAll(pageRequest);
	return list.map(x -> new CategoryDTO(x));
	}

	public List<CategoryDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
