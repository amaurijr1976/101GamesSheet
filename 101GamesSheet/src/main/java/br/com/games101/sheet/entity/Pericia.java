package br.com.games101.sheet.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.games101.sheet.dto.PericiaRequestDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name ="tb_pericia")
public class Pericia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String tipo;
	
	private String descricao;
	
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="ficha", nullable=false)
   private Cenario cenario;
	
	static public Pericia retornaEntity(PericiaRequestDTO request,Optional<Cenario> cenario) {
			return Pericia.builder()
						 .nome(request.getNome())
						 .descricao(request.getDescricao())
						 .tipo(request.getTipo())
						 .cenario(cenario.get())
						 .build();
	}
	
}
