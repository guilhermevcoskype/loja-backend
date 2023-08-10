package com.gui.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gui.domain.mapper.ProdutoMapper;
import com.gui.domain.model.Produto;
import com.gui.domain.model.TipoProduto;
import com.gui.domain.repository.ProdutoRepository;
import com.gui.domain.utils.FileSaver;
import com.gui.infra.exception.ProdutoException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ProdutoMapper produtoMapper;

	@Autowired
	private FileSaver fileSaver;

	public Page<com.gui.domain.dto.DadosProduto> obterTodos(Pageable pageable) {
		return repository.findAll(pageable).map(produtoMapper::mapperToRecord);
	}

	public com.gui.domain.dto.DadosProduto buscarProdutoPorCodigo(Long codigo) {
		return repository.findById(codigo).map(produtoMapper::mapperToRecord)
				.orElseThrow(() -> new ProdutoException(codigo));
	}

	public com.gui.domain.dto.DadosProduto editarProduto(Long id, com.gui.domain.dto.DadosProduto produto) {

		return repository.findById(id).map(retorno -> {
			retorno.setDataInsercao(produto.dataInsercao());
			retorno.setDescricao(produto.descricao());
			retorno.setEstoque(produto.estoque());
			retorno.setPreco(produto.preco());
			retorno.setTipoProduto(TipoProduto.valueOf(produto.tipoProduto()));
			retorno.setUrlImagem(produto.urlImagem());
			return produtoMapper.mapperToRecord(repository.save(retorno));
		}).orElseThrow(() -> new ProdutoException(id));
	}

	@Cacheable(value = "ultimosLancamentos") // guarda o retorno da função no
	public List<Produto> obterUltimosLançamentos() {
		return repository.findUltimosLancamentos();
	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public com.gui.domain.dto.DadosProduto salvarProduto(MultipartFile file, com.gui.domain.dto.DadosProduto produtoDTO) {

		fileSaver.write(file);

		return produtoMapper.mapperToRecord(repository.save(produtoMapper.mapperToProduto(produtoDTO)));

	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public com.gui.domain.dto.DadosProduto salvarProdutoSemFile(com.gui.domain.dto.DadosProduto produtoDTO) {

		return produtoMapper.mapperToRecord(repository.save(produtoMapper.mapperToProduto(produtoDTO)));

	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public void removerProduto(Long id) {
		repository.delete(repository.findById(id).orElseThrow(() -> new ProdutoException(id)));
	}

	public Page<com.gui.domain.dto.DadosProduto> obterPorTipo(TipoProduto tipoProduto, Pageable paginacao) {
		return repository.findByTipoProduto(tipoProduto, paginacao).map(produtoMapper::mapperToRecord);
	}

	public Page<com.gui.domain.dto.DadosProduto> buscador(String buscado, Pageable paginacao) {
		return repository.findProdutoDaBusca(buscado, paginacao).map(produtoMapper::mapperToRecord);
	}

}
