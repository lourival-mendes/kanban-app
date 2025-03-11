package br.com.kanbanquarkus.projection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PaginatedResponseTest {

    @Test
    void testPaginatedResponse() {
        List<String> resultados = Arrays.asList("resultado1", "resultado2");
        long totalRegistros = 100L;
        int totalPaginas = 10;
        int paginaAtual = 1;
        int tamanhoPagina = 10;

        PaginatedResponse<String> response = new PaginatedResponse<>(resultados, totalRegistros, totalPaginas,
                paginaAtual, tamanhoPagina);

        // Verificação dos valores dos campos
        assertEquals(resultados, response.getResultados());
        assertEquals(totalRegistros, response.getTotalRegistros());
        assertEquals(totalPaginas, response.getTotalPaginas());
        assertEquals(paginaAtual, response.getPaginaAtual());
        assertEquals(tamanhoPagina, response.getTamanhoPagina());
    }

    @Test
    void testSetResultados() {
        PaginatedResponse<String> response = new PaginatedResponse<>(null, 0, 0, 0, 0);
        List<String> resultados = Arrays.asList("resultado1", "resultado2");
        response.setResultados(resultados);
        assertEquals(resultados, response.getResultados());
    }

    @Test
    void testSetTotalRegistros() {
        PaginatedResponse<String> response = new PaginatedResponse<>(null, 0, 0, 0, 0);
        long totalRegistros = 100L;
        response.setTotalRegistros(totalRegistros);
        assertEquals(totalRegistros, response.getTotalRegistros());
    }

    @Test
    void testSetTotalPaginas() {
        PaginatedResponse<String> response = new PaginatedResponse<>(null, 0, 0, 0, 0);
        int totalPaginas = 10;
        response.setTotalPaginas(totalPaginas);
        assertEquals(totalPaginas, response.getTotalPaginas());
    }

    @Test
    void testSetPaginaAtual() {
        PaginatedResponse<String> response = new PaginatedResponse<>(null, 0, 0, 0, 0);
        int paginaAtual = 1;
        response.setPaginaAtual(paginaAtual);
        assertEquals(paginaAtual, response.getPaginaAtual());
    }

    @Test
    void testSetTamanhoPagina() {
        PaginatedResponse<String> response = new PaginatedResponse<>(null, 0, 0, 0, 0);
        int tamanhoPagina = 10;
        response.setTamanhoPagina(tamanhoPagina);
        assertEquals(tamanhoPagina, response.getTamanhoPagina());
    }
}
