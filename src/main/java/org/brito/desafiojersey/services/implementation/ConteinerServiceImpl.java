package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ClienteDAO;
import org.brito.desafiojersey.dao.ConteinerDAO;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.dtos.ConteinerDTO;
import org.brito.desafiojersey.services.ConteinerService;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.Page;
import org.brito.desafiojersey.utils.PaginadorUtils;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ConteinerServiceImpl implements ConteinerService {

    private final ConteinerDAO conteinerDAO;
    private final ModelMapper modelMapper;
    private final ClienteDAO clienteDAO;

    @Inject
    public ConteinerServiceImpl(ConteinerDAO conteinerDAO, ModelMapper modelMapper, ClienteDAO clienteDAO) {
        this.conteinerDAO = conteinerDAO;
        this.modelMapper = modelMapper;
        this.clienteDAO = clienteDAO;
    }

    @Override
    public String salvarConteiner(ConteinerDTO conteinerDTO) {
        Cliente cliente = clienteDAO.buscarClientePorId(conteinerDTO.getClienteId());
        Conteiner conteiner = modelMapper.map(conteinerDTO, Conteiner.class);
        conteiner.setCliente(modelMapper.map(cliente, Cliente.class));
        long idInserido = conteinerDAO.salvarContainer(conteiner);

        return MessageUtils.buscaValidacao("conteiner.salvo.sucesso", idInserido);
    }

    @Override
    public ConteinerDTO buscarConteinerPorId(long id) {
        Conteiner conteiner = conteinerDAO.buscarContainerPorId(id);

        return modelMapper.map(conteiner, ConteinerDTO.class);
    }

    @Override
    public String atualizarConteiner(ConteinerDTO conteinerDTO, Integer id) {
        Cliente cliente = clienteDAO.buscarClientePorId(conteinerDTO.getClienteId());
        Conteiner conteiner = modelMapper.map(conteinerDTO, Conteiner.class);
        conteiner.setCliente(modelMapper.map(cliente, Cliente.class));
        Integer idAtualizado = conteinerDAO.atualizarContainer(conteiner, id);
        return MessageUtils.buscaValidacao("conteiner.editado.sucesso", idAtualizado);

    }

    @Override
    public String deletarConteiner(Integer id) {
        conteinerDAO.deletarContainer(id);
        return MessageUtils.buscaValidacao("conteiner.deletado.sucesso", id);
    }

    @Override
    public Page<ConteinerDTO> listarConteinersPaginados(Integer paginaAtual, Integer tamanhoPagina) {
        List<Conteiner> usuarios = conteinerDAO.listarContaineres();
        List<ConteinerDTO> conteinerDTOS = usuarios.stream()
                .map(u -> modelMapper.map(u, ConteinerDTO.class))
                .toList();
        return PaginadorUtils.gerarPaginacao(conteinerDTOS, paginaAtual, tamanhoPagina);
    }

}
