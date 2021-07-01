package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private boolean delivery;

    @XmlAttribute
    private OrderStatus status;

    @XmlAttribute
    private double price;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private Product[] products;

    public enum OrderStatus {
        NEW,
        IN_PROCESSING,
        SENT,
        DELIVERED,
        CANCELED
    }

    @XmlRootElement(name = "product")
    public static class Product {

        @XmlAttribute
        private String id;

        @XmlAttribute
        private String name;

        @XmlAttribute
        private double price;

        @XmlAttribute
        private double quantity;

        public Product() { }

        public Product(String id, String name, double price, double quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
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

    public Order() { }

    public Order(String id, String name, boolean delivery,
                 OrderStatus status, double price, Product[] products) {
        this.id = id;
        this.name = name;
        this.delivery = delivery;
        this.status = status;
        this.price = price;
        this.products = products;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Order order = new Order(
                "1",
                "New order",
                true,
                OrderStatus.SENT,
                260,
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

        JAXBContext context = JAXBContext.newInstance(Order.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(order, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Order result = (Order) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
