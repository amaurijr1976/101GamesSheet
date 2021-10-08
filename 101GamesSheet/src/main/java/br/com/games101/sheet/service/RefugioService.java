package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.games101.sheet.dto.RefugioRequestDTO;
import br.com.games101.sheet.dto.RefugioResponseDTO;
import br.com.games101.sheet.entity.Refugio;

public interface RefugioService {

	public List<RefugioResponseDTO> listaRefugios();

	public Optional<Refugio> buscaRefugio(Long id);

	public RefugioResponseDTO incluiRefugio(@Valid RefugioRequestDTO refugioRequest);

	public RefugioResponseDTO alterarRefugio(@Valid RefugioRequestDTO refugioRequest, long id);
	
	public void excluirRefugio(Long id);
	
}
