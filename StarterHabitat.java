import java.util.ArrayList;

public class StarterHabitat{

    ArrayList<HabitatTiles> StarterHabitat1 = new ArrayList<>();
    ArrayList<HabitatTiles> StarterHabitat2 = new ArrayList<>();
    ArrayList<HabitatTiles> StarterHabitat3 = new ArrayList<>();
    ArrayList<HabitatTiles> StarterHabitat4 = new ArrayList<>();
    ArrayList<HabitatTiles> StarterHabitat5 = new ArrayList<>();


   {
        StarterHabitat1.add(new HabitatTiles("wetland", "wetland", "wetland", "wetland", "wetland", "wetland", "hawk", "", ""));
        StarterHabitat1.add(new HabitatTiles("river", "river", "river", "forest", "forest", "forest", "salmon", "elk", "hawk"));
        StarterHabitat1.add(new HabitatTiles("prairie", "prairie", "mountain", "prairie", "mountain", "mountain", "bear", "fox", ""));

        StarterHabitat2.add(new HabitatTiles("mountain", "mountain", "mountain", "mountain", "mountain", "mountain", "bear", "", ""));
        StarterHabitat2.add(new HabitatTiles("forest", "forest", "forest", "wetland", "wetland", "wetland", "hawk", "elk", "fox"));
        StarterHabitat2.add(new HabitatTiles("river", "river", "prairie", "river", "prairie", "prairie", "salmon", "bear", ""));

        StarterHabitat3.add(new HabitatTiles("forest", "forest", "forest", "forest", "forest", "forest", "elk", "", ""));
        StarterHabitat3.add(new HabitatTiles("mountain", "mountain", "mountain", "river", "river", "river", "hawk", "elk", "bear"));
        StarterHabitat3.add(new HabitatTiles("wetland", "wetland", "prairie", "wetland", "prairie", "prairie", "fox", "salmon", ""));

        StarterHabitat4.add(new HabitatTiles("river", "river", "river", "river", "river", "river", "salmon", "", ""));
        StarterHabitat4.add(new HabitatTiles("prairie", "prairie", "prairie", "forest", "forest", "forest", "salmon", "elk", "bear"));
        StarterHabitat4.add(new HabitatTiles("mountain", "mountain", "wetland", "mountain", "wetland", "wetland", "fox", "hawk", ""));

        StarterHabitat5.add(new HabitatTiles("prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "fox", "", ""));
        StarterHabitat5.add(new HabitatTiles("wetland", "wetland", "wetland", "river", "river", "river", "salmon", "hawk", "fox"));
        StarterHabitat5.add(new HabitatTiles("forest", "forest", "mountain", "forest", "mountain", "mountain", "bear", "elk", ""));
    }

}