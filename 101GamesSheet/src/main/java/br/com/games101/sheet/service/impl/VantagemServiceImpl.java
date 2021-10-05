package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.VantagemRequestDTO;
import br.com.games101.sheet.dto.VantagemResponseDTO;
import br.com.games101.sheet.entity.Vantagem;
import br.com.games101.sheet.repository.VantagemRepository;
import br.com.games101.sheet.service.CenarioService;
import br.com.games101.sheet.service.VantagemService;

@Service
public class VantagemServiceImpl implements VantagemService {

	@Autowired
	private VantagemRepository vantagemRepository;
	
	@Autowired
	private CenarioService cenarioService;

	@Override
	public List<VantagemResponseDTO> listaVantagens() {
		return VantagemResponseDTO.convertDTO(vantagemRepository.findAll()); 
	}

	@Override
	public Optional<Vantagem> buscaVantagem(Long id) {
		return vantagemRepository.findById(id);
	}

	@Override
	public VantagemResponseDTO incluiVantagem(@Valid VantagemRequestDTO vantagemRequest) throws IllegalArgumentException {
		Vantagem vantagem = Vantagem.retornaEntity(vantagemRequest,cenarioService.buscarCenario(vantagemRequest.getCenario()));
		VantagemResponseDTO vantagemResponse = VantagemResponseDTO.convertDTO(vantagemRepository.save(vantagem));
		return vantagemResponse;
	}
	
	@Override
	@Transactional
	public List<VantagemResponseDTO> incluiListaVantagens(@Valid List<VantagemRequestDTO> vantagemRequestLista) {
		List<VantagemResponseDTO> vantagemResponseLista = new ArrayList<VantagemResponseDTO>();
		vantagemRequestLista.forEach(vantagemRequest -> vantagemResponseLista.add(
													   		VantagemResponseDTO.convertDTO(
													   				vantagemRepository.save(
													   						Vantagem.retornaEntity(vantagemRequest,cenarioService.buscarCenario(vantagemRequest.getCenario()))
													   						)
													   		 )
													   	)
									);
		return vantagemResponseLista;
	}

	@Override
	@Transactional
	public VantagemResponseDTO alterarVantagem(@Valid VantagemRequestDTO vantagemRequest, long id) {
		Optional<Vantagem> vantagem = vantagemRepository.findById(id);
		VantagemResponseDTO vantagemResponse = null;
		if(vantagem.isPresent()) {
			vantagem.get().setNome(vantagemRequest.getNome());
			vantagem.get().setDescricao(vantagemRequest.getDescricao());
			vantagem.get().setCenario(cenarioService.buscarCenario(vantagemRequest.getCenario()).get());
			vantagemResponse = VantagemResponseDTO.convertDTO(vantagem.get());
		}
		return vantagemResponse;
	}
	
	@Override
	@Transactional
	public void excluirVantagem(Long id) {
		vantagemRepository.deleteById(id);
	}

	
	


}
