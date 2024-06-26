package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.utils.Page;

public interface ClienteService {

    String salvarCliente(ClienteDTO clienteDTO);

    ClienteDTO buscarClientePorId(long id);

    String atualizarCliente(ClienteDTO clienteDTO, Integer id);

    String deletarCliente(Integer id);

    Page<ClienteDTO> listarClientesPaginados(Integer paginaAtual, Integer tamanhoPagina);

}
