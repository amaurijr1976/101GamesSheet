package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.entity.Item;
import br.com.games101.sheet.entity.Pericia;
import br.com.games101.sheet.entity.Personagem;
import br.com.games101.sheet.entity.PersonagemItems;
import br.com.games101.sheet.entity.Refugio;
import br.com.games101.sheet.entity.Vantagem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonagemResponseDTO implements Serializable {

	private static final long serialVersionUID = -8985353880999800249L;
	
	private long id;

	private String nome;

    private String raca;
    
	private String jogador;
    
	private long corpo;
    
	private long saude;
    
	private long espirito;
    
	private long postura;
    
	private long mente;
    
	private long vontade;
    	
	private long insanidade;
	
	private long fome;

	private String historia;
    
    private Calendar data_criacao;
	
    private long experiencia;
    
    private Cenario cenario;
    
    private List<Pericia> listaPericias;
    

    private List<Feitico> listaFeiticos;
    

    private List<Vantagem> listaVantagens;
    

    private List<PersonagemItems> listaItens;
    

    private List<Refugio> listaRefugios;
    
    static public List<PersonagemResponseDTO> convertDTO(List<Personagem> listaPersonagem){
    	return listaPersonagem.stream().map(personagem -> PersonagemResponseDTO.convertDTO(personagem)).collect(Collectors.toList());
    }
    
    static public PersonagemResponseDTO convertDTO(Personagem personagem){
    	PersonagemResponseDTO personagemDTO = PersonagemResponseDTO.builder().build();
    	BeanUtils.copyProperties(personagem,personagemDTO);
    	return personagemDTO;
    }

}
