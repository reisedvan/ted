package br.com.grupodass.ted.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "celula")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Celula {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String descricao;

	@Column(name = "setor")
	private String setor;

	@JsonIgnore
	@OneToMany(mappedBy = "celula")
	private Set<Ordem> ordens;

	public Celula(Integer id, String descricao, String setor) {
		this.id = id;
		this.descricao = descricao;
		this.setor = setor;
	}

	@Override
	public String toString() {
		return "Celula [id=" + id + ", descricao=" + descricao + ", setor=" + setor + "]";
	}

}
