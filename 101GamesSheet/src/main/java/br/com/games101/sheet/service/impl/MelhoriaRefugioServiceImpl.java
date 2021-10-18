package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.MelhoriaRefugioRequestDTO;
import br.com.games101.sheet.dto.MelhoriaRefugioResponseDTO;
import br.com.games101.sheet.entity.MelhoriaRefugio;
import br.com.games101.sheet.repository.MelhoriaRefugioRepository;
import br.com.games101.sheet.service.CenarioService;
import br.com.games101.sheet.service.MelhoriaRefugioService;

@Service
public class MelhoriaRefugioServiceImpl implements MelhoriaRefugioService {

	@Autowired
	private MelhoriaRefugioRepository melhoriaRefugioRepository;
	
	@Autowired
	private CenarioService cenarioService;

	@Override
	public List<MelhoriaRefugioResponseDTO> listaMelhoriaRefugio() {
		return MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugioRepository.findAll()); 
	}

	@Override
	public Optional<MelhoriaRefugio> buscaMelhoriaRefugio(Long id) {
		return melhoriaRefugioRepository.findById(id);
	}
	@Override
	@Transactional
	public List<MelhoriaRefugioResponseDTO> incluiListaMelhoriaRefugio(@Valid List<MelhoriaRefugioRequestDTO> melhoriaRefugioRequestLista) {
		List<MelhoriaRefugioResponseDTO> melhoriaRefugioResponseLista = new ArrayList<MelhoriaRefugioResponseDTO>();
		melhoriaRefugioRequestLista.forEach(melhoriaRefugioRequest -> melhoriaRefugioResponseLista.add(
													   		MelhoriaRefugioResponseDTO.convertDTO(
													   				melhoriaRefugioRepository.save(MelhoriaRefugio.retornaEntity(melhoriaRefugioRequest,cenarioService.buscarCenario(melhoriaRefugioRequest.getCenario())))
													   		 )
													   	)
									);
		return melhoriaRefugioResponseLista;
	}

	@Override
	public MelhoriaRefugioResponseDTO incluiMelhoriaRefugio(@Valid MelhoriaRefugioRequestDTO melhoriaRefugioRequest) {
		MelhoriaRefugio melhoriaRefugio = MelhoriaRefugio.retornaEntity(melhoriaRefugioRequest,cenarioService.buscarCenario(melhoriaRefugioRequest.getCenario()));
		MelhoriaRefugioResponseDTO melhoriaRefugioResponse = MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugioRepository.save(melhoriaRefugio));
		return melhoriaRefugioResponse;
	}

	@Override
	@Transactional
	public MelhoriaRefugioResponseDTO alterarMelhoriaRefugio(@Valid MelhoriaRefugioRequestDTO melhoriaRefugioRequest, long id) {
		Optional<MelhoriaRefugio> melhoriaRefugio = melhoriaRefugioRepository.findById(id);
		MelhoriaRefugioResponseDTO melhoriaRefugioResponse = null;
		if(melhoriaRefugio.isPresent()) {
			melhoriaRefugio.get().setNome(melhoriaRefugioRequest.getNome());
			melhoriaRefugio.get().setDescricao(melhoriaRefugioRequest.getDescricao());
			melhoriaRefugio.get().setBonus_defesa(melhoriaRefugioRequest.getBonus_defesa());
			melhoriaRefugio.get().setCusto_tecnologia(melhoriaRefugioRequest.getCusto_tecnologia());
			melhoriaRefugio.get().setCusto_espaco(melhoriaRefugioRequest.getCusto_espaco());
			melhoriaRefugio.get().setCenario(cenarioService.buscarCenario(melhoriaRefugioRequest.getCenario()).get());
			melhoriaRefugioResponse = MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugio.get());
		}
		return melhoriaRefugioResponse;
	}
	
	@Override
	@Transactional
	public void excluirMelhoriaRefugio(Long id) {
		melhoriaRefugioRepository.deleteById(id);
	}
}
