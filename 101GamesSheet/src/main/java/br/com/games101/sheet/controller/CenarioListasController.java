package br.com.games101.sheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.games101.sheet.dto.relacaoCenario.ItensPorCenarioResponseDTO;
import br.com.games101.sheet.service.CenarioListasService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/listaporcenarios")
public class CenarioListasController {

	@Autowired
	private CenarioListasService cenarioListasService; 
	
	@GetMapping("/{id}/itens")
	public ResponseEntity<ItensPorCenarioResponseDTO> relacaoPorCenario(@PathVariable long id){
		ItensPorCenarioResponseDTO itemPorCenario = cenarioListasService.listaItensPorCenario(id);
		return ResponseEntity.status(HttpStatus.OK).body(itemPorCenario);
	}
	
}
