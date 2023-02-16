package br.com.games101.sheet.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.i18n.LocaleContextHolder;

public class Util {
	

	public String retornaDataAtualFormatado(String formatoData) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoData, LocaleContextHolder.getLocale());
		return LocalDate.now().format(formatter);
	}
}
