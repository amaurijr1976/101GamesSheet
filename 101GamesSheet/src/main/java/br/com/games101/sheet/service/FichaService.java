package br.com.games101.sheet.service;

import java.util.List;

import br.com.games101.sheet.dto.FichaResponseDTO;
import br.com.games101.sheet.dto.FichaResquestDTO;

public interface FichaService {

	public List<FichaResponseDTO> listaFichas();

	public FichaResponseDTO salvarFicha(FichaResquestDTO fichaRequest);
}
