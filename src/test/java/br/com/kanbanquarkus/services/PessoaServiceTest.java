package br.com.kanbanquarkus.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.kanbanquarkus.dao.PessoaDao;
import br.com.kanbanquarkus.dto.PessoaDTO;
import br.com.kanbanquarkus.model.Pessoa;
import br.com.kanbanquarkus.projection.PaginatedResponse;
import br.com.kanbanquarkus.projection.PessoaProjection;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

// @QuarkusTest
public class PessoaServiceTest {

 


}
