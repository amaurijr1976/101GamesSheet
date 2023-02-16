package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Pericia;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PericiaResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long id;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String nome;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String descricao;
	
	@JsonView(ViewDetalhes.DetalheCompleto.class)
	private Cenario cenario;

	static public List<PericiaResponseDTO> convertDTO(List<Pericia> listaPericias){
		return listaPericias.stream().map(pericia -> PericiaResponseDTO.convertDTO(pericia)).collect(Collectors.toList());
	}
	
	static public List<PericiaResponseDTO> convertDTO(Set<Pericia> listaPericias){
		return listaPericias.stream().map(pericia -> PericiaResponseDTO.convertDTO(pericia)).collect(Collectors.toList());
	}

    static public PericiaResponseDTO convertDTO(Pericia pericia){
    	PericiaResponseDTO periciaDTO = PericiaResponseDTO.builder().build();
    	BeanUtils.copyProperties(pericia,periciaDTO);
    	return periciaDTO;
    }
}
