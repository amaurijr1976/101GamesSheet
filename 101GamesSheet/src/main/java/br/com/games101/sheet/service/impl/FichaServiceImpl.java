package br.com.games101.sheet.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.FichaResponseDTO;
import br.com.games101.sheet.dto.FichaResquestDTO;
import br.com.games101.sheet.entity.Ficha;
import br.com.games101.sheet.repository.FichaRepository;
import br.com.games101.sheet.service.FichaService;

@Service
public class FichaServiceImpl implements FichaService {

	@Autowired
	private FichaRepository fichaRepository;
	
	@Override
	public List<FichaResponseDTO> listaFichas() {
		return FichaResponseDTO.convertDTO(fichaRepository.findAll());
	}

	@Override
	public FichaResponseDTO salvarFicha(FichaResquestDTO fichaRequest) {
		Ficha ficha = fichaRepository.save(fichaRequest.convertEntity());
		return FichaResponseDTO.convertDTO(ficha);
	}

	@Override
	public FichaResponseDTO buscarFicha(@NotNull Long id) {
		Optional<Ficha> ficha = fichaRepository.findById(id);
		return (ficha.isPresent())?FichaResponseDTO.convertDTO(ficha.get()):null;
	}

	@Override
	@Transactional
	public boolean excluirFicha(Long id) {
		boolean existe = false;
		Optional<Ficha> ficha = fichaRepository.findById(id);
		if(ficha.isPresent()) {
			fichaRepository.deleteById(id);
			existe=true;
		}
		return existe;
	}

	@Override
	@Transactional
	public FichaResponseDTO atualizarFicha(Long id, FichaResquestDTO fichaRequest) {
		Optional<Ficha> ficha = fichaRepository.findById(id);
		FichaResponseDTO fichaResponse = null;
		if(ficha.isPresent()) {
			ficha.get().setNome(fichaRequest.getNome());
			fichaResponse = FichaResponseDTO.convertDTO(ficha.get());
		}
		return fichaResponse;
	}

}
