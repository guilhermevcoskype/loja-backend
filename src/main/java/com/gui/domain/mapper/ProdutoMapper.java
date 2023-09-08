package com.gui.domain.mapper;

import com.gui.domain.dto.DadosProduto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gui.domain.model.Produto;
import com.gui.domain.model.TipoProduto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class ProdutoMapper {

    @Value("${url.imagem}")
    public String urlImagem;

    public DadosProduto mapperToRecord(Produto produto) {
        if (produto == null) {
            return null;
        }
        return new DadosProduto(produto.getId().toString(), produto.getDescricao(), produto.getEstoque().toString(), produto.getPreco().toString(),
                produto.getDataInsercao().toString(), produto.getUrlImagem(), produto.getTipoProduto().getTipo());

    }

    public Produto mapperToProduto(DadosProduto dadosProduto, String... fileSaver) {
        if (dadosProduto == null) {
            return null;
        }
        Produto produto = new Produto();
        if (dadosProduto.id() != null) {
            produto.setId(Long.valueOf(dadosProduto.id()));
        }
        produto.setDataInsercao(Date.valueOf(LocalDate.now()));
        produto.setDescricao(dadosProduto.descricao());
        produto.setEstoque(Long.valueOf(dadosProduto.estoque()));
        produto.setPreco(new BigDecimal(dadosProduto.preco()));
        produto.setTipoProduto(TipoProduto.valueOf(dadosProduto.tipoProduto().toUpperCase()));
        produto.setUrlImagem(fileSaver.length > 0 ? urlImagem+fileSaver[0] : dadosProduto.urlImagem());
        return produto;
    }

}