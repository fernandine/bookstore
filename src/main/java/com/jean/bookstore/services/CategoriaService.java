package com.jean.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.bookstore.DTOs.CategoriaDTO;
import com.jean.bookstore.domain.Categoria;
import com.jean.bookstore.exceptions.ObjectNotFoundException;
import com.jean.bookstore.repositories.CategoriaRepository;
import com.jean.bookstore.services.exceptions.DataIntegrityViolationException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(
	()-> new ObjectNotFoundException("Objeto não foi encontrado! Id: " + id + ", tipo: " + Categoria.class.getName()));
		
	}
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria udpate(Integer id, CategoriaDTO objDto) {
		Categoria obj = findById(id);
		obj.setNome(obj.getNome());
		obj.setDescricao(obj.getDescricao());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new com.jean.bookstore.services.exceptions.DataIntegrityViolationException(
					"Categoria não pode ser deletada!");
		}
		
	}
}
