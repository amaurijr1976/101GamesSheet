package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Vantagem;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VantagemResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long id;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String nome;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String descricao;
		
	@JsonView(ViewDetalhes.DetalheCompleto.class)
	private Cenario cenario;

	static public List<VantagemResponseDTO> convertDTO(Set<Vantagem> listaVantagens){
		return listaVantagens.stream().map(vantagem -> VantagemResponseDTO.convertDTO(vantagem)).collect(Collectors.toList());
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
