package br.com.games101.sheet.service;

import java.util.List;

import javax.validation.Valid;

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.ItemRequestDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;

public interface ItemService {

	public List<ItemResponseDTO> listaItems();

	public ItemResponseDTO buscaItems(Long id);

	public ItemResponseDTO incluiItem(@Valid ItemRequestDTO itemRequest,CenarioResponseDTO cenario) throws IllegalArgumentException;

	public ItemResponseDTO alterarItem(@Valid ItemRequestDTO itemRequest, CenarioResponseDTO cenarioResponseDTO, long id);
	
	public boolean excluirItem(Long id);

}
