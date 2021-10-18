package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.games101.sheet.dto.ItemRequestDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;
import br.com.games101.sheet.entity.Item;

public interface ItemService {

	public List<ItemResponseDTO> listaItems();

	public Optional<Item> buscaItem(Long id);

	public ItemResponseDTO incluiItem(@Valid ItemRequestDTO itemRequest);

	public List<ItemResponseDTO> incluiListaItens(@Valid List<ItemRequestDTO> itemRequestLista);
	
	public ItemResponseDTO alterarItem(@Valid ItemRequestDTO itemRequest, long id);
	
	public void excluirItem(Long id);
}
