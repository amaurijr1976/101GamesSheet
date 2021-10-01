package br.com.games101.sheet.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.context.i18n.LocaleContextHolder;

public class Util {
	

	public String retornaDataAtualFormatado() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Calendar calendar = Calendar.getInstance(LocaleContextHolder.getLocale());
		return formatter.format(calendar.getTime());
	}
}
