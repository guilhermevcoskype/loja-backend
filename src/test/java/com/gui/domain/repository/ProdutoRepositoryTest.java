package com.gui.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") //o que tem depois do traço no properties vai ser acionado

public class ProdutoRepositoryTest {
    @Test
    void testFindProdutoDaBusca() {

    }

    @Test
    void testFindUltimosLancamentos() {

    }
}
