package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	private Long cenario;

	static public List<MelhoriaRefugioResponseDTO> convertDTO(List<MelhoriaRefugio> listaMelhoriasRefugio){
		return listaMelhoriasRefugio.stream().map(melhoriaRefugio -> MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugio)).collect(Collectors.toList());
	}

	public static MelhoriaRefugioResponseDTO convertDTO(MelhoriaRefugio melhoriaRefugio) {
		return MelhoriaRefugioResponseDTO.builder()
							  .id(melhoriaRefugio.getId())
							  .nome(melhoriaRefugio.getNome())
							  .descricao(melhoriaRefugio.getDescricao())
							  .custo_espaco(melhoriaRefugio.getCusto_espaco())
							  .custo_tecnologia(melhoriaRefugio.getCusto_tecnologia())
							  .bonus_defesa(melhoriaRefugio.getBonus_defesa())
							  .cenario(melhoriaRefugio.getCenario().getId())
							  .build();
	}
}
