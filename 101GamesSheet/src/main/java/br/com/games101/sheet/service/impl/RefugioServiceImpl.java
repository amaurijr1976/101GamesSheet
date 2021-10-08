package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.RefugioRequestDTO;
import br.com.games101.sheet.dto.RefugioResponseDTO;
import br.com.games101.sheet.dto.VantagemResponseDTO;
import br.com.games101.sheet.entity.MelhoriaRefugio;
import br.com.games101.sheet.entity.Refugio;
import br.com.games101.sheet.entity.Vantagem;
import br.com.games101.sheet.repository.RefugioRepository;
import br.com.games101.sheet.service.MelhoriaRefugioService;
import br.com.games101.sheet.service.RefugioService;

@Service
public class RefugioServiceImpl implements RefugioService {

	@Autowired
	private RefugioRepository refugioRepository;
	
	@Autowired
	private MelhoriaRefugioService melhoriaRefugioService;

	@Override
	public List<RefugioResponseDTO> listaRefugios() {
		return RefugioResponseDTO.convertDTO(refugioRepository.findAll()); 
	}

	@Override
	public Optional<Refugio> buscaRefugio(Long id) {
		return refugioRepository.findById(id);
	}

	@Override
	public RefugioResponseDTO incluiRefugio(@Valid RefugioRequestDTO refugioRequest) throws IllegalArgumentException {
		Refugio refugio = Refugio.retornaEntity(refugioRequest);
        List<MelhoriaRefugio> listaMelhoria = new ArrayList<>();
		refugioRequest.getMelhoriasRefugio().forEach(refugioAux -> listaMelhoria.add(melhoriaRefugioService.buscaMelhoriaRefugio(refugioAux.getId()).get()));
		refugio.setMelhoriasRefugio(listaMelhoria);
		RefugioResponseDTO refugioDTO = RefugioResponseDTO.convertDTO(refugioRepository.save(refugio));
		return refugioDTO;
	}

	@Override
	@Transactional
	public RefugioResponseDTO alterarRefugio(@Valid RefugioRequestDTO refugioRequest, long id) {
		Optional<Refugio> refugio = refugioRepository.findById(id);
		RefugioResponseDTO  refugioDTO = null;
		if(refugio.isPresent()) {
			//List<MelhoriaRefugio> listaMelhoria = new ArrayList<>();
			//refugioRequest.getListaMelhoriaRefugio().forEach(refugioAux -> listaMelhoria.add(melhoriaRefugioService.buscaMelhoriaRefugio(refugioAux.getId()).get()));
			//refugio.setMelhoriasRefugio(listaMelhoria);
			BeanUtils.copyProperties(refugioRequest,refugio.get());
			refugioDTO = RefugioResponseDTO.convertDTO(refugioRepository.save(refugio.get()));}
		return refugioDTO;
	}
	
	@Override
	@Transactional
	public void excluirRefugio(Long id) {
		refugioRepository.deleteById(id);
	}
}
