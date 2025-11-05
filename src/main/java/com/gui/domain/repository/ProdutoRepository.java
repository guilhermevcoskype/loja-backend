package com.gui.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gui.domain.model.Produto;
import com.gui.domain.model.TipoProduto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findByTipoProduto(TipoProduto tipoProduto, Pageable paginacao);

    @Query("""
                SELECT p
                FROM Produto p
                WHERE LOWER(p.descricao) LIKE LOWER(CONCAT('%', :buscado, '%'))
            """)
    Page<Produto> findProdutoDaBusca(String buscado, Pageable pageable);

    @Query("""
            SELECT p
            FROM Produto p
            ORDER BY p.dataInsercao
            desc
            """)
    Page<Produto> findUltimosLancamentos(Pageable page);

}
