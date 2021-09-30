package br.com.games101.sheet.config.validation;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroFormDTO {
	
	private String campo;
	private String erro;
	private LocalDate date;

}
