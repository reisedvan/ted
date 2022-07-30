package br.com.grupodass.ted.domain.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ordem")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ordem {
	@Id
	@Column(name = "ordemNum")
	protected Integer id;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "processo")
	private String processo;

	@ManyToOne
	@JoinColumn(name = "celula_id")
	private Celula celula;

	@Column(name = "produto")
	private String produto;

	@Column(name = "sequencia")
	private Integer sequencia;

	@Column(name = "inicioProducao")
	private LocalDate inicioProducao;

	@Column(name = "documento")
	private String documento;

	@Column(name = "setorPendencia")
	private String setorPendencia;

	@Column(name = "entrega")
	private LocalDate entrega;

	@Column(name = "familia")
	private long familia;

	@Column(name = "tempo")
	private Double tempo;

	@Column(name = "marca")
	private Integer marca;

	@Column(name = "dataSave", nullable = true)
	private Timestamp dataSave;

	public Double getProductionTime(Integer qtdFuncionarios, Double eficiencia, Integer minJornada) {
		Integer tempoDisponivel = minJornada * qtdFuncionarios;
		Double tempoOrdem = tempo*quantidade;
		Double capacidade = tempoOrdem/tempoDisponivel;
		eficiencia = eficiencia > 1 ? eficiencia / 100 : eficiencia;
		return capacidade * eficiencia;

	}

	public Ordem(Integer id, Integer quantidade, String processo, Celula celula, String produto, Integer sequencia,
			String inicioProducao, String documento, String setorPendencia, String entrega, long familia, Double tempo,
			Integer marca) {

		this.id = id;
		this.quantidade = quantidade;
		this.processo = processo;
		this.celula = celula;
		this.produto = produto;
		this.sequencia = sequencia;
		this.inicioProducao = toLocalDate(inicioProducao);
		this.documento = documento;
		this.setorPendencia = setorPendencia;
		this.entrega = toLocalDate(entrega);
		this.familia = familia;
		this.tempo = tempo;
		this.marca = marca;
	}

	
	
	
	private LocalDate toLocalDate(String date) {
		int year=0;
		int month=0;
		int day =0;
		if (date.contains("-")) {
			if (date.split("-")[0].length() > 2) {
				 year = Integer.parseInt(date.split("-")[0]);
				 month = Integer.parseInt(date.split("-")[1]);
				 day = Integer.parseInt(date.split("-")[2]);
			}else {
				 year = Integer.parseInt(date.split("-")[2]);
				 month = Integer.parseInt(date.split("-")[1]);
				 day = Integer.parseInt(date.split("-")[0]);
			}
		}else if(date.contains("/")) {
			if (date.split("/")[0].length() > 2) {
				 year = Integer.parseInt(date.split("/")[0]);
				 month = Integer.parseInt(date.split("/")[1]);
				 day = Integer.parseInt(date.split("/")[2]);
			}else {
				 year = Integer.parseInt(date.split("/")[2]);
				 month = Integer.parseInt(date.split("/")[1]);
				 day = Integer.parseInt(date.split("/")[0]);
			}
		}
		
		return  year>0 ? LocalDate.of(year, month, day) : LocalDate.now();
	}

	@Override
	public String toString() {
		return "Ordem [ordem=" + id + ", quantidade=" + quantidade + ", processo=" + processo + ", celula=" + celula
				+ ", produto=" + produto + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordem other = (Ordem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
