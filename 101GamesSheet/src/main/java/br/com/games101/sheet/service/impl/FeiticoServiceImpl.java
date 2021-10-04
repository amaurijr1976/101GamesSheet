package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.FeiticoRequestDTO;
import br.com.games101.sheet.dto.FeiticoResponseDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;
import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.entity.Item;
import br.com.games101.sheet.repository.FeiticoRepository;
import br.com.games101.sheet.service.CenarioService;
import br.com.games101.sheet.service.FeiticoService;

@Service
public class FeiticoServiceImpl implements FeiticoService {

	@Autowired
	private FeiticoRepository feiticoRepository;
	
	@Autowired
	private CenarioService cenarioService;

	@Override
	public List<FeiticoResponseDTO> listaFeiticos() {
		return FeiticoResponseDTO.convertDTO(feiticoRepository.findAll()); 
	}

	@Override
	public Optional<Feitico> buscaFeitico(Long id) {
		return feiticoRepository.findById(id);
	}

	@Override
	public FeiticoResponseDTO incluiFeitico(@Valid FeiticoRequestDTO feiticoRequest) throws IllegalArgumentException {
		Feitico feitico = Feitico.retornaEntity(feiticoRequest,cenarioService.buscarCenario(feiticoRequest.getCenario()));
		FeiticoResponseDTO feiticoResponse = FeiticoResponseDTO.convertDTO(feiticoRepository.save(feitico));
		return feiticoResponse;
	}
	
	@Override
	@Transactional
	public List<FeiticoResponseDTO> incluiListaFeiticos(@Valid List<FeiticoRequestDTO> feiticoRequestLista) {
		List<FeiticoResponseDTO> feiticoResponseLista = new ArrayList<FeiticoResponseDTO>();
		feiticoRequestLista.forEach(feiticoRequest -> feiticoResponseLista.add(
													   		FeiticoResponseDTO.convertDTO(
													   				feiticoRepository.save(Feitico.retornaEntity(feiticoRequest,cenarioService.buscarCenario(feiticoRequest.getCenario())))
													   		 )
													   	)
									);
		return feiticoResponseLista;
	}

	@Override
	@Transactional
	public FeiticoResponseDTO alterarFeitico(@Valid FeiticoRequestDTO feiticoRequest, long id) {
		Optional<Feitico> feitico = feiticoRepository.findById(id);
		FeiticoResponseDTO feiticoResponse = null;
		if(feitico.isPresent()) {
			feitico.get().setId(id);
			feitico.get().setNome(feiticoRequest.getNome());
			feitico.get().setDescricao(feiticoRequest.getDescricao());
			feitico.get().setCenario(cenarioService.buscarCenario(feiticoRequest.getCenario()).get());
			feiticoResponse = FeiticoResponseDTO.convertDTO(feitico.get());
		}
		return feiticoResponse;
	}
	
	@Override
	@Transactional
	public void excluirFeitico(Long id) {
		feiticoRepository.deleteById(id);
	}

	
	


}
