/*
Group 33
Fionn Murray – fmurray31
Jed Rena – jrena7
Mark Dwyer – MarkDwyer41
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AnimalTiles {
    private String animal;
    ArrayList<AnimalTiles> animalAL;

    public void animalSetup() {
        animalAL = new ArrayList<>();
        animalAL = generateAnimals(animalAL);
        shuffleAnimals();
    }

    public ArrayList<AnimalTiles> getAnimalAL() {
        return animalAL;
    }

    public AnimalTiles(String animal) {
        this.animal = animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    // Generates 20 of each animal and adds it to an arraylist
    private ArrayList<AnimalTiles> generateAnimals(ArrayList<AnimalTiles> al) {
        for (int i=0; i<20; i++) {

            getAnimalAL().add(new AnimalTiles("Hawk"));
            getAnimalAL().add(new AnimalTiles("Bear"));
            getAnimalAL().add(new AnimalTiles("Elk"));
            getAnimalAL().add(new AnimalTiles("Salmon"));
            getAnimalAL().add(new AnimalTiles("Fox"));
        }
        return al;
    }

    // shuffles the arraylist using collections
    public void shuffleAnimals() {
        Collections.shuffle(animalAL, new Random());
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnimalTiles) {
            return this.animal.equals(((AnimalTiles) obj).animal);
        } else {
            return this.equals(obj);
        }
    }
    @Override
    public String toString() {
        return animal;
    }
}
