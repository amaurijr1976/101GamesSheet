package br.com.games101.sheet.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.games101.sheet.entity.PersonagemItems;
import br.com.games101.sheet.entity.PersonagemItemsId;

public interface PersonagemItemRepository extends JpaRepository<PersonagemItems,PersonagemItemsId>{

	Set<PersonagemItems> findByPersonagemItemId_IdPersonagem(long idPersonagem);
	
	long deleteByPersonagemItemId_IdPersonagem(long idPersonagem);
}
