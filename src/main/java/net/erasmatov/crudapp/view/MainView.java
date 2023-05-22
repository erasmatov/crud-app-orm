package net.erasmatov.crudapp.view;

import java.util.Scanner;

public class MainView {
    public static void showMainMenu() {
        Scanner input = new Scanner(System.in);
        int option;

        do {
            System.out.println("MAIN MENU:\n" +
                    "1. Developer\n" +
                    "2. Skill\n" +
                    "3. Specialty\n" +
                    "0. Exit program");
            System.out.print("Enter your selection > ");

            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    new DeveloperView().showDeveloperMenu();
                    break;

                case 2:
                    new SkillView().showSkillMenu();
                    break;

                case 3:
                    new SpecialtyView().showSpecialtyMenu();
                    break;

                case 0:
                    System.out.print(
                            "\nThank you for using the program. Goodbye!");
                    input.close();
                    break;

                default:
                    System.out.println("Invalid input: " + option + "!\n");
                    break;
            }
        } while (option != 0);
        System.exit(0);
    }
}