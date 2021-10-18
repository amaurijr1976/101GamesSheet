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

import br.com.games101.sheet.dto.VantagemRequestDTO;
import br.com.games101.sheet.dto.VantagemResponseDTO;
import br.com.games101.sheet.entity.Vantagem;
import br.com.games101.sheet.service.VantagemService;

@RestController
@RequestMapping("/vantagem")
@ResponseBody
public class VantagemController {
	
	@Autowired
	private VantagemService vantagemService;

	@GetMapping()
	public ResponseEntity<List<VantagemResponseDTO>> listaVantagens(){
		List<VantagemResponseDTO> listaItens = vantagemService.listaVantagens();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VantagemResponseDTO> buscaVantagem(@PathVariable @NumberFormat Long id){
		Optional<Vantagem> vantagem = vantagemService.buscaVantagem(id);
		return (vantagem.isPresent())?ResponseEntity.status(HttpStatus.OK).body(VantagemResponseDTO.convertDTO(vantagem.get())):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<VantagemResponseDTO> incluiVantagem(@RequestBody @Valid VantagemRequestDTO vantagemRequest,UriComponentsBuilder uriBuilder){
			VantagemResponseDTO vantagem = vantagemService.incluiVantagem(vantagemRequest);
			URI uri = uriBuilder.path("vantagem/{id}").buildAndExpand(vantagem.getId()).toUri();
			return ResponseEntity.created(uri).body(vantagem);
	}
	
	@PostMapping("/incluiListaVantagens")
	public ResponseEntity<List<VantagemResponseDTO>> incluiListaPericias(@RequestBody @Valid List<VantagemRequestDTO> vantagemRequestLista){
			List<VantagemResponseDTO> vantagens = vantagemService.incluiListaVantagens(vantagemRequestLista);
			return ResponseEntity.status(HttpStatus.CREATED).body(vantagens);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<VantagemResponseDTO> alterarVantagem(@RequestBody @Valid VantagemRequestDTO vantagemRequest,@PathVariable long id){
		VantagemResponseDTO vantagem = vantagemService.alterarVantagem(vantagemRequest,id);
		return (vantagem !=null)?ResponseEntity.ok(vantagem):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		Optional<Vantagem> buscaVantagem = vantagemService.buscaVantagem(id);
		if(buscaVantagem.isPresent()) vantagemService.excluirVantagem(id);
		return (buscaVantagem.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
