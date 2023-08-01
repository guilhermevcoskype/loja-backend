package com.gui.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gui.dto.ProdutoDTO;
import com.gui.exception.ProdutoException;
import com.gui.mapper.ProdutoMapper;
import com.gui.model.Produto;
import com.gui.model.TipoProduto;
import com.gui.repository.JPQLQueries;
import com.gui.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository repository;

	private JPQLQueries jPQLQueries;

	private ProdutoMapper produtoMapper;

	public ProdutoService(JPQLQueries jPQLQueries, ProdutoRepository repository, ProdutoMapper produtoMapper) {
		this.jPQLQueries = jPQLQueries;
		this.repository = repository;
		this.produtoMapper = produtoMapper;
	}

	/*
	 * public Iterable<Produto> obterTodos() {
	 * Iterable<Produto> produtos = repository.findAll();
	 * return produtos;
	 * }
	 */

	public List<ProdutoDTO> obterTodos() {
		return repository.findAll().stream().map(produtoMapper::mapperToDto).collect(Collectors.toList());
	}

	public ProdutoDTO buscarProdutoPorCodigo(Long codigo) {
		return repository.findById(codigo).map(produtoMapper::mapperToDto)
				.orElseThrow(() -> new ProdutoException(codigo));
	}

	public ProdutoDTO editarProduto(Long codigo, ProdutoDTO produto) {

		return repository.findById(codigo).map(retorno -> {
			retorno.setDataInsercao(produto.dataInsercao());
			retorno.setDescricao(produto.descricao());
			retorno.setEstoque(produto.estoque());
			retorno.setPreco(produto.preco());
			retorno.setTipoProduto(produto.tipoProduto());
			retorno.setUrlImagem(produto.urlImagem());
			return produtoMapper.mapperToDto(repository.save(retorno));
		}).orElseThrow(() -> new ProdutoException(codigo));
	}

	/*
	 * @Cacheable(value = "ultimosLancamentos") // guarda o retorno da função no
	 * cache
	 * public List<Produto> obterUltimosLançamentos() {
	 * return jPQLQueries.findUltimosLancamentos();
	 * }
	 */

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public ProdutoDTO salvarProduto(ProdutoDTO produto) {
		return produtoMapper.mapperToDto(repository.save(produtoMapper.mapperToProduto(produto)));
	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public void removerProduto(Long codigo) {

		repository.delete(repository.findById(codigo).orElseThrow(() -> new ProdutoException(codigo)));

		// pode ser assim também
		/*
		 * repository.findById(codigo).map(retorno -> {
		 * repository.deleteById(codigo);
		 * return true; // tem que retornar algo
		 * }).orElseThrow(() -> new ProdutoException(codigo));
		 */

	}

	public Page<Produto> obterPorTipo(TipoProduto tipoProduto, Pageable paginacao) {
		return repository.findByTipoProduto(tipoProduto, paginacao);
	}

	public Page<Produto> buscador(String busca, Pageable paginacao) {
		return repository.findProdutoDaBusca(busca, paginacao);
	}

}
