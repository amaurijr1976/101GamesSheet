package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.games101.sheet.dto.FeiticoRequestDTO;
import br.com.games101.sheet.dto.FeiticoResponseDTO;
import br.com.games101.sheet.entity.Feitico;

public interface FeiticoService {

	public List<FeiticoResponseDTO> listaFeiticos();

	public Optional<Feitico> buscaFeitico(Long id);

	public FeiticoResponseDTO incluiFeitico(@Valid FeiticoRequestDTO feiticoRequest);

	public List<FeiticoResponseDTO> incluiListaFeiticos(@Valid List<FeiticoRequestDTO> feiticoRequestLista);
	
	public FeiticoResponseDTO alterarFeitico(@Valid FeiticoRequestDTO feiticoRequest, long id);
	
	public void excluirFeitico(Long id);


}
