package br.com.games101.sheet.controller;

import java.net.URI;
import java.util.List;

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

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.CenarioResquestDTO;
import br.com.games101.sheet.service.CenarioService;

@RestController
@RequestMapping("/cenario")
public class CenarioController {
	
	@Autowired
	private CenarioService cenarioService;
	
	@GetMapping()
	public ResponseEntity<List<CenarioResponseDTO>> listaCenario(){
		List<CenarioResponseDTO> listaFichas = cenarioService.listaFichas();
		return ResponseEntity.status(HttpStatus.OK).body(listaFichas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CenarioResponseDTO> buscarCenario(@PathVariable Long id){
		CenarioResponseDTO cenario = cenarioService.buscarCenario(id);
		return (cenario!=null)?ResponseEntity.ok(cenario):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<CenarioResponseDTO> incluirFicha(@RequestBody @Valid CenarioResquestDTO cenarioResquest, UriComponentsBuilder uriBuilder){
		CenarioResponseDTO cenarioResponse = cenarioService.salvarCenario(cenarioResquest);
		URI uri = uriBuilder.path("/cenario/{id}").buildAndExpand(cenarioResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(cenarioResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CenarioResponseDTO> atualizarCenario(@RequestBody @Valid CenarioResquestDTO cenarioResquest,@PathVariable Long id){
		CenarioResponseDTO cenarioResponse = cenarioService.atualizarCenario(id,cenarioResquest);
		return (cenarioResponse!=null)?ResponseEntity.ok(cenarioResponse):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		return (cenarioService.excluirFicha(id))?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
	
	
}
