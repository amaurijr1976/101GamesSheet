package br.com.games101.sheet.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.ItemRequestDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;
import br.com.games101.sheet.entity.Cenario;
import br.com.games101.sheet.entity.Item;
import br.com.games101.sheet.repository.ItemReposityory;
import br.com.games101.sheet.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemReposityory itemRepository;
	

	@Override
	public List<ItemResponseDTO> listaItems() {
		return ItemResponseDTO.convertDTO(itemRepository.findAll()); 
	}

	@Override
	public ItemResponseDTO buscaItems(Long id) {
		Optional<Item> item = itemRepository.findById(id);
		return (item.isPresent())?ItemResponseDTO.convertDTO(item.get()):null;
	}

	@Override
	public ItemResponseDTO incluiItem(@Valid ItemRequestDTO itemRequest,CenarioResponseDTO cenario) throws IllegalArgumentException {
		Item item = Item.retornaEntity(itemRequest,cenario);
		ItemResponseDTO itemResponse = ItemResponseDTO.convertDTO(itemRepository.save(item));
		return itemResponse;
	}

	@Override
	@Transactional
	public ItemResponseDTO alterarItem(@Valid ItemRequestDTO itemRequest, CenarioResponseDTO cenarioResponse, long id) {
		Optional<Item> item = itemRepository.findById(id);
		ItemResponseDTO itemResponse = null;
		if(item.isPresent()) {
			item.get().setId(id);
			item.get().setNome(itemRequest.getNome());
			item.get().setTipo(itemRequest.getTipo());
			item.get().setDescricao(itemRequest.getDescricao());
			item.get().setCenario(Cenario.builder().id(cenarioResponse.getId()).nome(cenarioResponse.getNome()).build());
			itemResponse = ItemResponseDTO.convertDTO(item.get());
		}
		return itemResponse;
	}
	
	@Override
	@Transactional
	public boolean excluirItem(Long id) {
		boolean isDelete = false;
		Optional<Item> cenario = itemRepository.findById(id);
		if(cenario.isPresent()) {
			itemRepository.deleteById(id);
			isDelete=true;
		}
		return isDelete;
	}
	
	


}
