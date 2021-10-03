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
		return listaVantagens.stream().map(vantagem -> VantagemResponseDTO.convertDTO(vantagem)).collect(Collectors.toList());
	}

	public static VantagemResponseDTO convertDTO(Vantagem vantagem) {
		return VantagemResponseDTO.builder()
							  .id(vantagem.getId())
							  .nome(vantagem.getNome())
							  .tipo(vantagem.getTipo())
							  .descricao(vantagem.getDescricao())
							  .cenario(vantagem.getCenario().getId())
							  .build();
	}
}
