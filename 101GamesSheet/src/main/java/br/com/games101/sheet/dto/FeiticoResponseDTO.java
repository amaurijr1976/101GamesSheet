package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.games101.sheet.entity.Feitico;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FeiticoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	private String descricao;
	private String tipo;
	private long cenario;

	static public List<FeiticoResponseDTO> convertDTO(List<Feitico> listaItens){
		return listaItens.stream().map(feitico -> FeiticoResponseDTO.convertDTO(feitico)).collect(Collectors.toList());
	}

	public static FeiticoResponseDTO convertDTO(Feitico feitico) {
		return FeiticoResponseDTO.builder()
							  .id(feitico.getId())
							  .nome(feitico.getNome())
							  .tipo(feitico.getTipo())
							  .descricao(feitico.getDescricao())
							  .cenario(feitico.getCenario().getId())
							  .build();
	}
}
