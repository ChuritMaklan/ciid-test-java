package com.example.ciidjsp.servlet;

import com.example.ciidjsp.model.PersonType;
import com.example.ciidjsp.service.PersonTypeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet({"/person-types"})
public class PersonTypeServlet extends HttpServlet {
    private final PersonTypeService personTypeService = new PersonTypeService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // List all people (default action)
            List<PersonType> personTypes = null;
            try {
                personTypes = personTypeService.getAllPersonTypes();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("personTypes", personTypes);
            request.getRequestDispatcher("/views/personType/personTypeIndex.jsp").forward(request, response);
        } else {
            try {
                switch (action) {
                    case "show":
                        showPersonType(request, response);
                        break;
                    case "new":
                        newPersonType(request, response);
                        break;
                    case "edit":
                        editPersonType(request, response);
                        break;
                    case "delete":
                        deletePersonType(request, response);
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
                        createPersonType(request, response);
                        break;
                    case "update":
                        updatePersonType(request, response);
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    private void showPersonType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("personType", personTypeService.getPersonTypeById(id));
        request.getRequestDispatcher("/views/personType/personTypeShow.jsp").forward(request, response);
    }

    private void newPersonType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/personType/personTypeNew.jsp").forward(request, response);
    }

    private void editPersonType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("personType", personTypeService.getPersonTypeById(id));
        request.getRequestDispatcher("/views/personType/personTypeEdit.jsp").forward(request, response);
    }

    private void createPersonType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        personTypeService.addPersonType(request.getParameter("typeName"));
        response.sendRedirect(request.getContextPath() + "/person-types");
    }

    private void updatePersonType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
       personTypeService.updatePersonType(Integer.parseInt(request.getParameter("id")), request.getParameter("typeName"));
        response.sendRedirect(request.getContextPath() + "/person-types");
    }

    private void deletePersonType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        personTypeService.deletePersonType(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/person-types");
    }
}
