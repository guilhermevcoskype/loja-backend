package com.gui.domain.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import com.gui.domain.model.Produto;
import com.gui.domain.model.TipoProduto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") //o que tem depois do traço no properties vai ser acionado

public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deve retornar uma lista de produtos com descrição equivalente à string enviada")
    void findProdutoDaBuscaRetornaResultado() {

        var produto = new Produto(null, "camisa", Long.valueOf(22), new BigDecimal(22.2), Date.valueOf(LocalDate.now()), "teste", TipoProduto.CAMISA);
        var pageable = PageRequest.of(0, 10, Sort.by("descricao").ascending());

        cadastraProduto(produto);

        List<Produto> listaProdutos = produtoRepository.findProdutoDaBusca("camisa", pageable).getContent();
        Assertions.assertThat(listaProdutos.stream().map(retorno -> retorno.getDescricao())).contains("camisa");
    }

    private void cadastraProduto(Produto produto) {
        em.persist(produto);
    }

}
