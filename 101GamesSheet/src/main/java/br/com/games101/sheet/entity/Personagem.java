package br.com.games101.sheet.entity;

import java.util.Calendar;
import java.util.List;

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
    
    private Calendar data_criacao;
	
    private long experiencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cenario", nullable=false)
    private Cenario cenario;
    
    @ManyToMany()
    @JoinTable(name="tb_pericia_personagem",  joinColumns=
    {@JoinColumn(name="id_personagem")}, inverseJoinColumns=
      {@JoinColumn(name="id_pericia")})
    private List<Pericia> listaPericias;
    
    @ManyToMany()
    @JoinTable(name="tb_feitico_personagem",  joinColumns=
    {@JoinColumn(name="id_personagem")}, inverseJoinColumns=
      {@JoinColumn(name="id_feitico")})
    private List<Feitico> listaFeiticos;
    
    @ManyToMany()
    @JoinTable(name="tb_vantagem_personagem",  joinColumns=
    {@JoinColumn(name="id_personagem")}, inverseJoinColumns=
      {@JoinColumn(name="id_vantagem")})
    private List<Vantagem> listaVantagens;
    
	@OneToMany(mappedBy = "personagem",
			cascade=CascadeType.ALL,
			orphanRemoval = true)
	private List<PersonagemItems> listaItems;
    
    @ManyToMany()
    @JoinTable(name="tb_refugio_personagem",  joinColumns=
    {@JoinColumn(name="id_personagem")}, inverseJoinColumns=
      {@JoinColumn(name="id_refugio")})
    private List<Refugio> listaRefugios;
    

	public static Personagem retornaEntity(@Valid PersonagemRequestDTO personagemRequest) {
    	Personagem personagem = Personagem.builder().build();
    	BeanUtils.copyProperties(personagemRequest,personagem);
    	return personagem;
	}
    
}
