package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ClienteDAO;
import org.brito.desafiojersey.dao.ConteinerDAO;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.exceptions.ConflitoException;
import org.brito.desafiojersey.services.ClienteService;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.PaginatedResponse;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementação da interface {@link ClienteService}, gerenciando operações de clientes usando DAOs.
 */
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO;
    private final ModelMapper modelMapper;
    private final ConteinerDAO conteinerDAO;

    @Inject
    public ClienteServiceImpl(ClienteDAO clienteDAO, ModelMapper modelMapper, ConteinerDAO conteinerDAO) {
        this.clienteDAO = clienteDAO;
        this.modelMapper = modelMapper;
        this.conteinerDAO = conteinerDAO;
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
        verificarSeClientePossuiConteiner(id);
        clienteDAO.deletarCliente(id);
        return MessageUtils.buscaValidacao("cliente.deletado.sucesso", id);
    }

    private void verificarSeClientePossuiConteiner(Integer id) {
        List<Conteiner> listaConteineresCliente = conteinerDAO.listaConteineresPorCliente(id);
        if (!listaConteineresCliente.isEmpty()){
            throw new ConflitoException(
                    MessageUtils.buscaValidacao("cliente.possui.conteiner"));
        }
    }

    @Override
    public PaginatedResponse<ClienteDTO> listarClientesPaginados(Integer paginaAtual, Integer tamanhoPagina) throws SQLException {
        List<Cliente> clientes = clienteDAO.listarClientes(paginaAtual, tamanhoPagina);
        long totalElements = clienteDAO.buscarTotalClientes();
        int totalPages = (int) Math.ceil((double) totalElements / paginaAtual);
        PaginatedResponse<ClienteDTO> response = new PaginatedResponse<>();
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        response.setContent(clientes.stream().map(c -> modelMapper.map(c, ClienteDTO.class)).toList());
        response.setPageNumber(paginaAtual);
        response.setPageSize(tamanhoPagina);

        return response;

    }

}
