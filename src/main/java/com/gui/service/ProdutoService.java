package com.gui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gui.model.Produto;
import com.gui.model.TipoProduto;
import com.gui.repository.JPQLQueries;
import com.gui.repository.ProdutoRepository;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private JPQLQueries jPQLQueries;

	public Iterable<Produto> obterTodos(){
		Iterable<Produto> produtos = repository.findAll();
		return produtos;
	}

	public Produto obterPorCodigo(Long codigo){
		return repository.findById(codigo).orElse(null);
	}

	public Page<Produto> obterPorTipo(TipoProduto tipoProduto, Pageable paginacao){
		return repository.findByTipoProduto(tipoProduto, paginacao);
	}

	@Cacheable(value="ultimosLancamentos") //guarda o retorno da função no cache
	public List<Produto> obterUltimosLançamentos(){
		return jPQLQueries.findUltimosLancamentos();
	}

	@CacheEvict(value="ultimosLancamentos")//limpa o cache ao fazer esse método
	public void salvar(Produto produto){
		repository.save(produto);
	}
	
	public Page<Produto> buscador(String busca, Pageable paginacao) {
		return repository.findProdutoDaBusca(busca, paginacao);
	}

}
