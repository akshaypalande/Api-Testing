package com.petshop;

    public class Duck extends Bird implements Swimmable, Flyable{

        public Duck(String id){
            this.id = id;
            name = "Duck";
            colour =  Colour.WHITE;
        }

        @Override
        public void eat() {
            System.out.println("Duck eats fish");
        }

        @Override
        public void swim() {
            System.out.println("Duck can swim");
        }

        @Override
        public void fly() {
            System.out.println("Duck can fly");
        }
    }
