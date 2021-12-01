package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.ItemPersonagemRequestDTO;
import br.com.games101.sheet.dto.ItemPersonagemResponseDTO;
import br.com.games101.sheet.dto.PersonagemRequestDTO;
import br.com.games101.sheet.dto.PersonagemResponseDTO;
import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.entity.Item;
import br.com.games101.sheet.entity.Pericia;
import br.com.games101.sheet.entity.Personagem;
import br.com.games101.sheet.entity.PersonagemItems;
import br.com.games101.sheet.entity.PersonagemItemsId;
import br.com.games101.sheet.entity.Refugio;
import br.com.games101.sheet.entity.Vantagem;
import br.com.games101.sheet.repository.PersonagemItemRepository;
import br.com.games101.sheet.repository.PersonagemRepository;
import br.com.games101.sheet.service.CenarioService;
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
	private PersonagemItemRepository personagemItemRepository;
	
	@Autowired
	private CenarioService cenarioService;
	
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
		Personagem personagem = Personagem.retornaEntity(personagemRequest,cenarioService.buscarCenario(personagemRequest.getCenario()));
		relacionaListasPersonagem(personagemRequest, personagem);
		List<PersonagemItems> listaItensPersonagem = new ArrayList<>();
		PersonagemResponseDTO personagemDTO = PersonagemResponseDTO.convertDTO(personagemRepository.save(personagem));
		personagemRequest.getListaItens().forEach(itemAux -> listaItensPersonagem.add(geraItemPersonagem(itemAux,personagemDTO.getId())));
		personagemItemRepository.saveAll(listaItensPersonagem);
		personagemDTO.setListaItemPersonagem(ItemPersonagemResponseDTO.convertDTO(listaItensPersonagem));
		return personagemDTO;
	}

	@Override
	@Transactional
	public PersonagemResponseDTO alterarPersonagem(@Valid PersonagemRequestDTO personagemRequest, long id) {
		Optional<Personagem> personagem = personagemRepository.findById(id);
		Set<PersonagemItems> listaItensPersonagem = new HashSet<>();
		PersonagemResponseDTO  personagemDTO = null;
		if(personagem.isPresent()) {
			BeanUtils.copyProperties(personagemRequest,personagem.get());
			relacionaListasPersonagem(personagemRequest, personagem.get());
			personagemItemRepository.deleteAll(personagem.get().getListaItems());
			personagemRequest.getListaItens().forEach(itemAux -> listaItensPersonagem.add(geraItemPersonagem(itemAux,personagem.get().getId())));
			personagemItemRepository.saveAll(listaItensPersonagem);
			personagem.get().setListaItems(listaItensPersonagem);
			personagemDTO = PersonagemResponseDTO.convertDTO(personagem.get());
		}
		return personagemDTO;
	}
	
	@Override
	@Transactional
	public void excluirPersonagem(Long id) {
		personagemItemRepository.deleteByPersonagemItemId_IdPersonagem(id);
		personagemRepository.deleteById(id);
	}
	
	private void relacionaListasPersonagem(PersonagemRequestDTO personagemRequest, Personagem personagem) {
		Set<Pericia> listaPericias = new HashSet<>();
        Set<Feitico> listaFeiticos = new HashSet<>();
        Set<Vantagem> listaVantagens = new HashSet<>();
        Set<Refugio> listaRefugios = new HashSet<>();
        personagemRequest.getListaFeiticos().forEach(feiticoAux -> listaFeiticos.add(feiticoService.buscaFeitico(feiticoAux).get()));
		personagemRequest.getListaPericias().forEach(periciaAux -> listaPericias.add(periciaService.buscaPericia(periciaAux).get()));
		personagemRequest.getListaVantagens().forEach(vantagemAux -> listaVantagens.add(vantagemService.buscaVantagem(vantagemAux).get()));
		personagemRequest.getListaRefugios().forEach(refugioAux -> listaRefugios.add(refugioService.buscaRefugio(refugioAux).get()));
		personagem.setListaFeiticos(listaFeiticos);
		personagem.setListaPericias(listaPericias);
		personagem.setListaVantagens(listaVantagens);
		personagem.setListaRefugios(listaRefugios);
	}
	
	private PersonagemItems geraItemPersonagem(ItemPersonagemRequestDTO itemAux,long id) {
		Item item = itemService.buscaItem(itemAux.getIdItem()).get();
		Personagem personagem = buscaPersonagem(id).get();
		PersonagemItemsId personagemItemsId = PersonagemItemsId.builder().idItem(item.getId()).idPersonagem(id).build();
		return PersonagemItems.builder()
				.personagemItemId(personagemItemsId)
				.item(item)
				.personagem(personagem)
				.quantidade(itemAux.getQuantidade())
				.build();
	}
}
