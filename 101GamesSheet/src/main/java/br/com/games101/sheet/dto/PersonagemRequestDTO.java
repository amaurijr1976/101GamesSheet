package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.entity.Item;
import br.com.games101.sheet.entity.Pericia;
import br.com.games101.sheet.entity.PersonagemItems;
import br.com.games101.sheet.entity.Refugio;
import br.com.games101.sheet.entity.Vantagem;
import br.com.games101.sheet.validation.CenarioExistAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonagemRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 3625439735368602080L;

	@NotBlank @Length(min = 3, max = 45, message = "Tamanho deve ser entre 3 e 45 caracteres.")
	private String nome;

	@NotBlank @Length(min = 3, max = 45, message = "Tamanho deve ser entre 3 e 45 caracteres.")
    private String raca;
    
	@NotBlank @Length(min = 3, max = 45, message = "Tamanho deve ser entre 3 e 45 caracteres.")
	private String jogador;
    
	@Positive
	private long corpo;
    
	@Positive
	private long saude;
    
	@Positive
	private long espirito;
    
	@Positive
	private long postura;
    
	@Positive
	private long mente;
    
	@Positive
	private long vontade;
    	
	@PositiveOrZero
	private long insanidade;
	
	@PositiveOrZero
	private long fome;

	@NotBlank @Length(max = 4500, message = "Tamanho deve até 4500 caracteres.")
	private String historia;
    
    private Calendar data_criacao;
	
    @PositiveOrZero
    private long experiencia;
    
	@Positive(message = "Convenio deve ser um numero positivo")
	@CenarioExistAnnotation
	private Long cenario;
	
    private List<Pericia> listaPericias;
    

    private List<Feitico> listaFeiticos;
    

    private List<Vantagem> listaVantagens;
    

    private List<PersonagemItems> listaItens;
    

    private List<Refugio> listaRefugios;
}
