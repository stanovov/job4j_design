package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.Arrays;

public class Order {
    private final String id;
    private final String name;
    private final boolean delivery;
    private final OrderStatus status;
    private final double price;
    private final Product[] products;

    public enum OrderStatus {
        NEW,
        IN_PROCESSING,
        SENT,
        DELIVERED,
        CANCELED
    }

    public static class Product {
        private final String id;
        private final String name;
        private final double price;
        private final double quantity;

        public Product(String id, String name, double price, double quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public double getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Product{"
                    + "id='" + id + '\''
                    + ", name='" + name + '\''
                    + ", price=" + price
                    + ", quantity=" + quantity
                    + '}';
        }
    }

    public Order(String id, String name, boolean delivery,
                 OrderStatus status, double price, Product[] products) {
        this.id = id;
        this.name = name;
        this.delivery = delivery;
        this.status = status;
        this.price = price;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public Product[] getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", delivery=" + delivery
                + ", status=" + status
                + ", price=" + price
                + ", products=" + Arrays.toString(products)
                + '}';
    }

    private static void exampleGSON() {
        Order order = new Order(
                "1",
                "New order",
                false,
                OrderStatus.NEW,
                222.0,
                new Product[]{
                        new Product(
                                "product_1",
                                "Тетрадь в клетку 24 листа",
                                111.0,
                                2.0
                        )
                }
        );
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(order));
        String orderJson =
                "{"
                        + "\"id\":\"1\","
                        + "\"name\":\"New order\","
                        + "\"delivery\":true,"
                        + "\"status\":\"SENT\","
                        + "\"price\":260.0,"
                        + "\"products\":["
                        + "{"
                        + "\"id\":\"product_1\","
                        + "\"name\":\"Тетрадь в клетку 24 листа\","
                        + "\"price\":111.0,"
                        + "\"quantity\":2.0"
                        + "},{"
                        + "\"id\":\"product_2\","
                        + "\"name\":\"Ручка черная гелиевая\","
                        + "\"price\":38.0,"
                        + "\"quantity\":1.0"
                        + "}"
                        + "]"
                        + "}";
        Order orderMod = gson.fromJson(orderJson, Order.class);
        System.out.println(orderMod);
    }

    private static void examplePOJO() {
        Order order = new Order(
                "1",
                "New order",
                true,
                OrderStatus.NEW,
                260.0,
                new Product[]{
                        new Product(
                                "product_1",
                                "Тетрадь в клетку 24 листа",
                                111.0,
                                2.0
                        ),
                        new Product(
                                "product_2",
                                "Ручка черная гелиевая",
                                38.0,
                                1.0
                        )
                }
        );
        JSONObject jsonObject = new JSONObject(order);
        System.out.println(jsonObject.toString());
    }

    public static void main(String[] args) {
        examplePOJO();
    }
}
