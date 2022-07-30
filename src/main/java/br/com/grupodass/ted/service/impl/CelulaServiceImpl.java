package br.com.grupodass.ted.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.grupodass.ted.domain.entity.Celula;
import br.com.grupodass.ted.domain.entity.Ordem;
import br.com.grupodass.ted.domain.repository.CelulasRepository;
import br.com.grupodass.ted.rest.dto.CelulaDTO;
import br.com.grupodass.ted.service.CelulaService;

@Service
public class CelulaServiceImpl implements CelulaService {

	private CelulasRepository repository;

	public CelulaServiceImpl(CelulasRepository repository) {
		this.repository = repository;
	}

	@Override
	public Celula save(Celula celula) {
		return repository.save(celula);
	}

	@Override
	@Transactional
	public void update(Integer id, Celula newCelula) {
		repository.findById(id).map(celulaExistente -> {
			newCelula.setId(id);
			repository.save(newCelula);
			return newCelula;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Celula não encontrada"));

	}

	@Override
	@Transactional
	public void delete(Integer id) {
		repository.findById(id).map(Celula -> {
			repository.delete(Celula);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Celula não encontrada"));
	}

	@Override
	public Celula findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Celula não encontrada"));
	}

	@Override
	public List<Celula> findWithFilter(Celula filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		return repository.findAll(Example.of(filtro, matcher));

	}
	
	@Override
	public Double getLeadTime(CelulaDTO dto) {
		return findRequests(dto.getId()).stream()
				.map(x -> x.getProductionTime(dto.getFuncionarios(), dto.getEficiencia(), dto.getJornada()))
				.reduce(0.0, Double::sum);

	}

	@Override
	public List<Ordem> findRequests(Integer id) {
		Celula celula = repository.findCelulaFetchOrdens(id);
		List<Ordem> x = celula.getOrdens().stream().filter(ordem -> ordem.getDataSave() != null).toList();
		
		return x;
	}

	@Override
	public List<Ordem> findOrdens(Integer id) {
		Celula celula = repository.findCelulaFetchOrdens(id);
		List<Ordem> x =  celula.getOrdens().stream().toList();

		return x;
	}
	
}
