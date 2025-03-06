package com.example.ciidjsp.servlet;

import com.example.ciidjsp.model.Order;
import com.example.ciidjsp.model.OrderItem;
import com.example.ciidjsp.model.Part;
import com.example.ciidjsp.service.OrderService;
import com.example.ciidjsp.service.PartService;
import com.example.ciidjsp.service.PersonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final PersonService personService = new PersonService();
    private final PartService partService = new PartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            try {
                List<Order> orders = orderService.getAllOrders();
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/views/order/orderIndex.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                switch (action) {
                    case "show":
                        showOrder(request, response);
                        break;
                    case "new":
                        newOrder(request, response);
                        break;
                    case "edit":
                        editOrder(request, response);
                        break;
                    case "delete":
                        deleteOrder(request, response);
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            try {
                switch (action) {
                    case "create":
                        createOrder(request, response);
                        break;
                    case "update":
                        updateOrder(request, response);
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrderById(id);
        request.setAttribute("order", order);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems = orderService.getOrderItemsByOrderId(order.getId());
        Map<Integer, Part> partsMap = new HashMap<>();
        for (OrderItem orderItem : orderItems) {
            Part part = partService.getPartById(orderItem.getPartId());
            partsMap.put(orderItem.getPartId(), part);
        }
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("partsMap", partsMap);
        request.setAttribute("customer", personService.getPersonById(order.getPersonId()));
        request.getRequestDispatcher("/views/order/orderShow.jsp").forward(request, response);
    }
    private void newOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("parts", partService.getAllParts());
        request.setAttribute("persons", personService.getAllCustomers());
        request.getRequestDispatcher("/views/order/orderNew.jsp").forward(request, response);
    }

    private void editOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("order", orderService.getOrderById(id));
        request.setAttribute("persons", personService.getAllCustomers());
        request.getRequestDispatcher("/views/order/orderEdit.jsp").forward(request, response);
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int personId = Integer.parseInt(request.getParameter("customerName"));
        String orderDate = request.getParameter("orderDate");
        String[] selectedIds = request.getParameterValues("selectedItems");
        int[] partIds = new int[selectedIds.length];
        for(int i = 0; i < partIds.length; i++){
            partIds[i] = Integer.parseInt(selectedIds[i]);
        }
        String[] quantities = request.getParameterValues("quantities");
        int[] quantitiesValue = new int[quantities.length];
        for(int i = 0; i < quantitiesValue.length; i++){
            quantitiesValue[i] = Integer.parseInt(quantities[i]);
        }
        orderService.addOrder(personId, Date.valueOf(orderDate), partIds, quantitiesValue);
        response.sendRedirect(request.getContextPath() + "/orders");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int personId = Integer.parseInt(request.getParameter("customerName"));
        String orderDate = request.getParameter("orderDate");
        orderService.updateOrder(id, personId, Date.valueOf(orderDate));
        response.sendRedirect(request.getContextPath() + "/orders");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.deleteOrder(id);
        response.sendRedirect(request.getContextPath() + "/orders");
    }
}

