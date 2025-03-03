package org.example;

import org.example.consoleUI.*;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize UI classes
        PersonTypeConsoleUI personTypeUI = new PersonTypeConsoleUI();
        PersonConsoleUI personUI = new PersonConsoleUI();
        PartConsoleUI partUI = new PartConsoleUI();
        CategoryConsoleUI categoryUI = new CategoryConsoleUI();
        OrderConsoleUI orderUI = new OrderConsoleUI();

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a person type");
            System.out.println("2. Get person type by ID");
            System.out.println("3. Update person type");
            System.out.println("4. Delete person type");
            System.out.println("5. Show all person types");
            System.out.println("6. Add a person");
            System.out.println("7. Get person by ID");
            System.out.println("8. Update person");
            System.out.println("9. Delete person");
            System.out.println("10. Show all persons");
            System.out.println("11. Add a part");
            System.out.println("12. Get part by ID");
            System.out.println("13. Update part");
            System.out.println("14. Delete part");
            System.out.println("15. Show all parts");
            System.out.println("16. Add a category");
            System.out.println("17. Get category by ID");
            System.out.println("18. Update category");
            System.out.println("19. Delete category");
            System.out.println("20. Show all categories");
            System.out.println("21. Create order");
            System.out.println("22. Get order by ID");
            System.out.println("23. Update order");
            System.out.println("24. Delete order");
            System.out.println("25. Show all orders");
            System.out.println("26. Show all parts in category");
            System.out.println("27. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                // Person Type Operations
                case 1:
                    personTypeUI.addPersonType();
                    break;
                case 2:
                    personTypeUI.getPersonTypeById();
                    break;
                case 3:
                    personTypeUI.updatePersonType();
                    break;
                case 4:
                    personTypeUI.deletePersonType();
                    break;
                case 5:
                    personTypeUI.getAllPersonTypes();
                    break;

                // Person Operations
                case 6:
                    personUI.addPerson();
                    break;
                case 7:
                    personUI.getPersonById();
                    break;
                case 8:
                    personUI.updatePerson();
                    break;
                case 9:
                    personUI.deletePerson();
                    break;
                case 10:
                    personUI.getAllPersons();
                    break;

                // Part Operations
                case 11:
                    partUI.addPart();
                    break;
                case 12:
                    partUI.getPartById();
                    break;
                case 13:
                    partUI.updatePart();
                    break;
                case 14:
                    partUI.deletePart();
                    break;
                case 15:
                    partUI.getAllParts();
                    break;

                // Category Operations
                case 16:
                    categoryUI.addCategory();
                    break;
                case 17:
                    categoryUI.getCategoryById();
                    break;
                case 18:
                    categoryUI.updateCategory();
                    break;
                case 19:
                    categoryUI.deleteCategory();
                    break;
                case 20:
                    categoryUI.getAllCategories();
                    break;

                // Order Operations
                case 21:
                    orderUI.addOrder();
                    break;
                case 22:
                    orderUI.getOrderById();
                    break;
                case 23:
                    orderUI.updateOrder();
                    break;
                case 24:
                    orderUI.deleteOrder();
                    break;
                case 25:
                    orderUI.getAllOrders();
                    break;

                // Show parts in category
                case 26:
                    partUI.getPartsByCategory();
                    break;

                // Exit
                case 27:
                    running = false;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
