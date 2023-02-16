package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.entity.MelhoriaRefugio;
import br.com.games101.sheet.entity.Refugio;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefugioResponseDTO implements Serializable {

	private static final long serialVersionUID = -903428764195814238L;

	@JsonView(ViewDetalhes.DetalheChild.class)
	private long id;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String nome;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String local;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long defesa;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long tecnologia;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long espaco;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private List<MelhoriaRefugioResponseDTO> melhoriasRefugio;
	
	static public List<RefugioResponseDTO> convertDTO(List<Refugio> listaRefugio){
		return listaRefugio.stream().map(refugio -> RefugioResponseDTO.convertDTO(refugio)).collect(Collectors.toList());
	}
	
	static public List<RefugioResponseDTO> convertDTO(Set<Refugio> listaRefugio){
		return listaRefugio.stream().map(refugio -> RefugioResponseDTO.convertDTO(refugio)).collect(Collectors.toList());
	}
	
    static public RefugioResponseDTO convertDTO(Refugio refugio){
    	RefugioResponseDTO refugioDTO = RefugioResponseDTO.builder().build();
    	BeanUtils.copyProperties(refugio,refugioDTO);
    	refugioDTO.setMelhoriasRefugio(MelhoriaRefugioResponseDTO.convertDTO(refugio.getMelhoriasRefugio()));
    	return refugioDTO;
    }

}
