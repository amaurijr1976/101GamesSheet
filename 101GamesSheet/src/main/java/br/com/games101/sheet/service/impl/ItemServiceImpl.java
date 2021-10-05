package br.com.games101.sheet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.ItemRequestDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;
import br.com.games101.sheet.entity.Item;
import br.com.games101.sheet.repository.ItemRepository;
import br.com.games101.sheet.service.CenarioService;
import br.com.games101.sheet.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CenarioService cenarioService;

	@Override
	public List<ItemResponseDTO> listaItems() {
		return ItemResponseDTO.convertDTO(itemRepository.findAll()); 
	}

	@Override
	public Optional<Item> buscaItems(Long id) {
		return itemRepository.findById(id);
	}

	@Override
	public ItemResponseDTO incluiItem(@Valid ItemRequestDTO itemRequest) throws IllegalArgumentException {
		Item item = Item.retornaEntity(itemRequest,cenarioService.buscarCenario(itemRequest.getCenario()));
		ItemResponseDTO itemResponse = ItemResponseDTO.convertDTO(itemRepository.save(item));
		return itemResponse;
	}
	
	@Override
	@Transactional
	public List<ItemResponseDTO> incluiListaItens(@Valid List<ItemRequestDTO> itemRequestLista) {
		List<ItemResponseDTO> itemResponseLista = new ArrayList<ItemResponseDTO>();
		itemRequestLista.forEach(itemRequest -> itemResponseLista.add(
													   		ItemResponseDTO.convertDTO(
													   				itemRepository.save(Item.retornaEntity(itemRequest,cenarioService.buscarCenario(itemRequest.getCenario())))
													   		 )
													   	)
									);
		return itemResponseLista;
	}

	@Override
	@Transactional
	public ItemResponseDTO alterarItem(@Valid ItemRequestDTO itemRequest, long id) {
		Optional<Item> item = itemRepository.findById(id);
		ItemResponseDTO itemResponse = null;
		if(item.isPresent()) {
			item.get().setNome(itemRequest.getNome());
			item.get().setTipo(itemRequest.getTipo());
			item.get().setDescricao(itemRequest.getDescricao());
			item.get().setCenario(cenarioService.buscarCenario(itemRequest.getCenario()).get());
			itemResponse = ItemResponseDTO.convertDTO(item.get());
		}
		return itemResponse;
	}
	
	@Override
	@Transactional
	public void excluirItem(Long id) {
		itemRepository.deleteById(id);
	}

	
	


}
