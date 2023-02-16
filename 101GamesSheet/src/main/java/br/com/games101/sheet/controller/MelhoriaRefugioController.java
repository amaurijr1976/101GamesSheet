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

import br.com.games101.sheet.dto.MelhoriaRefugioRequestDTO;
import br.com.games101.sheet.dto.MelhoriaRefugioResponseDTO;
import br.com.games101.sheet.entity.MelhoriaRefugio;
import br.com.games101.sheet.service.MelhoriaRefugioService;

@RestController
@RequestMapping("/melhoriaRefugio")
@ResponseBody
public class MelhoriaRefugioController {
	
	@Autowired
	private MelhoriaRefugioService melhoriaRefugioService;

	@GetMapping()
	public ResponseEntity<List<MelhoriaRefugioResponseDTO>> listaMelhoriaRefugios(){
		List<MelhoriaRefugioResponseDTO> listaItens = melhoriaRefugioService.listaMelhoriaRefugio();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MelhoriaRefugioResponseDTO> buscaMelhoriaRefugio(@PathVariable @NumberFormat Long id){
		Optional<MelhoriaRefugio> melhoriaRefugio = melhoriaRefugioService.buscaMelhoriaRefugio(id);
		return (melhoriaRefugio.isPresent())?ResponseEntity.status(HttpStatus.OK).body(MelhoriaRefugioResponseDTO.convertDTO(melhoriaRefugio.get())):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<MelhoriaRefugioResponseDTO> incluiMelhoriaRefugio(@RequestBody @Valid MelhoriaRefugioRequestDTO melhoriaRefugioRequest,UriComponentsBuilder uriBuilder){
			MelhoriaRefugioResponseDTO melhoriaRefugio = melhoriaRefugioService.incluiMelhoriaRefugio(melhoriaRefugioRequest);
			URI uri = uriBuilder.path("melhoriaRefugio/{id}").buildAndExpand(melhoriaRefugio.getId()).toUri();
			return ResponseEntity.created(uri).body(melhoriaRefugio);
	}
	
	@PostMapping("/incluiListaMelhoriaRefugios")
	public ResponseEntity<List<MelhoriaRefugioResponseDTO>> incluiListaMelhoriaRefugios(@RequestBody @Valid List<MelhoriaRefugioRequestDTO> melhoriaRefugioRequestLista){
			List<MelhoriaRefugioResponseDTO> melhoriaRefugios = melhoriaRefugioService.incluiListaMelhoriaRefugio(melhoriaRefugioRequestLista);
			return ResponseEntity.status(HttpStatus.CREATED).body(melhoriaRefugios);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<MelhoriaRefugioResponseDTO> alterarMelhoriaRefugio(@RequestBody @Valid MelhoriaRefugioRequestDTO melhoriaRefugioRequest,@PathVariable long id){
		MelhoriaRefugioResponseDTO melhoriaRefugio = melhoriaRefugioService.alterarMelhoriaRefugio(melhoriaRefugioRequest,id);
		return (melhoriaRefugio !=null)?ResponseEntity.ok(melhoriaRefugio):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		Optional<MelhoriaRefugio> buscaMelhoriaRefugios = melhoriaRefugioService.buscaMelhoriaRefugio(id);
		if(buscaMelhoriaRefugios.isPresent()) melhoriaRefugioService.excluirMelhoriaRefugio(id);
		return (buscaMelhoriaRefugios.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
