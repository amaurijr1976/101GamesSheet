package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.games101.sheet.entity.Pericia;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PericiaResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String nome;
	
	private String descricao;
	
	private Long cenario;

	static public List<PericiaResponseDTO> convertDTO(List<Pericia> listaPericias){
		return listaPericias.stream().map(pericia -> PericiaResponseDTO.convertDTO(pericia)).collect(Collectors.toList());
	}

	public static PericiaResponseDTO convertDTO(Pericia pericia) {
		return PericiaResponseDTO.builder()
							  .id(pericia.getId())
							  .nome(pericia.getNome())
							  .descricao(pericia.getDescricao())
							  .cenario(pericia.getCenario().getId())
							  .build();
	}
}
