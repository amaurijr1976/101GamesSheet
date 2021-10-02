package br.com.games101.sheet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.relacaoCenario.ItensPorCenarioResponseDTO;
import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.repository.CenarioRepository;
import br.com.games101.sheet.service.CenarioListasService;

@Service
public class CenarioListasServiceImpl implements CenarioListasService {

	@Autowired
	private CenarioRepository cenarioRepository;
	
	@Override
	public ItensPorCenarioResponseDTO listaItensPorCenario(long id) {
		Optional<Cenario> cenarioListaItens = cenarioRepository.findById(id);
		return (cenarioListaItens.isPresent())?ItensPorCenarioResponseDTO.convertDTO(cenarioListaItens.get()):new ItensPorCenarioResponseDTO();
	}

}
