package br.com.games101.sheet.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.games101.sheet.dto.ItemRequestDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;
import br.com.games101.sheet.dto.VantagemRequestDTO;
import br.com.games101.sheet.dto.VantagemResponseDTO;
import br.com.games101.sheet.entity.Item;
import br.com.games101.sheet.service.ItemService;

@RestController
@RequestMapping("/item")
@ResponseBody
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@GetMapping()
	public ResponseEntity<List<ItemResponseDTO>> listaItems(){
		List<ItemResponseDTO> listaItens = itemService.listaItems();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemResponseDTO> buscaItem(@PathVariable @NumberFormat Long id){
		Optional<Item> item = itemService.buscaItem(id);
		return (item.isPresent())?ResponseEntity.status(HttpStatus.OK).body(ItemResponseDTO.convertDTO(item.get())):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<ItemResponseDTO> incluiItem(@RequestBody @Valid ItemRequestDTO itemRequest,UriComponentsBuilder uriBuilder){
			ItemResponseDTO item = itemService.incluiItem(itemRequest);
			URI uri = uriBuilder.path("items/{id}").buildAndExpand(item.getId()).toUri();
			return ResponseEntity.created(uri).body(item);
	}
	
	@PostMapping("/incluiListaItens")
	public ResponseEntity<List<ItemResponseDTO>> incluiListaItens(@RequestBody @Valid List<ItemRequestDTO> itemRequestLista){
			List<ItemResponseDTO> itens = itemService.incluiListaItens(itemRequestLista);
			return ResponseEntity.status(HttpStatus.CREATED).body(itens);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemResponseDTO> alterarItem(@RequestBody @Valid ItemRequestDTO itemRequest,@PathVariable long id){
		ItemResponseDTO item = itemService.alterarItem(itemRequest,id);
		return (item !=null)?ResponseEntity.ok(item):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		Optional<Item> buscaItems = itemService.buscaItem(id);
		if(buscaItems.isPresent()) itemService.excluirItem(id);
		return (buscaItems.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
