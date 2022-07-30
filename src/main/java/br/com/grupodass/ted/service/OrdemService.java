package br.com.grupodass.ted.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.grupodass.ted.domain.entity.Ordem;

@Service
public interface OrdemService {

	public Ordem findById(int id);

	public void delete(Integer id);

	public Ordem save(Ordem ordem);

	public void update(Integer id, Ordem newOrdem);

	public List<Ordem> findWithFilter(Ordem filtro);

	public void requestOrdem(Integer id);

}
