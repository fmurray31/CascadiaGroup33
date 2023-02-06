import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AnimalTiles {
    private String animal;
    ArrayList<AnimalTiles> animalAL;

    public void animalSetup() {
        animalAL = new ArrayList<>();
        animalAL = generateAnimals(animalAL);
        animalAL = shuffleAnimals(animalAL);
    }

    public ArrayList<AnimalTiles> getAnimalAL() {
        return animalAL;
    }

    public AnimalTiles(String animal) {
        this.animal = animal;
    }

    public String getAnimal() {
        return animal;
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

    private ArrayList<AnimalTiles> shuffleAnimals(ArrayList<AnimalTiles> al) {
        Collections.shuffle(al, new Random());
        return al;
    }


    @Override
    public String toString() {
        return animal;
    }
}
