package br.com.games101.sheet.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.CenarioResquestDTO;
import br.com.games101.sheet.entity.Cenario;
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
	public CenarioResponseDTO salvarCenario(CenarioResquestDTO cenarioResquest) {
		Cenario cenario = cenarioRepository.save(Cenario.retornaEntity(cenarioResquest));
		return CenarioResponseDTO.convertDTO(Optional.ofNullable(cenario));
	}

	@Override
	public CenarioResponseDTO buscarCenario(@NotNull Long id) {
		Optional<Cenario> cenario = cenarioRepository.findById(id);
		return (cenario.isPresent())?CenarioResponseDTO.convertDTO(Optional.ofNullable(cenario.get())):null;
	}

	@Override
	@Transactional
	public boolean excluirFicha(Long id) {
		boolean existe = false;
		Optional<Cenario> cenario = cenarioRepository.findById(id);
		if(cenario.isPresent()) {
			cenarioRepository.deleteById(id);
			existe=true;
		}
		return existe;
	}

	@Override
	@Transactional
	public CenarioResponseDTO atualizarCenario(Long id, CenarioResquestDTO cenarioResquest) {
		Optional<Cenario> cenario = cenarioRepository.findById(id);
		CenarioResponseDTO cenarioResponse = null;
		if(cenario.isPresent()) {
			cenario.get().setNome(cenarioResquest.getNome());
			cenarioResponse = CenarioResponseDTO.convertDTO(Optional.of(cenario.get()));
		}
		return cenarioResponse;
	}

}
