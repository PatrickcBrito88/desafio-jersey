package org.brito.desafiojersey.utils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PaginadorUtils {

    public static <T> Page<T> gerarPaginacao(List<T> lista, Integer paginaAtual, Integer tamanhoPagina) {
        int numeroPagina = Objects.nonNull(paginaAtual) ? paginaAtual : 0;
        int tamanhoPaginacao = (Objects.nonNull(tamanhoPagina) && tamanhoPagina >= 1) ? tamanhoPagina : 10;
        int indiceInicial = numeroPagina * tamanhoPaginacao;

        if (indiceInicial >= lista.size()) {
            return new Page<>(Collections.emptyList(), numeroPagina, tamanhoPaginacao, 0);
        }

        int indiceFinal = Math.min(indiceInicial + tamanhoPaginacao, lista.size());
        List<T> paginatedList = lista.subList(indiceInicial, indiceFinal);

        return new Page<>(paginatedList, numeroPagina, tamanhoPaginacao, lista.size());
    }
}
