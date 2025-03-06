package com.example.ciidjsp.servlet;

import com.example.ciidjsp.model.*;
import com.example.ciidjsp.service.CategoryService;
import com.example.ciidjsp.service.PartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final PartService partService = new PartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            try {
                List<Category> categories = categoryService.getAllCategories();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/views/category/categoryIndex.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                switch (action) {
                    case "show":
                        showCategory(request, response);
                        break;
                    case "new":
                        newCategory(request, response);
                        break;
                    case "edit":
                        editCategory(request, response);
                        break;
                    case "delete":
                        deleteCategory(request, response);
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
                        createCategory(request, response);
                        break;
                    case "update":
                        updateCategory(request, response);
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.getCategoryById(id);
        request.setAttribute("category", category);
        request.setAttribute("category", category);
        request.setAttribute("parts", partService.getPartsByCategory(category.getId()));
        request.getRequestDispatcher("/views/category/categoryShow.jsp").forward(request, response);
    }

    private void newCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/category/categoryNew.jsp").forward(request, response);
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("category", categoryService.getCategoryById(id));
        request.getRequestDispatcher("/views/category/categoryEdit.jsp").forward(request, response);
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        categoryService.addCategory(name);
        response.sendRedirect(request.getContextPath() + "/categories");
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        categoryService.updateCategory(id, name);
        response.sendRedirect(request.getContextPath() + "/categories");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteCategory(id);
        response.sendRedirect(request.getContextPath() + "/categories");
    }
}
