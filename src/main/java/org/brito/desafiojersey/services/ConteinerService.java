package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.ConteinerDTO;
import org.brito.desafiojersey.utils.Page;

public interface ConteinerService {

    String salvarConteiner(ConteinerDTO conteinerDTO);

    ConteinerDTO buscarConteinerPorId(long id);

    String atualizarConteiner(ConteinerDTO conteinerDTO, Integer id);

    String deletarConteiner(Integer id);

    Page<ConteinerDTO> listarConteinersPaginados(Integer paginaAtual, Integer tamanhoPagina);

}
