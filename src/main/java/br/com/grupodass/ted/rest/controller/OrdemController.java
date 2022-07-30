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

import br.com.grupodass.ted.domain.entity.Ordem;
import br.com.grupodass.ted.service.OrdemService;

@RestController
@RequestMapping("api/ordens")
public class OrdemController {

	private OrdemService service;

	public OrdemController( OrdemService service) {
		this.service = service;
	}

	@GetMapping("/get/{id}")
	public Ordem findById(@PathVariable("id") Integer id) {
		return service.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
	}

	@PostMapping("/storage")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody List<Ordem> ordens) {
		ordens.forEach(ordem->service.save(ordem));		
	}

	

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Integer id, @RequestBody Ordem newOrdem) {
		service.update(id, newOrdem);
	}
	
	@PutMapping("/request/{id}")
	public void requestOrdem(@PathVariable("id") Integer ordem) {
		service.requestOrdem(ordem);
	}

	@GetMapping
	public List<Ordem> findWithFilter(@RequestBody Ordem filtro) {
		return service.findWithFilter(filtro);
	}

}
