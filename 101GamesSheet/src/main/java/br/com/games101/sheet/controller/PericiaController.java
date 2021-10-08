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

import br.com.games101.sheet.dto.PericiaRequestDTO;
import br.com.games101.sheet.dto.PericiaResponseDTO;
import br.com.games101.sheet.entity.Pericia;
import br.com.games101.sheet.service.PericiaService;

@RestController
@RequestMapping("/pericia")
@ResponseBody
public class PericiaController {
	
	@Autowired
	private PericiaService periciaService;

	@GetMapping()
	public ResponseEntity<List<PericiaResponseDTO>> listaPericias(){
		List<PericiaResponseDTO> listaItens = periciaService.listaPericias();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PericiaResponseDTO> buscaPericia(@PathVariable @NumberFormat Long id){
		Optional<Pericia> pericia = periciaService.buscaPericia(id);
		return (pericia.isPresent())?ResponseEntity.status(HttpStatus.OK).body(PericiaResponseDTO.convertDTO(pericia.get())):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<PericiaResponseDTO> incluiPericia(@RequestBody @Valid PericiaRequestDTO periciaRequest,UriComponentsBuilder uriBuilder){
			PericiaResponseDTO pericia = periciaService.incluiPericia(periciaRequest);
			URI uri = uriBuilder.path("pericia/{id}").buildAndExpand(pericia.getId()).toUri();
			return ResponseEntity.created(uri).body(pericia);
	}
	
	@PostMapping("/incluiListaPericias")
	public ResponseEntity<List<PericiaResponseDTO>> incluiListaPericias(@RequestBody @Valid List<PericiaRequestDTO> periciaRequestLista){
			List<PericiaResponseDTO> pericias = periciaService.incluiListaPericias(periciaRequestLista);
			return ResponseEntity.status(HttpStatus.CREATED).body(pericias);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PericiaResponseDTO> alterarPericia(@RequestBody @Valid PericiaRequestDTO periciaRequest,@PathVariable long id){
		PericiaResponseDTO pericia = periciaService.alterarPericia(periciaRequest,id);
		return (pericia !=null)?ResponseEntity.ok(pericia):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		Optional<Pericia> buscaPericias = periciaService.buscaPericia(id);
		if(buscaPericias.isPresent()) periciaService.excluirPericia(id);
		return (buscaPericias.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
