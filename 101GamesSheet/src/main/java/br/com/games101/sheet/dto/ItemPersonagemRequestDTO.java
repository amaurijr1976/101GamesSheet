package br.com.games101.sheet.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemPersonagemRequestDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1082957014360129147L;
	
	private long idItem;
	
	private long quantidade;

}
