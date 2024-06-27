package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ClienteDAO;
import org.brito.desafiojersey.dao.ConteinerDAO;
import org.brito.desafiojersey.dao.MovimentacaoDAO;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.domain.Movimentacao;
import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.dtos.ConteinerDTO;
import org.brito.desafiojersey.dtos.ConteinerUsuarioDTO;
import org.brito.desafiojersey.exceptions.ConflitoException;
import org.brito.desafiojersey.services.ClienteService;
import org.brito.desafiojersey.services.ConteinerService;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.Page;
import org.brito.desafiojersey.utils.PaginadorUtils;
import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * Implementação da interface {@link ConteinerService}, gerenciando operações de contêineres usando DAOs.
 */
public class ConteinerServiceImpl implements ConteinerService {

    private final ConteinerDAO conteinerDAO;
    private final ModelMapper modelMapper;
    private final ClienteDAO clienteDAO;
    private final ClienteService clienteService;
    private final MovimentacaoDAO movimentacaoDAO;

    @Inject
    public ConteinerServiceImpl(ConteinerDAO conteinerDAO, ModelMapper modelMapper,
                                ClienteDAO clienteDAO, ClienteService clienteService,
                                MovimentacaoDAO movimentacaoDAO) {
        this.conteinerDAO = conteinerDAO;
        this.modelMapper = modelMapper;
        this.clienteDAO = clienteDAO;
        this.clienteService = clienteService;
        this.movimentacaoDAO = movimentacaoDAO;
    }

    @Override
    public String salvarConteiner(ConteinerDTO conteinerDTO) {
        Cliente cliente = clienteDAO.buscarClientePorId(conteinerDTO.getCliente().getId());
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
        Cliente cliente = clienteDAO.buscarClientePorId(conteinerDTO.getCliente().getId());
        Conteiner conteiner = modelMapper.map(conteinerDTO, Conteiner.class);
        conteiner.setCliente(modelMapper.map(cliente, Cliente.class));
        Integer idAtualizado = conteinerDAO.atualizarContainer(conteiner, id);
        return MessageUtils.buscaValidacao("conteiner.editado.sucesso", idAtualizado);

    }

    @Override
    public String deletarConteiner(Integer id) {
        verificaSeConteinerPossuiMovimentacao(id);
        conteinerDAO.deletarContainer(id);
        return MessageUtils.buscaValidacao("conteiner.deletado.sucesso", id);
    }

    private void verificaSeConteinerPossuiMovimentacao(Integer id) {
        List<Movimentacao> listaMovimentacoes = movimentacaoDAO.listaMovimentacoesPorContainer(id);
        if (!listaMovimentacoes.isEmpty()){
            throw new ConflitoException(
                    MessageUtils.buscaValidacao("conteiner.possui.movimentacao"));
        }
    }

    @Override
    public Page<ConteinerDTO> listarConteinersPaginados(Integer paginaAtual, Integer tamanhoPagina) {
        List<Conteiner> conteineres = conteinerDAO.listarContaineres();
        List<ConteinerDTO> conteinerDTOS = conteineres.stream()
                .map(u -> modelMapper.map(u, ConteinerDTO.class))
                .toList();
        return PaginadorUtils.gerarPaginacao(conteinerDTOS, paginaAtual, tamanhoPagina);
    }

    @Override
    public ConteinerUsuarioDTO listaConteineresPorCliente(Integer idCliente) {
        List<Conteiner> conteineres = conteinerDAO.listaConteineresPorCliente(idCliente);
        List<ConteinerDTO> conteinerDTOS = conteineres.stream()
                .map(u -> modelMapper.map(u, ConteinerDTO.class))
                .toList();
        ClienteDTO clienteDTO = clienteService.buscarClientePorId(idCliente);
        return new ConteinerUsuarioDTO(clienteDTO, conteinerDTOS);
    }

}
