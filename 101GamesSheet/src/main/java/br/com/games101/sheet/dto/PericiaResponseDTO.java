package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
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
