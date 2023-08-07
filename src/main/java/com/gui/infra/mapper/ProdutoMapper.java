package com.gui.infra.mapper;

import org.springframework.stereotype.Component;

import com.gui.domain.dto.ProdutoDTO;
import com.gui.domain.model.Produto;
import com.gui.domain.model.TipoProduto;

@Component
public class ProdutoMapper {

    public ProdutoDTO mapperToDto(Produto produto) {
        if (produto == null) {
            return null;
        }
        return new ProdutoDTO(produto.getId(), produto.getDescricao(), produto.getEstoque(), produto.getPreco(),
                produto.getDataInsercao(), produto.getUrlImagem(), produto.getTipoProduto().getTipo());

    }

    public Produto mapperToProduto(ProdutoDTO produtoDto) {
        if (produtoDto == null) {
            return null;
        }
        Produto produto = new Produto();
        if (produtoDto.id() != null) {
            produto.setId(produtoDto.id());
        }
        produto.setDataInsercao(produtoDto.dataInsercao());
        produto.setDescricao(produtoDto.descricao());
        produto.setEstoque(produtoDto.estoque());
        produto.setPreco(produtoDto.preco());
        produto.setTipoProduto(TipoProduto.valueOf(produtoDto.tipoProduto()));
        produto.setUrlImagem(produtoDto.urlImagem());
        return produto;
    }

}
