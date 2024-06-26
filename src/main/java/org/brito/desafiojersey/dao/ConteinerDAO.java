package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Conteiner;

import java.util.List;

public interface ConteinerDAO {

    long salvarContainer(Conteiner conteinerDTO);

    Conteiner buscarContainerPorId(long id);

    Integer atualizarContainer(Conteiner conteinerDTO, Integer id);

    void deletarContainer(Integer id);

    List<Conteiner> listarContaineres();

    List<Conteiner> listaConteineresPorCliente(long idCliente);
}
