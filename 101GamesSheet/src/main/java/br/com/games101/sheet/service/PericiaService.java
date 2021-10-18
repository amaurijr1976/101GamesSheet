package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.games101.sheet.dto.PericiaRequestDTO;
import br.com.games101.sheet.dto.PericiaResponseDTO;
import br.com.games101.sheet.entity.Pericia;

public interface PericiaService {

	public List<PericiaResponseDTO> listaPericias();

	public Optional<Pericia> buscaPericia(Long id);

	public PericiaResponseDTO incluiPericia(@Valid PericiaRequestDTO periciaRequest);

	public List<PericiaResponseDTO> incluiListaPericias(@Valid List<PericiaRequestDTO> periciaRequestLista);
	
	public PericiaResponseDTO alterarPericia(@Valid PericiaRequestDTO periciaRequest, long id);
	
	public void excluirPericia(Long id);


}
