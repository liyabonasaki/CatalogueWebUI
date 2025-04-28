package com.payu.CatalogueWebUI.service;

import com.payu.CatalogueWebUI.dto.BookDto;
import jakarta.ws.rs.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.List;

@Service
public class BookService {

    private Client client;
    private WebTarget baseTarget;

    @Value("${catalogue.management.url}")
    private String catalogueManagementUrl;


    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JacksonJsonProvider provider = new JacksonJsonProvider();
        provider.setMapper(mapper);

        client = ClientBuilder.newClient().register(provider);
        baseTarget = client.target(catalogueManagementUrl);
    }

    public List<BookDto> getAllBooks() {
        return baseTarget.path("getAllBooks")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<BookDto>>() {
                });
    }

    public BookDto addBook(BookDto book) {
        book.setId(null);  // Ensure ID is not sent for new books
        Response response = baseTarget.path("addBook")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(BookDto.class);
        } else {
            System.out.println("Failed to add book. Response: " + response.getStatus());
            return null;  // Or handle accordingly
        }
    }


    public BookDto getBookById(Long id) {
        return baseTarget.path("getAllBooks") // getAll used instead due to no /{id} endpoint in management
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<BookDto>>() {
                })
                .stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    public BookDto updateBook(Long id, BookDto book) {
        return baseTarget.path("updateBook/" + id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(book, MediaType.APPLICATION_JSON), BookDto.class);
    }

    public void deleteBook(Long id) {
        baseTarget.path("deleteBook/" + id)
                .request()
                .delete();
    }

}
