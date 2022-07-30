package br.com.grupodass.ted.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.grupodass.ted.domain.entity.Ordem;




public interface OrdensRepository extends JpaRepository<Ordem, Integer> {

	
	
	@Query(value =" select * from ordem where celula_id = ':id' and data_save is not null", nativeQuery = true)
	Optional<Ordem> findSaveByCelulaId(int  id);


	
}
