package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.games101.sheet.entity.Cenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CenarioResponseDTO implements Serializable {


	private static final long serialVersionUID = 1L;

	private long id;

	private String nome;
	
	
	public static Page<CenarioResponseDTO> convertDTO(Page<Cenario> cenarios) {
		return  cenarios.map(cenario -> CenarioResponseDTO.convertDTO(cenario));
	}
	
    static public CenarioResponseDTO convertDTO(Cenario cenario){
    	CenarioResponseDTO cenarioDTO = CenarioResponseDTO.builder().build();
    	BeanUtils.copyProperties(cenario,cenarioDTO);
    	return cenarioDTO;
    }
}
