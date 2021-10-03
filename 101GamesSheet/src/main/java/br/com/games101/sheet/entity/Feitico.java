package br.com.games101.sheet.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.games101.sheet.dto.FeiticoRequestDTO;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity(name="tb_feitico")
public class Feitico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String tipo;
	
	private String descricao;
	
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="ficha", nullable=false)
   private Cenario cenario;
	
	static public Feitico retornaEntity(FeiticoRequestDTO request,Optional<Cenario> cenario) {
			return Feitico.builder()
						 .nome(request.getNome())
						 .descricao(request.getDescricao())
						 .tipo(request.getTipo())
						 .cenario(cenario.get())
						 .build();
	}
	
}
