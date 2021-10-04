package br.com.games101.sheet.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.games101.sheet.dto.CenarioRequestDTO;
import br.com.games101.sheet.dto.CenarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tb_ficha")
public class Cenario {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;

	@OneToMany(mappedBy = "cenario",
	           fetch = FetchType.LAZY)
	private List<Item> items;
	
	@OneToMany(mappedBy = "cenario",
	           fetch = FetchType.LAZY)
	private List<Feitico> feiticos;
	
	@OneToMany(mappedBy = "cenario",
	           fetch = FetchType.LAZY)
	private List<Vantagem> vantagens;
	
	@OneToMany(mappedBy = "cenario",
	           fetch = FetchType.LAZY)
	private List<Pericia> pericias;
	
	static public Cenario retornaEntity(CenarioResponseDTO response) {
		return Cenario.builder()
					  .id(response.getId())
					  .nome(response.getNome())
					  .build();
	}
	
	static public Cenario retornaEntity(CenarioRequestDTO request) {
		return Cenario.builder().nome(request.getNome()).build();
	}
	
}
