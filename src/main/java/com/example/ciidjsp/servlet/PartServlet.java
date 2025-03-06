package com.example.ciidjsp.servlet;

import com.example.ciidjsp.model.Part;
import com.example.ciidjsp.service.CategoryService;
import com.example.ciidjsp.service.PartService;
import com.example.ciidjsp.service.PersonService;
import com.example.ciidjsp.service.PersonTypeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/parts")
public class PartServlet extends HttpServlet {
    private final PartService partService = new PartService();
    private final PersonService personService = new PersonService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            try {
                List<Part> parts = partService.getAllParts();
                request.setAttribute("parts", parts);
                request.getRequestDispatcher("/views/part/partIndex.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                switch (action) {
                    case "show":
                        showPart(request, response);
                        break;
                    case "new":
                        newPart(request, response);
                        break;
                    case "edit":
                        editPart(request, response);
                        break;
                    case "delete":
                        deletePart(request, response);
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
                        createPart(request, response);
                        break;
                    case "update":
                        updatePart(request, response);
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Part part = partService.getPartById(id);
        request.setAttribute("part", part);
        request.setAttribute("supplier", personService.getPersonById(part.getPersonId()));
        request.getRequestDispatcher("/views/part/partShow.jsp").forward(request, response);
    }

    private void newPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("categories", categoryService.getAllCategories());
        request.setAttribute("persons", personService.getAllSuppliers());
        request.getRequestDispatcher("/views/part/partNew.jsp").forward(request, response);
    }

    private void editPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("part", partService.getPartById(id));
        request.setAttribute("persons", personService.getAllSuppliers());
        request.getRequestDispatcher("/views/part/partEdit.jsp").forward(request, response);
    }

    private void createPart(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        Integer personId = Integer.parseInt(request.getParameter("supplierName"));
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        String[] selectedIds = request.getParameterValues("selectedItems");
        List<Integer> categoryIds = new ArrayList<>();
        for (String selectedId : selectedIds) {
            categoryIds.add(Integer.parseInt(selectedId));
        }
        partService.addPart(name, personId, price, description, quantity, categoryIds);
        response.sendRedirect(request.getContextPath() + "/parts");
    }

    private void updatePart(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Integer personId = Integer.parseInt(request.getParameter("supplierName"));
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        partService.updatePart(id, name, personId, price, description, quantity);
        response.sendRedirect(request.getContextPath() + "/parts");
    }

    private void deletePart(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        partService.deletePart(id);
        response.sendRedirect(request.getContextPath() + "/parts");
    }
}
