package com.gui.controller;

import com.gui.domain.dto.DadosProduto;
import com.gui.domain.model.TipoProduto;
import com.gui.domain.service.ProdutoService;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProdutosControllerTest {

    @Autowired
    private MockMvc requestMockMvc;

    @Autowired
    private JacksonTester<DadosProduto> produtoJson;

    @Autowired
    private JacksonTester<Page> pageJson;

    private DadosProduto dadosProduto = new DadosProduto(Long.valueOf(1), "camisa", Long.valueOf(22), new BigDecimal(22.2), Date.valueOf(LocalDate.now()), "teste", TipoProduto.CAMISA.getTipo());

    @MockBean
    private ProdutoService produtoService;


    @Test
    @WithMockUser
    @DisplayName("Deve retornar o status NO_CONTENT")
    void testObterTodosRetornandoNoContent() throws Exception {

        Mockito.when(produtoService.obterTodos(any())).thenReturn(null);
        var response = requestMockMvc.perform(get("/produtos")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Deve retornar uma page de produtos igual ao passado pelo serviço e o status OK")
    void testObterTodosRetornandoOk() throws Exception {
        var pageable = PageRequest.of(0, 10);
        var listDadosProduto = List.of(dadosProduto);
        var page = new PageImpl<>(listDadosProduto, pageable, listDadosProduto.size());
        Mockito.when(produtoService.obterTodos(any())).thenReturn(page);
        var response = requestMockMvc.perform(get("/produtos").contentType(MediaType.APPLICATION_JSON).content(pageable.toString())).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JSONObject jsonEsperado = new JSONObject(pageJson.write(page).getJson());
        JSONObject jsonRetornado = new JSONObject(response.getContentAsString());
        Assertions.assertThat(jsonRetornado.get("content").toString()).isEqualTo(jsonEsperado.get("content").toString());
    }

    @Test
    @WithMockUser
    @DisplayName("Deve retornar o produto")
    void testBuscarProdutoPorCodigoEAchar() throws Exception {

        Mockito.when(produtoService.buscarProdutoPorCodigo(any())).thenReturn(dadosProduto);
        var response = requestMockMvc.perform(get("/produtos/1")).andReturn().getResponse();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(produtoJson.write(dadosProduto).getJson().toString());
    }

    @Test
    @DisplayName("Deve retornar uma exceção BAD REQUEST")
    @WithMockUser
    void testSalvarSemFileSemConteudo() throws Exception {
        var response = requestMockMvc.perform(post("/produtos/sm")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar um status CREATED e retornar o json enviado.")
    @WithMockUser
    void testSalvarSemFileComConteudo() throws Exception {

        Mockito.when(produtoService.salvarProdutoSemFile(any())).thenReturn(dadosProduto);

        var response = requestMockMvc.perform(post("/produtos/sm").contentType(MediaType.APPLICATION_JSON).content(produtoJson.write(dadosProduto).getJson())).andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(produtoJson.write(dadosProduto).getJson());
    }

}
