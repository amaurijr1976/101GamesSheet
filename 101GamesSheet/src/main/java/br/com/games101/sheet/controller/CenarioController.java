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

import br.com.games101.sheet.dto.CenarioResponseDTO;
import br.com.games101.sheet.dto.CenarioRequestDTO;
import br.com.games101.sheet.entity.Cenario;
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
		Optional<Cenario> buscarCenario = cenarioService.buscarCenario(id);
		return (buscarCenario.isPresent())?ResponseEntity.ok(CenarioResponseDTO.convertDTO(buscarCenario.get())):ResponseEntity.notFound().build();
	}
	
	
	@PostMapping()
	public ResponseEntity<CenarioResponseDTO> incluirFicha(@RequestBody @Valid CenarioRequestDTO cenarioResquest, UriComponentsBuilder uriBuilder){
		CenarioResponseDTO cenarioResponse = cenarioService.incluiCenario(cenarioResquest);
		URI uri = uriBuilder.path("/cenario/{id}").buildAndExpand(cenarioResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(cenarioResponse);
	}
	
	@PostMapping("/incluiListaCenarios")
	public ResponseEntity<List<CenarioResponseDTO>> incluirListaFicha(@RequestBody @Valid List<CenarioRequestDTO> cenarioResquestLista){
		List<CenarioResponseDTO> cenarioResponseLista = cenarioService.incluiCenarioLista(cenarioResquestLista);
		return ResponseEntity.ok(cenarioResponseLista);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CenarioResponseDTO> atualizarCenario(@RequestBody @Valid CenarioRequestDTO cenarioResquest,@PathVariable Long id){
		CenarioResponseDTO cenarioResponse = cenarioService.atualizarCenario(id,cenarioResquest);
		return (cenarioResponse!=null)?ResponseEntity.ok(cenarioResponse):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirCenario(@PathVariable Long id){
		Optional<Cenario> buscarCenario = cenarioService.buscarCenario(id);
		if (buscarCenario.isPresent()) cenarioService.excluirFicha(id);
		return (buscarCenario.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
	
	
}
