package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
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
