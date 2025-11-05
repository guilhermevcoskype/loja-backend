package com.gui.controller;

import com.gui.domain.dto.DadosProdutoDTO;
import com.gui.domain.model.TipoProduto;
import com.gui.domain.service.ProdutoService;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProdutosControllerTest {

    @Autowired
    private MockMvc requestMockMvc;

    @Autowired
    private JacksonTester<DadosProdutoDTO> produtoJson;

    @Autowired
    private JacksonTester<Page> pageJson;

    private DadosProdutoDTO dadosProduto = new DadosProdutoDTO("1", "camisa", "22", "22.2", Date.valueOf(LocalDate.now()).toString(), "teste", TipoProduto.CAMISA.getTipo());

    private MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());

    @MockitoBean
    private ProdutoService produtoService;

    @Test
    @DisplayName("Deve retornar o status NO_CONTENT")
    void testObterTodosRetornandoNoContent() throws Exception {

        Mockito.when(produtoService.obterTodos(any())).thenReturn(null);
        var response = requestMockMvc.perform(get("/produtos/todos")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve retornar uma page de produtos igual ao passado pelo serviço e o status OK")
    void testObterTodosRetornandoOk() throws Exception {
        var pageable = PageRequest.of(0, 10);
        var listDadosProduto = List.of(dadosProduto);
        var page = new PageImpl<>(listDadosProduto, pageable, listDadosProduto.size());
        Mockito.when(produtoService.obterTodos(any())).thenReturn(page);
        var response = requestMockMvc.perform(get("/produtos/todos").contentType(MediaType.APPLICATION_JSON).content(pageable.toString())).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JSONObject jsonEsperado = new JSONObject(pageJson.write(page).getJson());
        JSONObject jsonRetornado = new JSONObject(response.getContentAsString());
        Assertions.assertThat(jsonRetornado.get("content").toString()).isEqualTo(jsonEsperado.get("content").toString());
    }

    @Test
    @DisplayName("Deve retornar uma exceção IllegalArgumentException")
    @WithMockUser(username = "guidovale@gmail.com", password = "soubom", roles = {"ADMIN"})
    void testSalvarSemConteudo() throws Exception {

        assertThrows(IllegalArgumentException.class, () -> {
            requestMockMvc.perform(MockMvcRequestBuilders.multipart("/produtos")
                    .file(null)
                    .param("dadosProduto", produtoJson.write(dadosProduto).getJson().toString())).andReturn().getResponse();
        });

    }

    @Test
    @DisplayName("Deve retornar um status CREATED e retornar o json enviado.")
    @WithMockUser(username = "guidovale@gmail.com", password = "soubom", roles = {"ADMIN"})
    void testSalvarComConteudo() throws Exception {

        Mockito.when(produtoService.salvarProduto(any(), any())).thenReturn(dadosProduto);

        var response = requestMockMvc.perform(MockMvcRequestBuilders.multipart("/produtos")
                .file(file)
                .param("dadosProduto", produtoJson.write(dadosProduto).getJson().toString())).andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(produtoJson.write(dadosProduto).getJson());
    }

    @Test
    @DisplayName("Deve retornar um json e o status ok.")
    void testarBuscaERetornarResultado() throws Exception {
        var pageable = PageRequest.of(0, 10);
        var listDadosProduto = List.of(dadosProduto);
        var page = new PageImpl<>(listDadosProduto, pageable, listDadosProduto.size());
        Mockito.when(produtoService.buscador(any(), any())).thenReturn(page);
        var response = requestMockMvc.perform(get("/produtos/busca").param("busca", "dadoBuscado")
                .param("page", "0")
                .param("size", "10")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JSONObject jsonEsperado = new JSONObject(pageJson.write(page).getJson());
        JSONObject jsonRetornado = new JSONObject(response.getContentAsString());
        Assertions.assertThat(jsonRetornado.get("content").toString()).isEqualTo(jsonEsperado.get("content").toString());
    }

    @Test
    @DisplayName("Deve retornar o status NO_CONTENT")
    void testobterUltimosLancamentosRetornandoNoContent() throws Exception {
        Mockito.when(produtoService.obterUltimosLançamentos(any())).thenReturn(null);
        var response = requestMockMvc.perform(get("/produtos")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deve retornar uma page de produtos igual ao passado pelo serviço e o status OK")
    void testobterUltimosLancamentosRetornandoOk() throws Exception {
        var pageable = PageRequest.of(0, 10);
        var listDadosProduto = List.of(dadosProduto);
        var page = new PageImpl<>(listDadosProduto, pageable, listDadosProduto.size());
        Mockito.when(produtoService.obterUltimosLançamentos(any())).thenReturn(page);
        var response = requestMockMvc.perform(get("/produtos")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JSONObject jsonEsperado = new JSONObject(pageJson.write(page).getJson());
        JSONObject jsonRetornado = new JSONObject(response.getContentAsString());
        Assertions.assertThat(jsonRetornado.get("content").toString()).isEqualTo(jsonEsperado.get("content").toString());
    }

    @Test
    @DisplayName("Deve retornar o produto")
    void testdetalharProdutoEAchar() throws Exception {

        Mockito.when(produtoService.buscarProdutoPorCodigo(any())).thenReturn(dadosProduto);
        var response = requestMockMvc.perform(get("/produtos/1")).andReturn().getResponse();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(produtoJson.write(dadosProduto).getJson().toString());
    }

    @Test
    @DisplayName("Deve retornar o produto e status ok")
    void testObterProdutosPorTipoERetornarValor() throws Exception {
        var pageable = PageRequest.of(0, 10);
        var listDadosProduto = List.of(dadosProduto);
        var page = new PageImpl<>(listDadosProduto, pageable, listDadosProduto.size());
        Mockito.when(produtoService.obterPorTipo(any(), any())).thenReturn(page);
        var response = requestMockMvc.perform(get("/produtos/buscarProdutoPorTipo").param("tipoProduto", "CAMISA")
                .param("page", "0")
                .param("size", "10")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JSONObject jsonEsperado = new JSONObject(pageJson.write(page).getJson());
        JSONObject jsonRetornado = new JSONObject(response.getContentAsString());
        Assertions.assertThat(jsonRetornado.get("content").toString()).isEqualTo(jsonEsperado.get("content").toString());
    }
}
