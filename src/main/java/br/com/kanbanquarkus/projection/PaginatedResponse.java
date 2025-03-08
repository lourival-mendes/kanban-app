package br.com.kanbanquarkus.projection;

import java.util.List;

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

    // Getters e Setters
    public List<T> getResultados() {
        return resultados;
    }

    public void setResultados(List<T> resultados) {
        this.resultados = resultados;
    }

    public long getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(long totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public int getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(int tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }
}

// TODO: Adicionar a importação do método buscarPessoas na classe PessoaResource

