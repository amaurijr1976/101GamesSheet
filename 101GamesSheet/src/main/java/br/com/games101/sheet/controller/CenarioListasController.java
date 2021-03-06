package br.com.games101.sheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.games101.sheet.dto.relacaoCenario.FeiticosPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.ItensPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.PericiasPorCenarioResponseDTO;
import br.com.games101.sheet.dto.relacaoCenario.VantagensPorCenarioResponseDTO;
import br.com.games101.sheet.service.CenarioListasService;

@RestController
@RequestMapping("/listaporcenarios")
public class CenarioListasController {

	@Autowired
	private CenarioListasService cenarioListasService; 
	
	@GetMapping("/{id}/itens")
	public ResponseEntity<ItensPorCenarioResponseDTO> relacaoItensPorCenario(@PathVariable long id){
		ItensPorCenarioResponseDTO itemPorCenario = cenarioListasService.listaItensPorCenario(id);
		return ResponseEntity.status(HttpStatus.OK).body(itemPorCenario);
	}
	
	@GetMapping("/{id}/feiticos")
	public ResponseEntity<FeiticosPorCenarioResponseDTO> relacaoFeiticosPorCenario(@PathVariable long id){
		FeiticosPorCenarioResponseDTO feiticosPorCenario = cenarioListasService.listaFeiticosPorCenario(id);
		return ResponseEntity.status(HttpStatus.OK).body(feiticosPorCenario);
	}
	
	@GetMapping("/{id}/vantagens")
	public ResponseEntity<VantagensPorCenarioResponseDTO> relacaoVantagensPorCenario(@PathVariable long id){
		VantagensPorCenarioResponseDTO vantagensPorCenario = cenarioListasService.listaVantagensPorCenario(id);
		return ResponseEntity.status(HttpStatus.OK).body(vantagensPorCenario);
	}
	
	@GetMapping("/{id}/pericias")
	public ResponseEntity<PericiasPorCenarioResponseDTO> relacaoPericiasPorCenario(@PathVariable long id){
		PericiasPorCenarioResponseDTO periciasPorCenario = cenarioListasService.listaPericiasPorCenario(id);
		return ResponseEntity.status(HttpStatus.OK).body(periciasPorCenario);
	}
	
}
