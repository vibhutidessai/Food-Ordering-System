import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {

        public static void main(String[] args) throws IOException {

                HttpServer server = HttpServer.create(
                                new InetSocketAddress(8080),
                                0);

                // FOOD API
                server.createContext("/api/food", exchange -> {

                        exchange.getResponseHeaders()
                                        .set("Access-Control-Allow-Origin", "*");

                        String response = "["
                                        + "{\"id\":1,\"name\":\"Burger\",\"price\":120},"
                                        + "{\"id\":2,\"name\":\"Pizza\",\"price\":200},"
                                        + "{\"id\":3,\"name\":\"Sandwich\",\"price\":100},"
                                        + "{\"id\":4,\"name\":\"Fries\",\"price\":80}"
                                        + "]";

                        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

                        exchange.getResponseHeaders()
                                        .set(
                                                        "Content-Type",
                                                        "application/json; charset=UTF-8");

                        exchange.sendResponseHeaders(
                                        200,
                                        responseBytes.length);

                        OutputStream outputStream = exchange.getResponseBody();

                        outputStream.write(responseBytes);

                        outputStream.close();
                });

                // ORDER API
                server.createContext("/api/order", exchange -> {

                        exchange.getResponseHeaders()
                                        .set("Access-Control-Allow-Origin", "*");

                        exchange.getResponseHeaders()
                                        .set(
                                                        "Access-Control-Allow-Methods",
                                                        "POST, OPTIONS");

                        exchange.getResponseHeaders()
                                        .set(
                                                        "Access-Control-Allow-Headers",
                                                        "Content-Type");

                        // Handle browser preflight request
                        if (exchange.getRequestMethod()
                                        .equalsIgnoreCase("OPTIONS")) {

                                exchange.sendResponseHeaders(
                                                204,
                                                -1);

                                exchange.close();

                                return;
                        }

                        // Only POST requests allowed
                        if (!exchange.getRequestMethod()
                                        .equalsIgnoreCase("POST")) {

                                String response = "Only POST requests are allowed.";

                                byte[] responseBytes = response.getBytes(
                                                StandardCharsets.UTF_8);

                                exchange.sendResponseHeaders(
                                                405,
                                                responseBytes.length);

                                exchange.getResponseBody()
                                                .write(responseBytes);

                                exchange.close();

                                return;
                        }

                        // CREATE CUSTOMER
                        Customer customer = new Customer(
                                        1,
                                        "Vibhuti");

                        // CREATE CART
                        Cart cart = new Cart();

                        // READ ORDER DATA
                        String requestBody = new String(
                                        exchange.getRequestBody()
                                                        .readAllBytes(),
                                        StandardCharsets.UTF_8);

                        System.out.println(
                                        "Received order: " + requestBody);

                        // ADD FOOD ITEMS
                        if (requestBody.contains("1")) {

                                cart.addItem(
                                                new FoodItem(
                                                                1,
                                                                "Burger",
                                                                120));
                        }

                        if (requestBody.contains("2")) {

                                cart.addItem(
                                                new FoodItem(
                                                                2,
                                                                "Pizza",
                                                                200));
                        }

                        if (requestBody.contains("3")) {

                                cart.addItem(
                                                new FoodItem(
                                                                3,
                                                                "Sandwich",
                                                                100));
                        }

                        if (requestBody.contains("4")) {

                                cart.addItem(
                                                new FoodItem(
                                                                4,
                                                                "Fries",
                                                                80));
                        }

                        // CREATE ORDER
                        Order order = new Order(
                                        101,
                                        customer,
                                        cart);

                        // PAYMENT
                        Payment payment = new UPIPayment();

                        // PLACE ORDER
                        order.placeOrder(payment);

                        double total = order.getTotal();

                        // SEND RESPONSE
                        String response = "{"
                                        + "\"message\":\"Order placed successfully\","
                                        + "\"orderId\":101,"
                                        + "\"total\":" + total
                                        + "}";

                        byte[] responseBytes = response.getBytes(
                                        StandardCharsets.UTF_8);

                        exchange.getResponseHeaders()
                                        .set(
                                                        "Content-Type",
                                                        "application/json; charset=UTF-8");

                        exchange.sendResponseHeaders(
                                        200,
                                        responseBytes.length);

                        exchange.getResponseBody()
                                        .write(responseBytes);

                        exchange.close();
                });

                // START SERVER
                server.start();
                System.out.println(
                                "Server started at http://localhost:8080");
        }
}