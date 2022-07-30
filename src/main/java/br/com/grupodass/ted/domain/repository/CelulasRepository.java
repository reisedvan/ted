package br.com.grupodass.ted.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.grupodass.ted.domain.entity.Celula;

public interface CelulasRepository extends JpaRepository<Celula, Integer> {

	
	
	@Query("select c from Celula c left join fetch c.ordens o where c.id = :id")
	Celula findCelulaFetchOrdens(@Param("id") Integer id);
	
	
	
	
	List<Celula> findBySetor(String setor);
	
	
}
