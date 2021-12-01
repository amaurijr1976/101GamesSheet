package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.MelhoriaRefugio;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MelhoriaRefugioResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	@JsonView(ViewDetalhes.DetalheChild.class)
	private long id;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String nome;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private String descricao;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long custo_tecnologia;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long custo_espaco;
	
	@JsonView(ViewDetalhes.DetalheChild.class)
	private long bonus_defesa;

	@JsonView(ViewDetalhes.DetalheCompleto.class)
	private Cenario cenario;

	static public List<MelhoriaRefugioResponseDTO> convertDTO(List<MelhoriaRefugio> listaMelhoriasRefugio){
		return listaMelhoriasRefugio.stream().map(melhoriaRefugio -> MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugio)).collect(Collectors.toList());
	}
	

	static public List<MelhoriaRefugioResponseDTO> convertDTO(Set<MelhoriaRefugio> listaMelhoriasRefugio){
		return listaMelhoriasRefugio.stream().map(melhoriaRefugio -> MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugio)).collect(Collectors.toList());
	}


    static public MelhoriaRefugioResponseDTO convertDTO(MelhoriaRefugio melhoriaRefugio){
    	MelhoriaRefugioResponseDTO melhoriaRefugioDTO = MelhoriaRefugioResponseDTO.builder().build();
    	BeanUtils.copyProperties(melhoriaRefugio,melhoriaRefugioDTO);
    	return melhoriaRefugioDTO;
    }
}
