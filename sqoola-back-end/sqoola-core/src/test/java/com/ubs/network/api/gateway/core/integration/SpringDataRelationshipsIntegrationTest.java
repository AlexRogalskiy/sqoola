/*
 * The MIT License
 *
 * Copyright 2018 WildBees Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ubs.network.api.gateway.core.integration;

import com.baeldung.SpringDataRestApplication;
import com.baeldung.models.Address;
import com.baeldung.models.Author;
import com.baeldung.models.Book;
import com.baeldung.models.Library;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.json.JSONException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataRestApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringDataRelationshipsIntegrationTest {

    @Autowired
    private TestRestTemplate template;

    private static final String BOOK_ENDPOINT = "http://localhost:8080/books/";
    private static final String AUTHOR_ENDPOINT = "http://localhost:8080/authors/";
    private static final String ADDRESS_ENDPOINT = "http://localhost:8080/addresses/";
    private static final String LIBRARY_ENDPOINT = "http://localhost:8080/libraries/";

    private static final String LIBRARY_NAME = "My Library";
    private static final String AUTHOR_NAME = "George Orwell";

    @Test
    public void whenSaveOneToOneRelationship_thenCorrect() throws JSONException {
        Library library = new Library(LIBRARY_NAME);
        template.postForEntity(LIBRARY_ENDPOINT, library, Library.class);

        Address address = new Address("Main street, nr 1");
        template.postForEntity(ADDRESS_ENDPOINT, address, Address.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(ADDRESS_ENDPOINT + "/1", requestHeaders);
        template.exchange(LIBRARY_ENDPOINT + "/1/libraryAddress", HttpMethod.PUT, httpEntity, String.class);

        ResponseEntity<Library> libraryGetResponse = template.getForEntity(ADDRESS_ENDPOINT + "/1/library", Library.class);
        assertEquals("library is incorrect", libraryGetResponse.getBody()
            .getName(), LIBRARY_NAME);
    }

    @Test
    public void whenSaveOneToManyRelationship_thenCorrect() throws JSONException{
        Library library = new Library(LIBRARY_NAME);
        template.postForEntity(LIBRARY_ENDPOINT, library, Library.class);

        Book book1 = new Book("Dune");
        template.postForEntity(BOOK_ENDPOINT, book1, Book.class);

        Book book2 = new Book("1984");
        template.postForEntity(BOOK_ENDPOINT, book2, Book.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> bookHttpEntity = new HttpEntity<>(LIBRARY_ENDPOINT + "/1", requestHeaders);
        template.exchange(BOOK_ENDPOINT + "/1/library", HttpMethod.PUT, bookHttpEntity, String.class);
        template.exchange(BOOK_ENDPOINT + "/2/library", HttpMethod.PUT, bookHttpEntity, String.class);

        ResponseEntity<Library> libraryGetResponse = template.getForEntity(BOOK_ENDPOINT + "/1/library", Library.class);
        assertEquals("library is incorrect", libraryGetResponse.getBody()
            .getName(), LIBRARY_NAME);
    }

    @Test
    public void whenSaveManyToManyRelationship_thenCorrect() throws JSONException{
        Author author1 = new Author(AUTHOR_NAME);
        template.postForEntity(AUTHOR_ENDPOINT, author1, Author.class);

        Book book1 = new Book("Animal Farm");
        template.postForEntity(BOOK_ENDPOINT, book1, Book.class);

        Book book2 = new Book("1984");
        template.postForEntity(BOOK_ENDPOINT, book2, Book.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(BOOK_ENDPOINT + "/1\n" + BOOK_ENDPOINT + "/2", requestHeaders);
        template.exchange(AUTHOR_ENDPOINT + "/1/books", HttpMethod.PUT, httpEntity, String.class);

        String jsonResponse = template.getForObject(BOOK_ENDPOINT + "/1/authors", String.class);
        JSONObject jsonObj = new JSONObject(jsonResponse).getJSONObject("_embedded");
        JSONArray jsonArray = jsonObj.getJSONArray("authors");
        assertEquals("author is incorrect", jsonArray.getJSONObject(0)
            .getString("name"), AUTHOR_NAME);
    }
}
