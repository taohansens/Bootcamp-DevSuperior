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

import com.taohbootcamp.dscatalog.dto.ProductDTO;
import com.taohbootcamp.dscatalog.entities.Product;
import com.taohbootcamp.dscatalog.repositories.ProductRepository;
import com.taohbootcamp.dscatalog.services.exceptions.DatabaseException;
import com.taohbootcamp.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
	Page<Product> list = repository.findAll(pageRequest);
	return list.map(x -> new ProductDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
		Product entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
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
}
