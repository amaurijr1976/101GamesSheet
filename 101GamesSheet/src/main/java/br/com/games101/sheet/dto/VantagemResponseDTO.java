package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Vantagem;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VantagemResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	private String descricao;
	private Cenario cenario;

	static public Set<VantagemResponseDTO> convertDTO(Set<Vantagem> listaVantagens){
		return listaVantagens.stream().map(vantagem -> VantagemResponseDTO.convertDTO(vantagem)).collect(Collectors.toSet());
	}
	
	static public List<VantagemResponseDTO> convertDTO(List<Vantagem> listaVantagens){
		return listaVantagens.stream().map(vantagem -> VantagemResponseDTO.convertDTO(vantagem)).collect(Collectors.toList());
	}

    static public VantagemResponseDTO convertDTO(Vantagem vantagem){
    	VantagemResponseDTO vantagemDTO = VantagemResponseDTO.builder().build();
    	BeanUtils.copyProperties(vantagem,vantagemDTO);
    	return vantagemDTO;
    }
}
