package com.gui.domain.mapper;

import org.springframework.stereotype.Component;

import com.gui.domain.model.Produto;
import com.gui.domain.model.TipoProduto;

@Component
public class ProdutoMapper {

    public com.gui.domain.dto.DadosProduto mapperToRecord(Produto produto) {
        if (produto == null) {
            return null;
        }
        return new com.gui.domain.dto.DadosProduto(produto.getId(), produto.getDescricao(), produto.getEstoque(), produto.getPreco(),
                produto.getDataInsercao(), produto.getUrlImagem(), produto.getTipoProduto().getTipo());

    }

    public Produto mapperToProduto(com.gui.domain.dto.DadosProduto dadosProduto) {
        if (dadosProduto == null) {
            return null;
        }
        Produto produto = new Produto();
        if (dadosProduto.id() != null) {
            produto.setId(dadosProduto.id());
        }
        produto.setDataInsercao(dadosProduto.dataInsercao());
        produto.setDescricao(dadosProduto.descricao());
        produto.setEstoque(dadosProduto.estoque());
        produto.setPreco(dadosProduto.preco());
        produto.setTipoProduto(TipoProduto.valueOf(dadosProduto.tipoProduto()));
        produto.setUrlImagem(dadosProduto.urlImagem());
        return produto;
    }

}
