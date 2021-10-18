package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.MelhoriaRefugio;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MelhoriaRefugioResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private long id;
	
	private String nome;
	
	private String descricao;
	
	private long custo_tecnologia;
	
	private long custo_espaco;
	
	private long bonus_defesa;

	private Cenario cenario;

	static public List<MelhoriaRefugioResponseDTO> convertDTO(List<MelhoriaRefugio> listaMelhoriasRefugio){
		return listaMelhoriasRefugio.stream().map(melhoriaRefugio -> MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugio)).collect(Collectors.toList());
	}

    static public MelhoriaRefugioResponseDTO convertDTO(MelhoriaRefugio melhoriaRefugio){
    	MelhoriaRefugioResponseDTO melhoriaRefugioDTO = MelhoriaRefugioResponseDTO.builder().build();
    	BeanUtils.copyProperties(melhoriaRefugio,melhoriaRefugioDTO);
    	return melhoriaRefugioDTO;
    }
}
