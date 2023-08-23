package com.gui.domain.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gui.domain.dto.DadosProduto;
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

	public Page<DadosProduto> obterTodos(Pageable pageable) {
		return repository.findAll(pageable).map(produtoMapper::mapperToRecord);
	}

	public DadosProduto buscarProdutoPorCodigo(Long codigo) {
		return repository.findById(codigo).map(produtoMapper::mapperToRecord)
				.orElseThrow(() -> new ProdutoException(codigo));
	}

	public DadosProduto editarProduto(Long id, DadosProduto produto) {

		return repository.findById(id).map(retorno -> {
			retorno.setDataInsercao(Date.valueOf(produto.dataInsercao()));
			retorno.setDescricao(produto.descricao());
			retorno.setEstoque(Long.valueOf(produto.estoque()));
			retorno.setPreco(new BigDecimal(produto.preco()));
			retorno.setTipoProduto(TipoProduto.valueOf(produto.tipoProduto()));
			retorno.setUrlImagem(produto.urlImagem());
			return produtoMapper.mapperToRecord(repository.save(retorno));
		}).orElseThrow(() -> new ProdutoException(id));
	}

	@Cacheable(value = "ultimosLancamentos") // guarda o retorno da função no
	public Page<DadosProduto> obterUltimosLançamentos(Pageable paginacao) throws ProdutoException{
		return repository.findUltimosLancamentos(paginacao).map(produtoMapper::mapperToRecord);
	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public DadosProduto salvarProduto(MultipartFile file, DadosProduto dadosProduto) {

		if(!file.getOriginalFilename().equals("blob")){
			return produtoMapper.mapperToRecord(repository.save(produtoMapper.mapperToProduto(dadosProduto, fileSaver.write(file))));
		}

		return produtoMapper.mapperToRecord(repository.save(produtoMapper.mapperToProduto(dadosProduto)));

	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public DadosProduto salvarProdutoSemFile(DadosProduto dadosProduto) {

		return produtoMapper.mapperToRecord(repository.save(produtoMapper.mapperToProduto(dadosProduto)));

	}

	@CacheEvict(value = "ultimosLancamentos") // limpa o cache ao fazer esse método
	public void removerProduto(Long id) {
		repository.delete(repository.findById(id).orElseThrow(() -> new ProdutoException(id)));
	}

	public Page<DadosProduto> obterPorTipo(TipoProduto tipoProduto, Pageable paginacao) {
		return repository.findByTipoProduto(tipoProduto, paginacao).map(produtoMapper::mapperToRecord);
	}

	public Page<DadosProduto> buscador(String buscado, Pageable paginacao) {
		return repository.findProdutoDaBusca(buscado, paginacao).map(produtoMapper::mapperToRecord);
	}

}
