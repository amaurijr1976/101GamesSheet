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

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.ItemRequestDTO;
import br.com.games101.sheet.dto.ItemResponseDTO;
import br.com.games101.sheet.service.CenarioService;
import br.com.games101.sheet.service.ItemService;

@RestController
@RequestMapping("/items")
@ResponseBody
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CenarioService cenarioService;
	

	@GetMapping()
	public ResponseEntity<List<ItemResponseDTO>> listaItems(){
		List<ItemResponseDTO> listaItens = itemService.listaItems();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemResponseDTO> buscaItem(@PathVariable @NumberFormat Long id){
		Optional<ItemResponseDTO> item = Optional.ofNullable(itemService.buscaItems(id));
		return (item.isPresent())?ResponseEntity.status(HttpStatus.OK).body(item.get()):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<ItemResponseDTO> incluiItem(@RequestBody @Valid ItemRequestDTO itemRequest,UriComponentsBuilder uriBuilder) throws IllegalAccessException{
		Optional<CenarioResponseDTO> cenario = retornaCenario(itemRequest.getCenario());
		if(cenario.isPresent()) {
			ItemResponseDTO item = itemService.incluiItem(itemRequest,cenario.get());
			URI uri = uriBuilder.path("items/{id}").buildAndExpand(item.getId()).toUri();
			return ResponseEntity.created(uri).body(item);
		}else {
			throw new IllegalArgumentException("cenario não localizado");	
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemResponseDTO> alterarItem(@RequestBody @Valid ItemRequestDTO itemRequest,@PathVariable long id) throws IllegalAccessException{
		Optional<CenarioResponseDTO> cenario = retornaCenario(itemRequest.getCenario());
		if(cenario.isPresent()) {
			ItemResponseDTO item = itemService.alterarItem(itemRequest,cenario.get(),id);
			return (item !=null)?ResponseEntity.ok(item):ResponseEntity.notFound().build();
		}else {
			throw new IllegalArgumentException("cenario não localizado");	
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		return (itemService.excluirItem(id))?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
	
	private Optional<CenarioResponseDTO> retornaCenario(long cenario) {
		Optional<CenarioResponseDTO> cenarioResponse = Optional.ofNullable(cenarioService.buscarCenario(cenario));
		return cenarioResponse;
	}
		
}
