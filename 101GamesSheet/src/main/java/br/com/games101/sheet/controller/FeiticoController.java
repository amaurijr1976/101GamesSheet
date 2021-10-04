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

import br.com.games101.sheet.dto.FeiticoRequestDTO;
import br.com.games101.sheet.dto.FeiticoResponseDTO;
import br.com.games101.sheet.entity.Feitico;
import br.com.games101.sheet.service.FeiticoService;

@RestController
@RequestMapping("/feitico")
@ResponseBody
public class FeiticoController {
	
	@Autowired
	private FeiticoService feiticoService;

	@GetMapping()
	public ResponseEntity<List<FeiticoResponseDTO>> listaFeiticos(){
		List<FeiticoResponseDTO> listaItens = feiticoService.listaFeiticos();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FeiticoResponseDTO> buscaFeitico(@PathVariable @NumberFormat Long id){
		Optional<Feitico> feitico = feiticoService.buscaFeitico(id);
		return (feitico.isPresent())?ResponseEntity.status(HttpStatus.OK).body(FeiticoResponseDTO.convertDTO(feitico.get())):ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	public ResponseEntity<FeiticoResponseDTO> incluiFeitico(@RequestBody @Valid FeiticoRequestDTO feiticoRequest,UriComponentsBuilder uriBuilder){
			FeiticoResponseDTO feitico = feiticoService.incluiFeitico(feiticoRequest);
			URI uri = uriBuilder.path("feiticos/{id}").buildAndExpand(feitico.getId()).toUri();
			return ResponseEntity.created(uri).body(feitico);
	}
	
	@PostMapping("/incluiListaFeiticos")
	public ResponseEntity<List<FeiticoResponseDTO>> incluiListaItens(@RequestBody @Valid List<FeiticoRequestDTO> feiticoRequestLista){
			List<FeiticoResponseDTO> feiticos = feiticoService.incluiListaFeiticos(feiticoRequestLista);
			return ResponseEntity.status(HttpStatus.CREATED).body(feiticos);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<FeiticoResponseDTO> alterarFeitico(@RequestBody @Valid FeiticoRequestDTO feiticoRequest,@PathVariable long id) throws IllegalAccessException{
		FeiticoResponseDTO feitico = feiticoService.alterarFeitico(feiticoRequest,id);
		return (feitico !=null)?ResponseEntity.ok(feitico):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		Optional<Feitico> buscaFeitico = feiticoService.buscaFeitico(id);
		if(buscaFeitico.isPresent()) feiticoService.excluirFeitico(id);
		return (buscaFeitico.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
