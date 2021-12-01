package br.com.games101.sheet.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Personagem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonView(ViewDetalhes.DetalheChild.class)
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

    
    private List<VantagemResponseDTO> listaVantagens;
    
    
    private List<RefugioResponseDTO> listaRefugios;

    private List<ItemPersonagemResponseDTO> listaItemPersonagem;
    
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
    								.listaItemPersonagem(ItemPersonagemResponseDTO.convertDTO(personagem.getListaItems()))
    								.build();
    	
    }
}
