package br.com.games101.sheet.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.games101.sheet.entity.PersonagemItems;
import br.com.games101.sheet.repository.PersonagemItemRepository;
import br.com.games101.sheet.service.PersonagemItemService;


@Service
public class PersonagemItemServiceImpl implements PersonagemItemService {

	@Autowired
	private PersonagemItemRepository personagemItemRepository;
	
	@Override
	public Set<PersonagemItems> findByPersonagemItemId_IdPersonagem(long idPersonagem) {
		return personagemItemRepository.findByPersonagemItemId_IdPersonagem(idPersonagem);
	}

}
