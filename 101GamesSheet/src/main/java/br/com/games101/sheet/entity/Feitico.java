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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tb_magia_poderes")
public class Feitico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String descricao;
	
	private String tipo;
	
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="cenario", nullable=false)
   private Cenario cenario;
	
	static public Feitico retornaEntity(FeiticoRequestDTO request,Optional<Cenario> cenario) {
			return Feitico.builder()
						 .nome(request.getNome())
						 .tipo(request.getTipo())
						 .descricao(request.getDescricao())
						 .cenario(cenario.get())
						 .build();
	}
	
}
