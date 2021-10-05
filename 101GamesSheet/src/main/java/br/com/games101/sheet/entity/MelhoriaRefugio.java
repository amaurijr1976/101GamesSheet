package br.com.games101.sheet.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.games101.sheet.dto.MelhoriaRefugioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_melhorias_refugio")
public class MelhoriaRefugio implements Serializable {

	private static final long serialVersionUID = -6312747277822146165L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String descricao;
	
	private long custo_tecnologia;
	
	private long custo_espaco;
	
	private long bonus_defesa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cenario", nullable=true)
	private Cenario cenario;

	static public MelhoriaRefugio retornaEntity(MelhoriaRefugioRequestDTO requestMelhoriaRefugio,Optional<Cenario> cenario) {
		return MelhoriaRefugio.builder()
				 .nome(requestMelhoriaRefugio.getNome())
				 .descricao(requestMelhoriaRefugio.getDescricao())
				 .bonus_defesa(requestMelhoriaRefugio.getBonus_defesa())
				 .custo_espaco(requestMelhoriaRefugio.getCusto_espaco())
				 .custo_tecnologia(requestMelhoriaRefugio.getCusto_tecnologia())
				 .cenario(cenario.get())
				 .build();
	}
	
}
