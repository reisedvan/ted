package br.com.grupodass.ted.service
;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.grupodass.ted.domain.entity.Celula;
import br.com.grupodass.ted.domain.entity.Ordem;
import br.com.grupodass.ted.rest.dto.CelulaDTO;



@Service
public interface CelulaService  {	
	
	public Celula save(Celula celula);

	public void update(Integer id, Celula newCelula);

	public void delete(Integer id);

	public Celula findById(Integer id);

	public List<Celula> findWithFilter(Celula filtro);

	public List<Ordem> findRequests(Integer id);

	public List<Ordem> findOrdens(Integer id);
	
	public Double getLeadTime(CelulaDTO dto);
	
}
