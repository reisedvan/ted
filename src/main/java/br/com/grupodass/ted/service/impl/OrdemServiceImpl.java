package br.com.grupodass.ted.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import br.com.grupodass.ted.domain.entity.Ordem;
import br.com.grupodass.ted.domain.repository.OrdensRepository;
import br.com.grupodass.ted.exception.TedException;
import br.com.grupodass.ted.service.OrdemService;

@Service
public class OrdemServiceImpl implements OrdemService {

	private OrdensRepository repository;

	public OrdemServiceImpl(OrdensRepository repository) {
		this.repository = repository;
	}

	@Override
	public Ordem findById(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new TedException("Ordem não encontrada"));
	}

	@Override
	public void delete(Integer id) {
		repository.findById(id).map(ordem -> {
			repository.delete(ordem);
			return Void.TYPE;
		}).orElseThrow(() -> new TedException("Ordem não encontrada"));
	}

	@Override
	public Ordem save(Ordem ordem) {
		return repository.save(ordem);
	}

	@Override
	public void update(Integer id, Ordem newOrdem) {
		repository.findById(id).map(ordemExistente -> {
			newOrdem.setId(id);
			repository.save(newOrdem);
			return newOrdem;
		}).orElseThrow(() -> new TedException("Ordem não encontrada"));
	}

	@Override
	public List<Ordem> findWithFilter(Ordem filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
		return repository.findAll(Example.of(filtro, matcher));

	}

	@Override
	public void requestOrdem(Integer id) {
		Ordem ordem = repository.findById(id).orElseThrow(() -> new TedException("Ordem não encontrada"));

		ordem.setDataSave(new Timestamp(System.currentTimeMillis()));
		repository.save(ordem);

	}

}