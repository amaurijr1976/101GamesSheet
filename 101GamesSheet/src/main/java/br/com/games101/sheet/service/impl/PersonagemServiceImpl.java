package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.PersonagemRequestDTO;
import br.com.games101.sheet.dto.PersonagemResponseDTO;
import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.entity.Pericia;
import br.com.games101.sheet.entity.Personagem;
import br.com.games101.sheet.entity.PersonagemItems;
import br.com.games101.sheet.entity.PersonagemItemsId;
import br.com.games101.sheet.entity.Refugio;
import br.com.games101.sheet.entity.Vantagem;
import br.com.games101.sheet.repository.PersonagemRepository;
import br.com.games101.sheet.service.FeiticoService;
import br.com.games101.sheet.service.ItemService;
import br.com.games101.sheet.service.PericiaService;
import br.com.games101.sheet.service.PersonagemService;
import br.com.games101.sheet.service.RefugioService;
import br.com.games101.sheet.service.VantagemService;

@Service
public class PersonagemServiceImpl implements PersonagemService {

	@Autowired
	private PersonagemRepository personagemRepository;
	
	@Autowired
	private FeiticoService feiticoService;
	
	@Autowired
	private PericiaService periciaService;
	
	@Autowired
	private VantagemService vantagemService;
	
	@Autowired
	private RefugioService refugioService;
	
	@Autowired
	private ItemService itemService;

	@Override
	public List<PersonagemResponseDTO> listaPersonagems() {
		return PersonagemResponseDTO.convertDTO(personagemRepository.findAll()); 
	}

	@Override
	public Optional<Personagem> buscaPersonagem(Long id) {
		return personagemRepository.findById(id);
	}

	@Override
	public PersonagemResponseDTO incluiPersonagem(@Valid PersonagemRequestDTO personagemRequest) throws IllegalArgumentException {
		Personagem personagem = Personagem.retornaEntity(personagemRequest);
        List<Feitico> listaFeiticos = new ArrayList<>();
        List<Vantagem> listaVantagens = new ArrayList<>();
        List<Pericia> listaPericias = new ArrayList<>();
        List<Refugio> listaRefugios = new ArrayList<>();
        List<PersonagemItems> listaItensPersonagem = new ArrayList<>();
		relacionaListasPersonagem(personagemRequest, personagem, listaFeiticos, listaVantagens, listaPericias,listaRefugios);
		PersonagemResponseDTO personagemDTO = PersonagemResponseDTO.convertDTO(personagemRepository.save(personagem));
		personagemRequest.getListaItens().forEach(itemAux -> listaItensPersonagem.add(geraItemPersonagem(itemAux,personagemDTO.getId())));
		alterarPersonagem(personagemRequest, personagemDTO.getId());
		return personagemDTO;
	}

	@Override
	@Transactional
	public PersonagemResponseDTO alterarPersonagem(@Valid PersonagemRequestDTO personagemRequest, long id) {
		Optional<Personagem> personagem = personagemRepository.findById(id);
		PersonagemResponseDTO  personagemDTO = null;
		if(personagem.isPresent()) {
			BeanUtils.copyProperties(personagemRequest,personagem.get());
			personagemDTO = PersonagemResponseDTO.convertDTO(personagemRepository.save(personagem.get()));}
		return personagemDTO;
	}
	
	@Override
	@Transactional
	public void excluirPersonagem(Long id) {
		personagemRepository.deleteById(id);
	}
	
	private void relacionaListasPersonagem(PersonagemRequestDTO personagemRequest, Personagem personagem,
			List<Feitico> listaFeiticos, List<Vantagem> listaVantagens, List<Pericia> listaPericias,
			List<Refugio> listaRefugios) {
		personagemRequest.getListaFeiticos().forEach(feiticoAux -> listaFeiticos.add(feiticoService.buscaFeitico(feiticoAux.getId()).get()));
		personagemRequest.getListaPericias().forEach(periciaAux -> listaPericias.add(periciaService.buscaPericia(periciaAux.getId()).get()));
		personagemRequest.getListaVantagens().forEach(vantagemAux -> listaVantagens.add(vantagemService.buscaVantagem(vantagemAux.getId()).get()));
		personagemRequest.getListaRefugios().forEach(refugioAux -> listaRefugios.add(refugioService.buscaRefugio(refugioAux.getId()).get()));
		personagem.setListaFeiticos(listaFeiticos);
		personagem.setListaPericias(listaPericias);
		personagem.setListaVantagens(listaVantagens);
		personagem.setListaRefugios(listaRefugios);
	}
	
	private PersonagemItems geraItemPersonagem(PersonagemItems itemAux,long id) {
		PersonagemItemsId personagemItemsId = PersonagemItemsId.builder().itemId(itemAux.getItem().getId()).personagemId(id).build();
		return PersonagemItems.builder()
				.personagemItemId(personagemItemsId)
				.quantidade(itemAux.getQuantidade())
				.dinheiro(itemAux.getDinheiro())
				.build();
	}
}
