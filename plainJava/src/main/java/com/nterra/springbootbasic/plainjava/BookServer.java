package com.nterra.springbootbasic.plainjava;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookServer {

  private static List<Book> books = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(8079), 0);
    server.createContext("/books", new BookHandler());
    server.start();

    books.add(new Book(1,"Java for Dummies", "not Philipp"));
  }

  static class BookHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
      String method = exchange.getRequestMethod();
      String path = exchange.getRequestURI().getPath();

      if (method.equals("GET") && path.equals("/books")) {
        handleGetAllBooks(exchange);
      } else if (method.equals("POST") && path.equals("/books")) {
        handleCreateBook(exchange);
      } else if (method.equals("PUT") && path.startsWith("/books/")) {
        handleUpdateBook(exchange, path);
      } else if (method.equals("DELETE") && path.startsWith("/books/")) {
        handleDeleteBook(exchange, path);
      } else {
        exchange.sendResponseHeaders(404, -1);
      }
    }

    // Alle Bücher abrufen (GET /books)
    private void handleGetAllBooks(HttpExchange exchange) throws IOException {
      JSONArray jsonArray = new JSONArray();
      for (Book book : books) {
        jsonArray.put(toJson(book));
      }

      String response = jsonArray.toString();
      exchange.getResponseHeaders().add("Content-Type", "application/json");
      exchange.sendResponseHeaders(200, response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }

    // Neues Buch erstellen (POST /books)
    private void handleCreateBook(HttpExchange exchange) throws IOException {
      InputStream is = exchange.getRequestBody();
      String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
      JSONObject json = new JSONObject(requestBody);

      if (!json.has("title") || !json.has("author")) {
        exchange.sendResponseHeaders(400, -1);
        return;
      }

      Book newBook = new Book(books.size()+1, json.getString("title"), json.getString("author"));
      books.add(newBook);

      String response = toJson(newBook).toString();
      exchange.getResponseHeaders().add("Content-Type", "application/json");
      exchange.sendResponseHeaders(201, response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }

    // Buch aktualisieren (PUT /books/{id})
    private void handleUpdateBook(HttpExchange exchange, String path) throws IOException {
      int id = extractIdFromPath(path);
      Optional<Book> bookOpt = books.stream().filter(b -> b.getId() == id).findFirst();
      if (bookOpt.isEmpty()) {
        exchange.sendResponseHeaders(404, -1);
        return;
      }

      Book book = bookOpt.get();
      String requestBody = new String(exchange.getRequestBody().readAllBytes(),
          StandardCharsets.UTF_8);
      JSONObject json = new JSONObject(requestBody);

      if (json.has("title")) {
        book.setTitle(json.getString("title"));
      }
      if (json.has("author")) {
        book.setAuthor(json.getString("author"));
      }

      String response = toJson(book).toString();
      exchange.getResponseHeaders().add("Content-Type", "application/json");
      exchange.sendResponseHeaders(200, response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }

    // Buch löschen (DELETE /books/{id})
    private void handleDeleteBook(HttpExchange exchange, String path) throws IOException {
      int id = extractIdFromPath(path);
      Optional<Book> bookOpt = books.stream().filter(b -> b.getId() == id).findFirst();
      if (bookOpt.isEmpty()) {
        exchange.sendResponseHeaders(404, -1);
        return;
      }

      books.remove(bookOpt.get());
      exchange.sendResponseHeaders(200, 0);
      OutputStream os = exchange.getResponseBody();
      os.close();
    }

    // Extrahiert die ID aus der URL (z.B. /books/1 -> 1)
    private int extractIdFromPath(String path) {
      try {
        return Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
      } catch (NumberFormatException e) {
        return -1; // Ungültige ID
      }
    }

    private JSONObject toJson(Book book) {
      JSONObject json = new JSONObject();
      json.put("id", book.getId());
      json.put("title", book.getTitle());
      json.put("author", book.getAuthor());
      return json;
    }
  }
}