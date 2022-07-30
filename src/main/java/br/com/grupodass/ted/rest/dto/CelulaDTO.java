package br.com.grupodass.ted.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CelulaDTO {
	
	private Integer id;
	private Integer funcionarios;
	private Integer jornada;
	private Double eficiencia;
	
	
}
