package com.petshop;

import java.util.Objects;

    public abstract class Pet {
        public enum Colour{BLACK, RED, BLACK_WHITE, GREEN, WHITE}

        String id;
        Colour colour;
        String name;
        int age;
        int price;

        public abstract void eat();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pet pet = (Pet) o;
            return Objects.equals(id, pet.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
