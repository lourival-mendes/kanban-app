package br.com.kanbanquarkus.resources;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("mongo")
public class MongoResource {

    private final MongoClient mongoClient;

    MongoResource(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkMongoConnection() {
        System.out.println(">>> mongoDB conectado!");

        try {
            MongoDatabase database = mongoClient.getDatabase("kanbandb");
            database.listCollectionNames().first();
            return Response.ok("Conexão com o MongoDB bem-sucedida!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("Falha ao conectar ao MongoDB: " + e.getMessage())
                    .build();
        }
    }
}