package com.petshop;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public void handelUserSelection(int choice) {
        PetStorage petStorage = PetStorage.getInstance();
        String petID;
        Scanner scanner;
        switch (choice) {
            case 1:
                add();
                break;
            case 2:
                System.out.println("please enter id of pet you want to remove ");
                scanner = new Scanner(System.in);
                petID = scanner.next();
                Pet petToRemove = petStorage.getPet(petID);
                petStorage.remove(petToRemove);
                break;
            case 3:
                System.out.println("please enter id of the pet that you want to update ");
                scanner = new Scanner(System.in);
                petID = scanner.next();
                Pet petToUpdate = petStorage.getPet(petID);
                update(petToUpdate);
                break;
            case 4:
                UserInterface userInterface = UserInterface.getInstance();
                userInterface.print(petStorage.getPetList());
                break;
            case 5:
                break;
            default:
                System.out.println("Entered choice is invalid, please enter a valid one");
        }
    }

    public void add() {
        Animal cat = new Cat("CAT001");
        cat.age = 6;
        cat.price = 20000;
        cat.breed = "CATCAT";

        Animal cat1 = new Cat("CAT001");
        cat.age = 6;
        cat.price = 20000;
        cat.breed = "CATCAT1";

        Animal dog = new Dog("DOG001");
        dog.age = 10;
        dog.price = 40000;
        dog.breed = "DOBBER";

        Bird duck = new Duck("DUCK001");
        duck.age = 9;
        duck.price = 5000;
        duck.species = "duckduck";

        Bird parrot = new Parrot("PARROT001");
        parrot.age = 1;
        parrot.price = 2000;
        parrot.species = "GREENPARROT";

        Animal rabbit = new Rabbit("RABBIT001");
        rabbit.age = 2;
        rabbit.price = 4500;
        rabbit.breed = "RABBITBIT";

        PetStorage petStorage = PetStorage.getInstance();
        petStorage.add(cat);
        petStorage.add(dog);
        petStorage.add(duck);
        petStorage.add(parrot);
        petStorage.add(rabbit);
        petStorage.add(cat1);
    }

    public void update(Pet pet){
        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = UserInterface.getInstance();
        int parameter = userInterface.showUpdateMenu();
        switch (parameter){
            case 1:
                System.out.println("Enter the new name ");
                pet.name = scanner.next();
                break;
            case 2:
                System.out.println("Enter the new ID ");
                pet.id = scanner.next();
                break;
            case 3:
                colour(pet);
                break;
            case 4:
                System.out.println("Enter the new age ");
                pet.age = scanner.nextInt();
                break;
            case 5:
                System.out.println("Enter the new price ");
                pet.price = scanner.nextInt();
                break;
            default:
                System.out.println("please enter a valid input");
        }
    }

    public void colour(Pet pet){
        System.out.println("please select colour from below");

        Scanner scanner = new Scanner(System.in);
        int count = 0;
        for (Pet.Colour c: Pet.Colour.values()) {
            count++;
            System.out.println(count + "-" + c);
        }
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pet.colour = Pet.Colour.BLACK;
                break;
            case 2:
                pet.colour = Pet.Colour.RED;
                break;
            case 3:
                pet.colour = Pet.Colour.BLACK_WHITE;
                break;
            case 4:
                pet.colour = Pet.Colour.GREEN;
                break;
            case 5:
                pet.colour = Pet.Colour.WHITE;
                break;
            default:
                System.out.println("please enter a valid input");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Pet management system !");

        UserInterface userInterface = UserInterface.getInstance();
        Main main = new Main();

        int choice = 0;
        while (choice != 5) {
            choice = userInterface.showMainMenu();
            main.handelUserSelection(choice);
        }

        /*Animal cat = new Cat("CAT001");
        cat.age = 6;
        cat.price = 20000;
        cat.breed = "CATCAT";

        Animal cat1 = new Cat("CAT001");
        cat.age = 6;
        cat.price = 20000;
        cat.breed = "CATCAT1";

        Animal dog = new Dog("DOG001");
        dog.age = 10;
        dog.price = 40000;
        dog.breed = "DOBBER";

        Bird duck = new Duck("DUCK001");
        duck.age = 9;
        duck.price = 5000;
        duck.species = "duckduck";

        Bird parrot = new Parrot("PARROT001");
        parrot.age = 1;
        parrot.price = 2000;
        parrot.species = "GREENPARROT";

        Animal rabbit = new Rabbit("RABBIT001");
        rabbit.age = 2;
        rabbit.price = 4500;
        rabbit.breed = "RABBITBIT";

        PetStorage petStorage = new PetStorage();
        petStorage.add(cat);
        petStorage.add(dog);
        petStorage.add(duck);
        petStorage.add(parrot);
        petStorage.add(rabbit);
        petStorage.add(cat1);

        UserInterface userInterface = new UserInterface();
        Set petList = petStorage.getPetList();
        userInterface.print(petList);

        petStorage.remove(cat);
        System.out.println("After removing");
        userInterface.print(petList);

        System.out.println("Animals list are");
        userInterface.printAnimals(petList);

        System.out.println("Birds list are");
        userInterface.printBirds(petList);

        System.out.println("Pets that can fly are");
        userInterface.printFlyables(petList);
        System.out.println("Pet that can swim are");
        userInterface.printSwimmables(petList);*/
    }
}


