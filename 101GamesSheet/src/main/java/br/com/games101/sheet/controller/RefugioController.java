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

import br.com.games101.sheet.dto.RefugioRequestDTO;
import br.com.games101.sheet.dto.RefugioResponseDTO;
import br.com.games101.sheet.entity.Refugio;
import br.com.games101.sheet.service.RefugioService;

@RestController
@RequestMapping("/refugio")
@ResponseBody
public class RefugioController {
	
	@Autowired
	private RefugioService refugioService;

	@GetMapping()
	public ResponseEntity<List<RefugioResponseDTO>> listaRefugios(){
		List<RefugioResponseDTO> listaItens = refugioService.listaRefugios();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RefugioResponseDTO> buscaRefugio(@PathVariable @NumberFormat Long id){
		Optional<Refugio> refugio = refugioService.buscaRefugio(id);
		return (refugio.isPresent())?ResponseEntity.status(HttpStatus.OK).body(RefugioResponseDTO.convertDTO(refugio.get())):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<RefugioResponseDTO> incluiRefugio(@RequestBody @Valid RefugioRequestDTO refugioRequest,UriComponentsBuilder uriBuilder){
			RefugioResponseDTO refugio = refugioService.incluiRefugio(refugioRequest);
			URI uri = uriBuilder.path("refugio/{id}").buildAndExpand(refugio.getId()).toUri();
			return ResponseEntity.created(uri).body(refugio);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<RefugioResponseDTO> alterarRefugio(@RequestBody @Valid RefugioRequestDTO refugioRequest,@PathVariable long id){
		RefugioResponseDTO refugio = refugioService.alterarRefugio(refugioRequest,id);
		return (refugio !=null)?ResponseEntity.ok(refugio):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		Optional<Refugio> buscaRefugios = refugioService.buscaRefugio(id);
		if(buscaRefugios.isPresent()) refugioService.excluirRefugio(id);
		return (buscaRefugios.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
