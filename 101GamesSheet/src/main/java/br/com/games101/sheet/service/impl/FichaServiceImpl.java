package br.com.games101.sheet.service.impl;

import java.util.List;

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

}
