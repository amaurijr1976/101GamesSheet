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

import com.fasterxml.jackson.annotation.JsonView;

import br.com.games101.sheet.dto.ItemPersonagemResponseDTO;
import br.com.games101.sheet.dto.PersonagemRequestDTO;
import br.com.games101.sheet.dto.PersonagemResponseDTO;
import br.com.games101.sheet.dto.ViewDetalhes;
import br.com.games101.sheet.entity.Personagem;
import br.com.games101.sheet.service.PersonagemService;
import br.com.games101.sheet.service.impl.PersonagemItemServiceImpl;

@RestController
@RequestMapping("/personagem")
@ResponseBody
public class PersonagemController {
	
	@Autowired
	private PersonagemService personagemService;
	
	@Autowired
	private PersonagemItemServiceImpl personagemItemService;

	
	@GetMapping()
	public ResponseEntity<List<PersonagemResponseDTO>> listaPersonagems(){
		List<PersonagemResponseDTO> listaItens = personagemService.listaPersonagems();
		return ResponseEntity.status(HttpStatus.OK).body(listaItens);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonagemResponseDTO> buscaPersonagem(@PathVariable @NumberFormat Long id){
		Optional<Personagem> personagem = personagemService.buscaPersonagem(id);
		if(personagem.isPresent()) {
			PersonagemResponseDTO personagemDTO = PersonagemResponseDTO.convertDTO(personagem.get());
			//personagemDTO.setListaItemPersonagem(ItemPersonagemResponseDTO.convertDTO(personagemItemService.findByPersonagemItemId_IdPersonagem(personagemDTO.getId())));
			return ResponseEntity.status(HttpStatus.OK).body(personagemDTO);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<PersonagemResponseDTO> incluiPersonagem(@RequestBody @Valid PersonagemRequestDTO personagemRequest,UriComponentsBuilder uriBuilder){
			PersonagemResponseDTO personagem = personagemService.incluiPersonagem(personagemRequest);
			URI uri = uriBuilder.path("personagem/{id}").buildAndExpand(personagem.getId()).toUri();
			return ResponseEntity.created(uri).body(personagem);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonagemResponseDTO> alterarPersonagem(@RequestBody @Valid PersonagemRequestDTO personagemRequest,@PathVariable long id){
		PersonagemResponseDTO personagem = personagemService.alterarPersonagem(personagemRequest,id);
		return (personagem !=null)?ResponseEntity.ok(personagem):ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirFicha(@PathVariable Long id){
		Optional<Personagem> buscaPersonagems = personagemService.buscaPersonagem(id);
		if(buscaPersonagems.isPresent()) {
			personagemService.excluirPersonagem(id);
		}
		return (buscaPersonagems.isPresent())?ResponseEntity.ok().build():ResponseEntity.notFound().build();
	}
}
