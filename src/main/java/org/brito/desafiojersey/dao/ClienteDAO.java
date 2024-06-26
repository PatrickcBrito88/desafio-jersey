package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.dtos.ClienteDTO;

import java.util.List;

public interface ClienteDAO {

    long salvarCliente(ClienteDTO clienteDTO);

    Cliente buscarClientePorId(long id);

    Integer atualizarCliente(ClienteDTO clienteDTO, Integer id);

    void deletarCliente(Integer id);

    List<Cliente> listarClientes();
}
