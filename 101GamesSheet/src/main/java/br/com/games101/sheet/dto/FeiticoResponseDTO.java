package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Feitico;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FeiticoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long id;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String nome;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String descricao;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String tipo;
	
	@JsonView(ViewDetalhes.DetalheCompleto.class)
	private Cenario cenario;

	static public List<FeiticoResponseDTO> convertDTO(Set<Feitico> listaItens){
		return listaItens.stream().map(feitico -> FeiticoResponseDTO.convertDTO(feitico)).collect(Collectors.toList());
	}
	
	static public List<FeiticoResponseDTO> convertDTO(List<Feitico> listaItens){
		return listaItens.stream().map(feitico -> FeiticoResponseDTO.convertDTO(feitico)).collect(Collectors.toList());
	}

    static public FeiticoResponseDTO convertDTO(Feitico feitico){
    	FeiticoResponseDTO feiticoDTO = FeiticoResponseDTO.builder().build();
    	BeanUtils.copyProperties(feitico,feiticoDTO);
    	return feiticoDTO;
    }
}
