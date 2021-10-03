package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.games101.sheet.entity.Vantagem;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VantagemResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	private String tipo;
	private String descricao;
	private Long cenario;

	static public List<VantagemResponseDTO> convertDTO(List<Vantagem> listaVantagens){
		return listaVantagems.stream().map(pericia -> VantagemResponseDTO.convertDTO(pericia)).collect(Collectors.toList());
	}

	public static VantagemResponseDTO convertDTO(Vantagem vamtagem) {
		return VantagemResponseDTO.builder()
							  .id(pericia.getId())
							  .nome(pericia.getNome())
							  .tipo(pericia.getTipo())
							  .descricao(pericia.getDescricao())
							  .cenario(pericia.getCenario().getId())
							  .build();
	}
}
