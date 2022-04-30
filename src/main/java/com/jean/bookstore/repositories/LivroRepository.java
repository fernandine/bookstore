package com.jean.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jean.bookstore.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

	@Query("SELECT obj FROM Livro obj WHERE categria_id = idCat ORDER BY title")
	List<Livro> findAllByCategoria(@Param(value = "idCat")Integer idCat);
	
	

}
