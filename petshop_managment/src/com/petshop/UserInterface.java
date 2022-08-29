package com.petshop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import  java.util.Set;

    public class UserInterface{
        static UserInterface instance;

        private UserInterface() {
        }

        public static UserInterface getInstance(){
            if (instance == null){
                instance = new UserInterface();
            }
            return instance;
        }
        public void print(Set<Pet> petList){
            for(Pet o: petList) {
                System.out.println(o);
            }
        }

        public void printAnimals(Set<Pet> petList) {
            for(Pet o: petList){
                if (o instanceof Animal){
                    System.out.println(o);
                }
            }
        }

        public void printBirds(Set<Pet> petList) {
            for (Pet o: petList) {
                if (o instanceof  Bird){
                    System.out.println(o);
                }
            }
        }

        public void printSwimmables(Set<Pet> petList) {
            for (Pet o: petList) {
                if (o instanceof Swimmable){
                    System.out.println(o);
                }
            }
        }

        public void printFlyables(Set<Pet> petList) {
            for (Pet o: petList) {
                if (o instanceof Flyable){
                    System.out.println(o);
                }
            }
        }

        public int showMainMenu(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select an option\n 1- Add, 2-Remove, 3-Update, 4-Display, 5_Exit");
            int choice = scanner.nextInt();
            return choice;
        }

        public int showUpdateMenu(){
            System.out.println("please enter what you want to update ");
            System.out.println("1-Name, 2-ID, 3-Colour,4-age, 5-price: ");
            Scanner scanner = new Scanner(System.in);
            int parameter = scanner.nextInt();
            return parameter;
        }
    }
