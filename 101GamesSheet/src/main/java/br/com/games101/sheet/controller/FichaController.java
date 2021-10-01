package br.com.games101.sheet.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.games101.sheet.dto.FichaResponseDTO;
import br.com.games101.sheet.dto.FichaResquestDTO;
import br.com.games101.sheet.service.FichaService;

@RestController
@RequestMapping("/fichas")
public class FichaController {
	
	@Autowired
	private FichaService fichaService;
	
	@GetMapping()
	public ResponseEntity<List<FichaResponseDTO>> listaFicha(){
		List<FichaResponseDTO> listaFichas = fichaService.listaFichas();
		return ResponseEntity.status(HttpStatus.OK).body(listaFichas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FichaResponseDTO> buscarFicha(@PathVariable Long id){
		FichaResponseDTO ficha = fichaService.buscarFicha(id);
		return (ficha!=null)?ResponseEntity.ok(ficha):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<FichaResponseDTO> incluirFicha(@RequestBody @Valid FichaResquestDTO fichaRequest, UriComponentsBuilder uriBuilder){
		FichaResponseDTO fichaResponse = fichaService.salvarFicha(fichaRequest);
		URI uri = uriBuilder.path("/fichas/{id}").buildAndExpand(fichaResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(fichaResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FichaResponseDTO> atualizarFicha(@RequestBody @Valid FichaResquestDTO fichaRequest,@PathVariable Long id){
		FichaResponseDTO fichaResponse = fichaService.atualizarFicha(id,fichaRequest);
		return (fichaResponse!=null)?ResponseEntity.ok(fichaResponse):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		return (fichaService.excluirFicha(id))?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
	
	
}
