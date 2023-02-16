package br.com.games101.sheet.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.games101.sheet.dto.PersonagemRequestDTO;
import br.com.games101.sheet.dto.PersonagemResponseDTO;
import br.com.games101.sheet.entity.Personagem;

public interface PersonagemService {

	public List<PersonagemResponseDTO> listaPersonagems();

	public Optional<Personagem> buscaPersonagem(Long id);

	public PersonagemResponseDTO incluiPersonagem(@Valid PersonagemRequestDTO personagemRequest);

	public PersonagemResponseDTO alterarPersonagem(@Valid PersonagemRequestDTO personagemRequest, long id);
	
	public void excluirPersonagem(Long id);
	
}
