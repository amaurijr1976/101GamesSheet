package br.com.games101.sheet.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;

import br.com.games101.sheet.dto.PersonagemRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_personagem")
public class Personagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne()
    @JoinColumn(name="cenario", nullable=false)
    private Cenario cenario;
    
    @OneToMany()
    @JoinTable(name = "tb_pericia_personagem", 
    		  joinColumns = @JoinColumn(name = "id_personagem"), 
    		  inverseJoinColumns = @JoinColumn(name = "id_pericia"))
    private Set<Pericia> listaPericias;
    
    @OneToMany()
    @JoinTable(name="tb_magia_personagem",  
    			joinColumns=@JoinColumn(name="id_personagem"),
    			inverseJoinColumns= @JoinColumn(name="id_magia"))
    private Set<Feitico> listaFeiticos;
    
    @OneToMany()
    @JoinTable(name="tb_vantagem_personagem", 
    			joinColumns= {@JoinColumn(name="id_personagem")},
    			inverseJoinColumns= {@JoinColumn(name="id_vantagem")})
    private Set<Vantagem> listaVantagens;

    @OneToMany()
    @JoinTable(name="tb_refugio_personagem",
    			joinColumns={@JoinColumn(name="id_personagem")},
    			inverseJoinColumns={@JoinColumn(name="id_refugio")})
    private Set<Refugio> listaRefugios;
    
    
	@OneToMany(mappedBy = "personagem",fetch = FetchType.EAGER)
	private Set<PersonagemItems> listaItems;
  
    

	public static Personagem retornaEntity(@Valid PersonagemRequestDTO personagemRequest,Optional<Cenario> cenario) {
    	Personagem personagem = Personagem.builder().build();
    	BeanUtils.copyProperties(personagemRequest,personagem,"data_criacao");
    	personagem.setData_criacao(Date.valueOf(LocalDate.now()));
    	personagem.setCenario(cenario.get());
    	personagem.setListaFeiticos(new HashSet<Feitico>());
    	personagem.setListaItems(new HashSet<PersonagemItems>());
    	personagem.setListaPericias(new HashSet<Pericia>());
    	personagem.setListaRefugios(new HashSet<Refugio>());
    	personagem.setListaVantagens(new HashSet<Vantagem>());
    	return personagem;
	}
    
}
