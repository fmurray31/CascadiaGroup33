import java.util.ArrayList;

public class AnimalTiles {
    private String animal;

    public AnimalTiles(String animal) {
        this.animal = animal;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return animal;
    }
}
