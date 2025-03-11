package br.com.kanbanquarkus.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mongodb.client.ListCollectionNamesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import jakarta.ws.rs.core.Response;

class MongoResourceTest {

    @Mock
    MongoClient mongoClient;

    @Mock
    MongoDatabase mongoDatabase;

    @Mock
    ListCollectionNamesIterable mongoIterable;

    @InjectMocks
    MongoResource mongoResource;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        when(mongoClient.getDatabase("kanbandb")).thenReturn(mongoDatabase);
        when(mongoDatabase.listCollectionNames()).thenReturn(mongoIterable);
        when(mongoIterable.first()).thenReturn("collectionName");
    }

    @Test
    void testCheckMongoConnectionSuccess() {
        Response response = mongoResource.checkMongoConnection();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Conexão com o MongoDB bem-sucedida!", response.getEntity());
    }

    @Test
    void testCheckMongoConnectionFailure() {
        when(mongoDatabase.listCollectionNames()).thenThrow(new RuntimeException("Erro de conexão"));

        Response response = mongoResource.checkMongoConnection();

        assertEquals(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), response.getStatus());
        assertEquals("Falha ao conectar ao MongoDB: Erro de conexão", response.getEntity());
    }
}
