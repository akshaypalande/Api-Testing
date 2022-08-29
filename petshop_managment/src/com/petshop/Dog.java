package com.petshop;

    public class Dog extends Animal implements  Swimmable{

        public Dog(String id){
            this.id = id;
            name = "Dog";
            colour =  Colour.BLACK;
        }

        @Override
        public void eat(){
            System.out.println("Dog eats flash");
        }

        @Override
        public void swim() {
            System.out.println("Dog can swim");
        }
    }
