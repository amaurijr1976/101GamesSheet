package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.entity.Pericia;
import br.com.games101.sheet.entity.Personagem;
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
    
    private Date data_criacao;
	
    private long experiencia;
    
    private long recursos;
    
    private Cenario cenario;
    
    private List<PericiaResponseDTO> listaPericias;
    

    private List<FeiticoResponseDTO> listaFeiticos;

    
    private Set<VantagemResponseDTO> listaVantagens;
    
    
    private Set<RefugioResponseDTO> listaRefugios;
//
//    
//    private Set<ItemPersonagemResponseDTO> listaItemPersonagem;
    
    static public List<PersonagemResponseDTO> convertDTO(List<Personagem> listaPersonagem){
    	return listaPersonagem.stream().map(personagem -> PersonagemResponseDTO.convertDTO(personagem)).collect(Collectors.toList());
    }
    
    static public PersonagemResponseDTO convertDTO(Personagem personagem){
    	return PersonagemResponseDTO.builder()
    								.id(personagem.getId())
    								.jogador(personagem.getJogador())
    								.nome(personagem.getNome())
    								.corpo(personagem.getCorpo())
    								.saude(personagem.getSaude())
    								.espirito(personagem.getEspirito())
    								.postura(personagem.getPostura())
    								.vontade(personagem.getVontade())
    								.mente(personagem.getMente())
    								.insanidade(personagem.getInsanidade())
    								.fome(personagem.getFome())
    								.historia(personagem.getHistoria())
    								.experiencia(personagem.getExperiencia())
    								.recursos(personagem.getRecursos())
    								.raca(personagem.getRaca())    								
    								.cenario(personagem.getCenario())
    								.data_criacao(Date.valueOf(LocalDate.now()))
    								.listaPericias(PericiaResponseDTO.convertDTO(personagem.getListaPericias()))
    								.listaFeiticos(FeiticoResponseDTO.convertDTO(personagem.getListaFeiticos()))
    								.listaVantagens(VantagemResponseDTO.convertDTO(personagem.getListaVantagens()))
    								.listaRefugios(RefugioResponseDTO.convertDTO(personagem.getListaRefugios()))
    								.build();
    	
    }
}
