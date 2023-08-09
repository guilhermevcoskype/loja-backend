package com.gui.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gui.domain.model.Produto;
import com.gui.domain.model.TipoProduto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public Page<Produto> findByTipoProduto(TipoProduto tipoProduto, Pageable paginacao);

	@Query("""
			FROM Produto p
			WHERE LOWER(p.descricao) like %:buscado%
			""")
	Page<Produto> findProdutoDaBusca(String buscado, Pageable pageable);

	@Query("""
			SELECT p FROM Produto p
			ORDER BY p.dataInsercao desc
			limit 5
			""")
	public List<Produto> findUltimosLancamentos();

}
