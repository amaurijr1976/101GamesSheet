package br.com.games101.sheet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.relacaoCenario.FeiticosPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.ItensPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.PericiasPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.VantagensPorCenarioResponseDTO;
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

	@Override
	public VantagensPorCenarioResponseDTO listaVantagensPorCenario(long id) {
		Optional<Cenario> cenarioListaVantagens = cenarioRepository.findById(id);
		return (cenarioListaVantagens.isPresent())?VantagensPorCenarioResponseDTO.convertDTO(cenarioListaVantagens.get()):new VantagensPorCenarioResponseDTO();
	}

	@Override
	public PericiasPorCenarioResponseDTO listaPericiasPorCenario(long id) {
		Optional<Cenario> cenarioListaPericias = cenarioRepository.findById(id);
		return (cenarioListaPericias.isPresent())?PericiasPorCenarioResponseDTO.convertDTO(cenarioListaPericias.get()):new PericiasPorCenarioResponseDTO();
	}

	@Override
	public FeiticosPorCenarioResponseDTO listaFeiticosPorCenario(long id) {
		Optional<Cenario> cenarioListaFeiticos = cenarioRepository.findById(id);
		return (cenarioListaFeiticos.isPresent())?FeiticosPorCenarioResponseDTO.convertDTO(cenarioListaFeiticos.get()):new FeiticosPorCenarioResponseDTO();
	}

}
