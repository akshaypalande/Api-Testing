package com.petshop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

    public class PetStorage {
        static PetStorage instance;

        private Set<Pet> petList = new HashSet();

        private PetStorage() {
        }

        public static PetStorage getInstance(){
            if (instance == null){
                instance = new PetStorage();
            }
            return instance;
        }

        public void add(Pet pet){
            petList.add(pet);
        }

        public Set<Pet> getPetList(){
            return petList;
        }

        public void remove(Pet pet){
            petList.remove(pet);
        }

        public Pet getPet(String petID){
            for (Pet pet: petList) {
                if(petID.equals(pet.id)){
                    return pet;
                }
            }
            return null;
        }
    }
