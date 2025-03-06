package com.example.ciidjsp.servlet;

import com.example.ciidjsp.model.Person;
import com.example.ciidjsp.model.PersonType;
import com.example.ciidjsp.service.PersonService;
import com.example.ciidjsp.service.PersonTypeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/persons")
public class PersonServlet extends HttpServlet {
    private final PersonService personService = new PersonService();
    private final PersonTypeService personTypeService = new PersonTypeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            try {
                List<Person> persons = personService.getAllPersons();
                request.setAttribute("persons", persons);
                request.getRequestDispatcher("/views/person/personIndex.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                switch (action) {
                    case "show":
                        showPerson(request, response);
                        break;
                    case "new":
                        newPerson(request, response);
                        break;
                    case "edit":
                        editPerson(request, response);
                        break;
                    case "delete":
                        deletePerson(request, response);
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
                        createPerson(request, response);
                        break;
                    case "update":
                        updatePerson(request, response);
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = personService.getPersonById(id);
        request.setAttribute("person", person);
        request.setAttribute("personType", personTypeService.getPersonTypeById(person.getTypeId()));
        request.getRequestDispatcher("/views/person/personShow.jsp").forward(request, response);
    }

    private void newPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<PersonType> personTypes = personTypeService.getAllPersonTypes();
        request.setAttribute("personTypes",personTypes);
        request.getRequestDispatcher("/views/person/personNew.jsp").forward(request, response);
    }

    private void editPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = personService.getPersonById(id);
        request.setAttribute("person", person);
        request.setAttribute("personTypes", personTypeService.getAllPersonTypes());
        request.getRequestDispatcher("/views/person/personEdit.jsp").forward(request, response);
    }

    private void createPerson(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int typeId = Integer.parseInt(request.getParameter("typeName"));
        personService.addPerson(name, email, phone, typeId);
        response.sendRedirect(request.getContextPath() + "/persons");
    }

    private void updatePerson(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int typeId = Integer.parseInt(request.getParameter("typeName"));
        personService.updatePerson(id, name, email, phone, typeId);
        response.sendRedirect(request.getContextPath() + "/persons");
    }

    private void deletePerson(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        personService.deletePerson(id);
        response.sendRedirect(request.getContextPath() + "/persons");
    }
}
