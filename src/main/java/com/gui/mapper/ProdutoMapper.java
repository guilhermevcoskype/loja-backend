package com.gui.mapper;

import org.springframework.stereotype.Component;

import com.gui.dto.ProdutoDTO;
import com.gui.model.Produto;

@Component
public class ProdutoMapper {

    public ProdutoDTO mapperToDto(Produto produto) {
        if (produto == null) {
            return null;
        }
        return new ProdutoDTO(produto.getCodigo(), produto.getDescricao(), produto.getEstoque(), produto.getPreco(),
                produto.getDataInsercao(), produto.getUrlImagem(), produto.getTipoProduto());

    }

    public Produto mapperToProduto(ProdutoDTO produtoDto) {
        if (produtoDto == null) {
            return null;
        }
        Produto produto = new Produto();
        if (produtoDto.codigo() != null) {
            produto.setCodigo(produtoDto.codigo());
        }
        produto.setDataInsercao(produtoDto.dataInsercao());
        produto.setDescricao(produtoDto.descricao());
        produto.setEstoque(produtoDto.estoque());
        produto.setPreco(produtoDto.preco());
        produto.setTipoProduto(produtoDto.tipoProduto());
        produto.setUrlImagem(produtoDto.urlImagem());
        return produto;
    }

}
