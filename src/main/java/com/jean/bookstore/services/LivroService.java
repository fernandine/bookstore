package com.jean.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.bookstore.domain.Categoria;
import com.jean.bookstore.domain.Livro;
import com.jean.bookstore.exceptions.ObjectNotFoundException;
import com.jean.bookstore.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(
	()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", tipo: " + Livro.class.getName()));
		
	}
	
	
	public List<Livro> findAll(Integer idCat){
		categoriaService.findById(idCat);
		return repository.findAllByCategoria(idCat);
	}
	

	public Livro udpate(Integer id, Livro obj) {
		Livro newobj = findById(id);
		updateData(newobj, obj);
		return repository.save(newobj);
	}

	private void updateData(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setNomeAutor(obj.getNomeAutor());
		newObj.setTexto(obj.getTexto());		
	}


	public Livro create(Integer idCat, Livro obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(idCat);
		obj.setCategoria(cat);
		return repository.save(obj);
		
	}

	public void delete(Integer id) {
		Livro obj = findById(id);
		repository.delete(obj);
		
	}
}
