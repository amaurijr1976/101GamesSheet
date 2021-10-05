package br.com.games101.sheet.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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

import br.com.games101.sheet.dto.RefugioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_refugio")
public class Refugio implements Serializable {

	private static final long serialVersionUID = -5301746954470803456L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String local;
	
	private long defesa;
	
	private long tecnologia;
	
	private long espaco;
	
    @ManyToMany()
    @JoinTable(name="tb_melhoria_refugio",  joinColumns=
    {@JoinColumn(name="id_refugio")}, inverseJoinColumns=
      {@JoinColumn(name="id_melhoria")})
	private List<MelhoriaRefugio> melhoriasRefugio;
	
	static public Refugio retornaEntity(RefugioRequestDTO requestRefugio) {
		return Refugio.builder()
				 .nome(requestRefugio.getNome())
				 .defesa(requestRefugio.getDefesa())
				 .local(requestRefugio.getLocal())
				 .espaco(requestRefugio.getEspaco())
				 .tecnologia(requestRefugio.getTecnologia())
				 .melhoriasRefugio(requestRefugio.getListaMelhoriaRefugio())
				 .build();
	}

}
