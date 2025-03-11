package br.com.kanbanquarkus.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.kanbanquarkus.dto.PessoaDTO;
import br.com.kanbanquarkus.projection.PaginatedResponse;
import br.com.kanbanquarkus.projection.PessoaProjection;
import br.com.kanbanquarkus.services.PessoaService;
import jakarta.ws.rs.core.Response;

class PessoaResourceTest {

    @Mock
    PessoaService pessoaService;

    @InjectMocks
    PessoaResource pessoaResource;

    private PessoaDTO pessoaDTO;
    private PessoaProjection pessoaProjection;
    private List<PessoaProjection> pessoaProjectionList;
    private PaginatedResponse<PessoaProjection> paginatedResponse;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        pessoaDTO = new PessoaDTO("nome", 18, "cidade", "profissao", "email@example.com", null);
        pessoaProjection = new PessoaProjection("nome", 18, "cidade", "profissao");
        pessoaProjectionList = Arrays.asList(pessoaProjection);
        paginatedResponse = new PaginatedResponse<>(pessoaProjectionList, 1L, 1, 1, 10);

        // when(pessoaService.listAll()).thenReturn(pessoaProjectionList);
        when(pessoaService.buscarPessoas(any(PessoaDTO.class), anyInt(), anyInt())).thenReturn(paginatedResponse);
        // when(pessoaService.findById(anyString())).thenReturn(pessoaProjection);
    }

    // @Test
    void testListAll() {
        Response response = pessoaResource.listAll();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(pessoaProjectionList, response.getEntity());
    }

    @Test
    void testFilter() {
        PaginatedResponse<PessoaProjection> response = pessoaResource.filter(pessoaDTO, 1, 10);

        assertNotNull(response);
        assertEquals(paginatedResponse, response);
    }

    // @Test
    void testFindById() {
        Response response = pessoaResource.findById("id");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(pessoaProjection, response.getEntity());
    }

    @Test
    void testDeletePessoa() {
        pessoaResource.deletePessoa("id");

        verify(pessoaService, times(1)).deleteById("id");
    }

    @Test
    void testUpdate() {
        pessoaResource.update("id", pessoaDTO);

        verify(pessoaService, times(1)).update("id", pessoaDTO);
    }

    @Test
    void testPersist() {
        pessoaResource.persist(pessoaDTO);

        verify(pessoaService, times(1)).persist(pessoaDTO);
    }
}
