package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.games101.sheet.dto.MelhoriaRefugioRequestDTO;
import br.com.games101.sheet.dto.MelhoriaRefugioResponseDTO;
import br.com.games101.sheet.entity.MelhoriaRefugio;

public interface MelhoriaRefugioService {

	public List<MelhoriaRefugioResponseDTO> listaMelhoriaRefugio();

	public Optional<MelhoriaRefugio> buscaMelhoriaRefugio(Long id);

	public MelhoriaRefugioResponseDTO incluiMelhoriaRefugio(@Valid MelhoriaRefugioRequestDTO melhoriaRefugioRequest);

	public List<MelhoriaRefugioResponseDTO> incluiListaMelhoriaRefugio(@Valid List<MelhoriaRefugioRequestDTO> melhoriaRefugioRequestLista);
	
	public MelhoriaRefugioResponseDTO alterarMelhoriaRefugio(@Valid MelhoriaRefugioRequestDTO melhoriaRefugioRequest, long id);
	
	public void excluirMelhoriaRefugio(Long id);


}
