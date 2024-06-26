package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ClienteDAO;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.services.ClienteService;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.Page;
import org.brito.desafiojersey.utils.PaginadorUtils;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO;
    private final ModelMapper modelMapper;

    @Inject
    public ClienteServiceImpl(ClienteDAO clienteDAO, ModelMapper modelMapper) {
        this.clienteDAO = clienteDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public String salvarCliente(ClienteDTO clienteDTO) {
        long idInserido = clienteDAO.salvarCliente(clienteDTO);
        return MessageUtils.buscaValidacao("cliente.salvo.sucesso", idInserido);

    }

    @Override
    public ClienteDTO buscarClientePorId(long id) {
        Cliente cliente = clienteDAO.buscarClientePorId(id);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public String atualizarCliente(ClienteDTO clienteDTO, Integer id) {
        Integer idAtualizado = clienteDAO.atualizarCliente(clienteDTO, id);
        return MessageUtils.buscaValidacao("cliente.editado.sucesso", idAtualizado);

    }

    @Override
    public String deletarCliente(Integer id) {
        clienteDAO.deletarCliente(id);
        return MessageUtils.buscaValidacao("cliente.deletado.sucesso", id);
    }

    @Override
    public Page<ClienteDTO> listarClientesPaginados(Integer paginaAtual, Integer tamanhoPagina) {
        List<Cliente> usuarios = clienteDAO.listarClientes();
        List<ClienteDTO> clienteDTOS = usuarios.stream()
                .map(u -> modelMapper.map(u, ClienteDTO.class))
                .toList();
        return PaginadorUtils.gerarPaginacao(clienteDTOS, paginaAtual, tamanhoPagina);
    }

}
