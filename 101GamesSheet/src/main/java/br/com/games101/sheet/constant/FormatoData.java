package br.com.games101.sheet.constant;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormatoData {

    FORMATTERDATAERRO("dd-MM-yyyy hh:mm:ss"),
	FORMATTERDATAERROSIMPLES("dd-MM-yyyy");
	
	private final String label;

	
	private static final Map<String, FormatoData> DADOS = new HashMap<String, FormatoData>();
    
    static {
        for (FormatoData e: values()) {
            DADOS.put(e.label, e);
        }
    }

	public static FormatoData valueOfLabel(String label) {
        return DADOS.get(label);
    }
}
