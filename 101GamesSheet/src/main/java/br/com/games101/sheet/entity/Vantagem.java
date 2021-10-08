package br.com.games101.sheet.entity;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.games101.sheet.dto.VantagemRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="tb_vantagem")
public class Vantagem implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String descricao;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cenario", nullable=false)
    private Cenario cenario;
	
	static public Vantagem retornaEntity(VantagemRequestDTO request,Optional<Cenario> cenario) {
			return Vantagem.builder()
						 .nome(request.getNome())
						 .descricao(request.getDescricao())
						 .cenario(cenario.get())
						 .build();
	}
	
}
