package com.petshop;

    public class Rabbit extends Animal {

        public Rabbit(String id){
            this.id = id;
            name = "Rabbit";
            colour =  Colour.WHITE;
        }

        @Override
        public void eat() {
            System.out.println("Rabbit eats carrots");
        }
    }

