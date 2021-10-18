package br.com.games101.sheet.service;

import java.util.Set;

import br.com.games101.sheet.entity.PersonagemItems;

public interface PersonagemItemService {

	public Set<PersonagemItems> findByPersonagemItemId_IdPersonagem(long idPersonagem);
}
