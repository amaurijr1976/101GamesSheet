package br.com.games101.sheet.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping()
	public ResponseEntity<FichaResponseDTO> incluirFicha(@RequestBody @Valid FichaResquestDTO fichaRequest, UriComponentsBuilder uriBuilder){
		FichaResponseDTO fichaResponse = fichaService.salvarFicha(fichaRequest);
		URI uri = uriBuilder.path("/fichas/{id}").buildAndExpand(fichaResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(fichaResponse);
	}
}
