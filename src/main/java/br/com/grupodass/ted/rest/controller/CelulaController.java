package br.com.grupodass.ted.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupodass.ted.domain.entity.Celula;
import br.com.grupodass.ted.domain.entity.Ordem;
import br.com.grupodass.ted.rest.dto.CelulaDTO;
import br.com.grupodass.ted.service.CelulaService;

@RestController
@RequestMapping("api/celulas")
public class CelulaController {

	private CelulaService service;
	public CelulaController(CelulaService service) {	
		this.service=service;
	}

	@GetMapping("/get/{id}")
	public Celula find(@PathVariable("id") int id) {
		return service.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		service.delete(id);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Celula save(@RequestBody Celula Celula) {
		return service.save(Celula);

	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Celula newCelula) {
		service.update(id, newCelula);
	}

	@GetMapping
	public List<Celula> findWithFilter( Celula filtro) {
		return service.findWithFilter(filtro);
	}

	
	
	@GetMapping("/tempo")
	public Double getProductionTime(@RequestBody CelulaDTO dto) {
		return service.getLeadTime(dto);
		
	}
	
	@GetMapping("/requestList/{id}")
	public List<Ordem>findRequests(@PathVariable("id") Integer id) {
		return service.findRequests(id);
	}
	
	@GetMapping("/ordensList/{id}")
	public List<Ordem>findOrdens(@PathVariable("id") Integer id) {
		return service.findOrdens(id);
	}
}
