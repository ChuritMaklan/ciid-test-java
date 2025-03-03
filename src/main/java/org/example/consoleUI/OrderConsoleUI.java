package org.example.consoleUI;

import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.service.OrderService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private OrderService orderService = new OrderService();

    public void addOrder() {
        System.out.println("Enter person ID:");
        int personId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter order date (YYYY-MM-DD):");
        Date orderDate = Date.valueOf(scanner.nextLine());

        try {
            // Create the order
            int orderId = orderService.addOrder(personId, orderDate);
            System.out.println("Order created with ID: " + orderId);

            // Add order items
            boolean addMoreItems = true;
            while (addMoreItems) {
                System.out.println("Add a new item to the order? (yes/no):");
                String response = scanner.nextLine().toLowerCase();
                if (!response.equals("yes")) {
                    addMoreItems = false;
                } else {
                    System.out.println("Enter part ID:");
                    int partId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter price:");
                    int price = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Add the order item
                    orderService.addOrderItem(orderId, partId, quantity, price);
                    System.out.println("Item added to the order.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating order: " + e.getMessage());
        }
    }

    public void getOrderById() {
        System.out.println("Enter order ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            Order order = orderService.getOrderById(id);
            if (order != null) {
                System.out.println(order);
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving order: " + e.getMessage());
        }
    }

    public void updateOrder() {
        System.out.println("Enter order ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new person ID:");
        int personId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new order date (YYYY-MM-DD):");
        Date orderDate = Date.valueOf(scanner.nextLine());
        try {
            orderService.updateOrder(id, personId, orderDate);
            System.out.println("Order updated.");
        } catch (SQLException e) {
            System.err.println("Error updating order: " + e.getMessage());
        }
    }

    public void deleteOrder() {
        System.out.println("Enter order ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            orderService.deleteOrder(id);
            System.out.println("Order deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting order: " + e.getMessage());
        }
    }

    public void getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            orders.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("Error retrieving orders: " + e.getMessage());
        }
    }

    public void getOrderItemsByOrderId() {
        System.out.println("Enter order ID:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
            if (orderItems.isEmpty()) {
                System.out.println("No order items found for order ID " + orderId + ".");
            } else {
                System.out.println("Order Items for Order ID " + orderId + ":");
                orderItems.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving order items: " + e.getMessage());
        }
    }
}
