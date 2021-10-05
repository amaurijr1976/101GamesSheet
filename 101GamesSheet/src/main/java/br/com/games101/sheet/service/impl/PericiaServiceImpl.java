package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.PericiaRequestDTO;
import br.com.games101.sheet.dto.PericiaResponseDTO;
import br.com.games101.sheet.entity.Pericia;
import br.com.games101.sheet.repository.PericiaRepository;
import br.com.games101.sheet.service.CenarioService;
import br.com.games101.sheet.service.PericiaService;

@Service
public class PericiaServiceImpl implements PericiaService {

	@Autowired
	private PericiaRepository periciaRepository;
	
	@Autowired
	private CenarioService cenarioService;

	@Override
	public List<PericiaResponseDTO> listaPericias() {
		return PericiaResponseDTO.convertDTO(periciaRepository.findAll()); 
	}

	@Override
	public Optional<Pericia> buscaPericias(Long id) {
		return periciaRepository.findById(id);
	}
	@Override
	@Transactional
	public List<PericiaResponseDTO> incluiListaPericias(@Valid List<PericiaRequestDTO> periciaRequestLista) {
		List<PericiaResponseDTO> periciaResponseLista = new ArrayList<PericiaResponseDTO>();
		periciaRequestLista.forEach(periciaRequest -> periciaResponseLista.add(
													   		PericiaResponseDTO.convertDTO(
													   				periciaRepository.save(Pericia.retornaEntity(periciaRequest,cenarioService.buscarCenario(periciaRequest.getCenario())))
													   		 )
													   	)
									);
		return periciaResponseLista;
	}

	@Override
	public PericiaResponseDTO incluiPericia(@Valid PericiaRequestDTO periciaRequest) throws IllegalArgumentException {
		Pericia pericia = Pericia.retornaEntity(periciaRequest,cenarioService.buscarCenario(periciaRequest.getCenario()));
		PericiaResponseDTO periciaResponse = PericiaResponseDTO.convertDTO(periciaRepository.save(pericia));
		return periciaResponse;
	}

	@Override
	@Transactional
	public PericiaResponseDTO alterarPericia(@Valid PericiaRequestDTO periciaRequest, long id) {
		Optional<Pericia> pericia = periciaRepository.findById(id);
		PericiaResponseDTO periciaResponse = null;
		if(pericia.isPresent()) {
			pericia.get().setNome(periciaRequest.getNome());
			pericia.get().setDescricao(periciaRequest.getDescricao());
			pericia.get().setCenario(cenarioService.buscarCenario(periciaRequest.getCenario()).get());
			periciaResponse = PericiaResponseDTO.convertDTO(pericia.get());
		}
		return periciaResponse;
	}
	
	@Override
	@Transactional
	public void excluirPericia(Long id) {
		periciaRepository.deleteById(id);
	}

}
