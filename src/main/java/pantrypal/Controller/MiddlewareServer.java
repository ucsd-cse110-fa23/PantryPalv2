// package main.java.pantrypal.Controller;

// import com.sun.net.httpserver.HttpServer;

// import pantrypal.Model.CRUDRecipes;
// import pantrypal.Model.ChatGPT;
// import pantrypal.Model.ILanguageModel;
// import pantrypal.Model.OpenAI;
// import pantrypal.Model.RecipeData;

// import com.google.gson.Gson;
// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.InetSocketAddress;
// import java.net.URLDecoder;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// public class MiddlewareServer {

// public static void main(String[] args) throws IOException {
// HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

// server.createContext("/recipes", new RecipesHandler());
// server.createContext("/recipes/create", new CreateRecipeHandler());
// server.createContext("/recipes/update", new UpdateRecipeHandler());
// server.createContext("/recipes/delete", new DeleteRecipeHandler());

// server.setExecutor(null);
// server.start();
// System.out.println("Server started on port 8000");
// }

// static class RecipesHandler implements HttpHandler {
// @Override
// public void handle(HttpExchange exchange) throws IOException {
// if ("GET".equals(exchange.getRequestMethod())) {
// ArrayList<RecipeData> recipes = CRUDRecipes.readRecipes();
// Gson gson = new Gson();
// String response = gson.toJson(recipes);
// sendResponse(exchange, response, 200);
// } else {
// // Method Not Allowed for this endpoint
// sendResponse(exchange, "Method Not Allowed", 405);
// }
// }
// }

// static class CreateRecipeHandler implements HttpHandler {
// @Override
// public void handle(HttpExchange exchange) throws IOException {
// if ("POST".equals(exchange.getRequestMethod())) {
// // Assume the recipe data is sent as a JSON in the request body
// RecipeData newRecipe = parseRecipeData(exchange);
// CRUDRecipes.createRecipe(newRecipe);
// Gson gson = new Gson();
// String response = gson.toJson(newRecipe);
// sendResponse(exchange, response, 201);
// } else {
// sendResponse(exchange, "Method Not Allowed", 405);
// }
// }
// }

// static class UpdateRecipeHandler implements HttpHandler {
// @Override
// public void handle(HttpExchange exchange) throws IOException {
// if ("PUT".equals(exchange.getRequestMethod())) {
// RecipeData updatedRecipe = parseRecipeData(exchange);
// CRUDRecipes.updateRecipe(updatedRecipe);
// Gson gson = new Gson();
// String response = gson.toJson(updatedRecipe);
// sendResponse(exchange, response, 200);
// } else {
// sendResponse(exchange, "Method Not Allowed", 405);
// }
// }
// }

// static class DeleteRecipeHandler implements HttpHandler {
// @Override
// public void handle(HttpExchange exchange) throws IOException {
// if ("DELETE".equals(exchange.getRequestMethod())) {
// // Assume we send the title as a query parameter for simplicity
// String title = extractTitleFromQuery(exchange);
// CRUDRecipes.deleteRecipe(title);
// sendResponse(exchange, "", 204);
// } else {
// sendResponse(exchange, "Method Not Allowed", 405);
// }
// }
// }

// // Helper method to send a response
// private static void sendResponse(HttpExchange exchange, String response, int
// statusCode) throws IOException {
// exchange.getResponseHeaders().set("Content-Type", "application/json");
// exchange.sendResponseHeaders(statusCode, response.getBytes().length);
// try (OutputStream os = exchange.getResponseBody()) {
// os.write(response.getBytes());
// }
// }

// // Helper method to parse RecipeData from the request body
// private static RecipeData parseRecipeData(HttpExchange exchange) throws
// IOException {
// // Assuming you have a constructor in RecipeData that accepts a title,
// // ingredients, and instructions
// InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(),
// StandardCharsets.UTF_8);
// RecipeData recipeData = new Gson().fromJson(isr, RecipeData.class);
// isr.close();
// return recipeData;
// }

// // Helper method to extract the title from the query string
// private static String extractTitleFromQuery(HttpExchange exchange) {
// String query = exchange.getRequestURI().getQuery();
// Map<String, String> queryPairs = parseQueryString(query);
// return queryPairs.getOrDefault("title", "");
// }

// // Utility method to parse query string into a Map
// private static Map<String, String> parseQueryString(String query) {
// Map<String, String> queryPairs = new HashMap<>();
// if (query == null || query.isEmpty()) {
// return queryPairs;
// }

// String[] pairs = query.split("&");
// for (String pair : pairs) {
// int idx = pair.indexOf("=");
// String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx),
// StandardCharsets.UTF_8) : pair;
// String value = idx > 0 && pair.length() > idx + 1
// ? URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8)
// : null;
// queryPairs.put(key, value);
// }
// return queryPairs;
// }

// }