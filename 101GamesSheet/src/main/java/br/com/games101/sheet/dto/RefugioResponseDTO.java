package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.MelhoriaRefugio;
import br.com.games101.sheet.entity.Refugio;
import br.com.games101.sheet.entity.Vantagem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefugioResponseDTO implements Serializable {

	private static final long serialVersionUID = -903428764195814238L;

	private long id;
	
	private String nome;
	
	private String local;
	
	private long defesa;
	
	private long tecnologia;
	
	private long espaco;
	
	private List<MelhoriaRefugio> melhoriasRefugio;
	
	static public List<RefugioResponseDTO> convertDTO(List<Refugio> listaRefugio){
		return listaRefugio.stream().map(refugio -> RefugioResponseDTO.convertDTO(refugio)).collect(Collectors.toList());
	}
	
    static public RefugioResponseDTO convertDTO(Refugio refugio){
    	RefugioResponseDTO refugioDTO = RefugioResponseDTO.builder().build();
    	BeanUtils.copyProperties(refugio,refugioDTO);
    	return refugioDTO;
    }

}
