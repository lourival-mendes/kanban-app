package br.com.kanbanquarkus.projection;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedResponse<T> {

    private List<T> resultados;
    private long totalRegistros;
    private int totalPaginas;
    private int paginaAtual;
    private int tamanhoPagina;

    public PaginatedResponse(List<T> resultados, long totalRegistros, int totalPaginas, int paginaAtual,
            int tamanhoPagina) {
        this.resultados = resultados;
        this.totalRegistros = totalRegistros;
        this.totalPaginas = totalPaginas;
        this.paginaAtual = paginaAtual;
        this.tamanhoPagina = tamanhoPagina;
    }
}