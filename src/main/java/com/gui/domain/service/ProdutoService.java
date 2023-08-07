package com.gui.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gui.domain.dto.ProdutoDTO;
import com.gui.domain.model.TipoProduto;
import com.gui.domain.repository.JPQLQueries;
import com.gui.domain.repository.ProdutoRepository;
import com.gui.infra.FileSaver;
import com.gui.infra.exception.ProdutoException;
import com.gui.infra.mapper.ProdutoMapper;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private JPQLQueries jPQLQueries;

	@Autowired
	private ProdutoMapper produtoMapper;

	@Autowired
	private FileSaver fileSaver;

	public Page<ProdutoDTO> obterTodos(Pageable pageable) {
		return repository.findAll(pageable).map(produtoMapper::mapperToDto);
	}

	public ProdutoDTO buscarProdutoPorCodigo(Long codigo) {
		return repository.findById(codigo).map(produtoMapper::mapperToDto)
				.orElseThrow(() -> new ProdutoException(codigo));
	}

	public ProdutoDTO editarProduto(Long id, ProdutoDTO produto) {

		return repository.findById(id).map(retorno -> {
			retorno.setDataInsercao(produto.dataInsercao());
			retorno.setDescricao(produto.descricao());
			retorno.setEstoque(produto.estoque());
			retorno.setPreco(produto.preco());
			retorno.setTipoProduto(TipoProduto.valueOf(produto.tipoProduto()));
			retorno.setUrlImagem(produto.urlImagem());
			return produtoMapper.mapperToDto(repository.save(retorno));
		}).orElseThrow(() -> new ProdutoException(id));
	}

	/*
	 * @Cacheable(value = "ultimosLancamentos") // guarda o retorno da função no
	 * cache
	 * public List<Produto> obterUltimosLançamentos() {
	 * return jPQLQueries.findUltimosLancamentos();
	 * }
	 */

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public ProdutoDTO salvarProduto(MultipartFile file, ProdutoDTO produtoDTO) {
		// return
		// produtoMapper.mapperToDto(repository.save(produtoMapper.mapperToProduto(produto)));

		fileSaver.write(file);

		return produtoMapper.mapperToDto(repository.save(produtoMapper.mapperToProduto(produtoDTO)));

	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public ProdutoDTO salvarProdutoSemFile(ProdutoDTO produtoDTO) {
		// return
		// produtoMapper.mapperToDto(repository.save(produtoMapper.mapperToProduto(produto)));

		return produtoMapper.mapperToDto(repository.save(produtoMapper.mapperToProduto(produtoDTO)));

	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public void removerProduto(Long id) {

		repository.delete(repository.findById(id).orElseThrow(() -> new ProdutoException(id)));

	}

	public Page<ProdutoDTO> obterPorTipo(TipoProduto tipoProduto, Pageable paginacao) {
		return repository.findByTipoProduto(tipoProduto, paginacao).map(produtoMapper::mapperToDto);
	}

	public Page<ProdutoDTO> buscador(String busca, Pageable paginacao) {
		return repository.findProdutoDaBusca(busca, paginacao).map(produtoMapper::mapperToDto);
	}

}
