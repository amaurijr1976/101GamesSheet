package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.games101.sheet.dto.VantagemRequestDTO;
import br.com.games101.sheet.dto.VantagemResponseDTO;
import br.com.games101.sheet.entity.Vantagem;

public interface VantagemService {

	public List<VantagemResponseDTO> listaVantagens();

	public Optional<Vantagem> buscaVantagem(Long id);

	public VantagemResponseDTO incluiVantagem(@Valid VantagemRequestDTO vantagemRequest);
	
	public List<VantagemResponseDTO> incluiListaVantagens(@Valid List<VantagemRequestDTO> vantagemRequestLista);

	public VantagemResponseDTO alterarVantagem(@Valid VantagemRequestDTO vantagemRequest, long id);
	
	public void excluirVantagem(Long id);

}
