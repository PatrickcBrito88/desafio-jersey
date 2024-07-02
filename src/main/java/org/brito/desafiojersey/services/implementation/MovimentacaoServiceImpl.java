package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ConteinerDAO;
import org.brito.desafiojersey.dao.MovimentacaoDAO;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.domain.Movimentacao;
import org.brito.desafiojersey.dtos.MovimentacaoDTO;
import org.brito.desafiojersey.exceptions.MovimentacaoException;
import org.brito.desafiojersey.services.MovimentacaoService;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.Page;
import org.brito.desafiojersey.utils.PaginadorUtils;
import org.brito.desafiojersey.utils.PaginatedResponse;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Implementação da interface {@link MovimentacaoService}, gerenciando operações de movimentações usando DAOs.
 */
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final ConteinerDAO conteinerDAO;
    private final ModelMapper modelMapper;
    private final MovimentacaoDAO movimentacaoDAO;

    @Inject
    public MovimentacaoServiceImpl(ConteinerDAO conteinerDAO, ModelMapper modelMapper, MovimentacaoDAO movimentacaoDAO) {
        this.conteinerDAO = conteinerDAO;
        this.modelMapper = modelMapper;
        this.movimentacaoDAO = movimentacaoDAO;
    }


    @Override
    public String criarMovimentacao(MovimentacaoDTO movimentacaoDTO) {
        Conteiner conteiner = conteinerDAO.buscarContainerPorId(movimentacaoDTO.getConteiner().getId());
        Movimentacao movimentacao = modelMapper.map(movimentacaoDTO, Movimentacao.class);
        movimentacao.setConteiner(conteiner);
        movimentacao.setHoraInicio(LocalDateTime.now());
        long idInserido = movimentacaoDAO.criarMovimentacao(movimentacao);

        return MessageUtils.buscaValidacao("movimentacao.salva.sucesso", idInserido);
    }

    @Override
    public MovimentacaoDTO buscarMovimentacaoPorId(long id) {
        Movimentacao movimentacao = movimentacaoDAO.buscarMovimentacaoPorId(id);

        return modelMapper.map(movimentacao, MovimentacaoDTO.class);
    }

    @Override
    public MovimentacaoDTO fecharMovimentacao(long id) {
        verificaMovimentacaoFechada(id);
        Movimentacao movimentacao = movimentacaoDAO.fecharMovimentacao(id);
        Conteiner conteiner = conteinerDAO.buscarContainerPorId(movimentacao.getConteiner().getId());
        movimentacao.setConteiner(conteiner);
        return modelMapper.map(movimentacao, MovimentacaoDTO.class);
    }

    private void verificaMovimentacaoFechada(long id) {
        Movimentacao movimentacaoArmazenada = movimentacaoDAO.buscarMovimentacaoPorId(id);
        if (Objects.nonNull(movimentacaoArmazenada.getHoraFim())){
            throw new MovimentacaoException(
                    MessageUtils.buscaValidacao("movimentacao.ja.fechada"));
        }
    }

    @Override
    public PaginatedResponse<MovimentacaoDTO> listaMovimentacoes(Integer paginaAtual, Integer tamanhoPagina) throws SQLException {
        List<Movimentacao> movimentacoes = movimentacaoDAO.listaMovimentacoes(paginaAtual, tamanhoPagina);
        long totalElements = movimentacaoDAO.buscaQuantidadeTotalItens();
        List<MovimentacaoDTO> movimentacaoDTOS = movimentacoes.stream().map(m -> modelMapper.map(m, MovimentacaoDTO.class)).toList();

        return PaginatedResponse.of(movimentacaoDTOS, paginaAtual, tamanhoPagina, totalElements);
    }

    @Override
    public Page<MovimentacaoDTO> listaMovimentacoesPorContainer(Integer paginaAtual, Integer tamanhoPagina, long idConteiner) {
        List<Movimentacao> movimentacoes = movimentacaoDAO.listaMovimentacoesPorContainer(idConteiner);
        List<MovimentacaoDTO> movimentacoesDtos = movimentacoes.stream()
                .map(u -> modelMapper.map(u, MovimentacaoDTO.class))
                .toList();
        return PaginadorUtils.gerarPaginacao(movimentacoesDtos, paginaAtual, tamanhoPagina);
    }

    @Override
    public Page<MovimentacaoDTO> listaMovimentacoesPorCliente(Integer paginaAtual, Integer tamanhoPagina, long idCliente) {
        List<Movimentacao> movimentacoes = movimentacaoDAO.listaMovimentacoesPorCliente(idCliente);
        List<MovimentacaoDTO> movimentacoesDtos = movimentacoes.stream()
                .map(u -> modelMapper.map(u, MovimentacaoDTO.class))
                .toList();
        return PaginadorUtils.gerarPaginacao(movimentacoesDtos, paginaAtual, tamanhoPagina);
    }
}
