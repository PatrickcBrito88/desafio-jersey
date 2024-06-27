package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ConteinerDAO;
import org.brito.desafiojersey.dao.MovimentacaoDAO;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.domain.Movimentacao;
import org.brito.desafiojersey.dtos.MovimentacaoDTO;
import org.brito.desafiojersey.services.MovimentacaoService;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.Page;
import org.brito.desafiojersey.utils.PaginadorUtils;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

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
        Movimentacao movimentacao = movimentacaoDAO.fecharMovimentacao(id);
        Conteiner conteiner = conteinerDAO.buscarContainerPorId(movimentacao.getConteiner().getId());
        movimentacao.setConteiner(conteiner);
        return modelMapper.map(movimentacao, MovimentacaoDTO.class);
    }

    @Override
    public Page<MovimentacaoDTO> listaMovimentacoes(Integer paginaAtual, Integer tamanhoPagina) {
        List<Movimentacao> movimentacoes = movimentacaoDAO.listaMovimentacoes();
        List<MovimentacaoDTO> movimentacoesDtos = movimentacoes.stream()
                .map(u -> modelMapper.map(u, MovimentacaoDTO.class))
                .toList();
        return PaginadorUtils.gerarPaginacao(movimentacoesDtos, paginaAtual, tamanhoPagina);
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
