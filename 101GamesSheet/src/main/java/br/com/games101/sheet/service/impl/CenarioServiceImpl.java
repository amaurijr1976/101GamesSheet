package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.CenarioRequestDTO;
import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.FeiticoRequestDTO;
import br.com.games101.sheet.dto.FeiticoResponseDTO;
import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.repository.CenarioRepository;
import br.com.games101.sheet.service.CenarioService;

@Service
public class CenarioServiceImpl implements CenarioService {

	@Autowired
	private CenarioRepository cenarioRepository;
	
	@Override
	public List<CenarioResponseDTO> listaFichas() {
		return CenarioResponseDTO.convertDTO(cenarioRepository.findAll());
	}

	@Override
	@Transactional
	public List<CenarioResponseDTO> incluiCenarioLista(List<CenarioRequestDTO> cenarioResquestLista) {
		List<CenarioResponseDTO> cenarioResponseLista = new ArrayList<CenarioResponseDTO>();
		cenarioResquestLista.forEach(cenarioRequest -> cenarioResponseLista.add(
													   		CenarioResponseDTO.convertDTO(
													   				cenarioRepository.save(Cenario.retornaEntity(cenarioRequest))
													   		 )
													   	)
									);
		return cenarioResponseLista;
	}
	
	
	@Override
	public CenarioResponseDTO incluiCenario(@Valid CenarioRequestDTO cenarioRequest){
		Cenario cenario = Cenario.retornaEntity(cenarioRequest);
		return CenarioResponseDTO.convertDTO(cenarioRepository.save(cenario));
	}

	@Override
	public Optional<Cenario> buscarCenario(@NotNull Long id) {
		Optional<Cenario> cenario = cenarioRepository.findById(id);
		return cenario;
	}

	@Override
	@Transactional
	public void excluirFicha(Long id) {
		cenarioRepository.deleteById(id);
	}

	@Override
	@Transactional
	public CenarioResponseDTO atualizarCenario(Long id, CenarioRequestDTO cenarioResquest) {
		Optional<Cenario> cenario = cenarioRepository.findById(id);
		CenarioResponseDTO cenarioResponse = null;
		if(cenario.isPresent()) {
			cenario.get().setNome(cenarioResquest.getNome());
			cenarioResponse = CenarioResponseDTO.convertDTO(cenario.get());
		}
		return cenarioResponse;
	}
}
