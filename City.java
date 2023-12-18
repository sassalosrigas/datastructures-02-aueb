/* file: City.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

public class City implements CityInterface {

    private int ID;
    private String name;
    private int population;
    private int InfluenzaCases;

    public City(int ID, String name, int population, int InfluenzaCases) {
        this.ID = ID;
        this.name = name;
        this.population = population;
        this.InfluenzaCases = InfluenzaCases;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getInfluenzaCases() {
        return InfluenzaCases;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setInfluenzaCases(int InfluenzaCases) {
        this.InfluenzaCases = InfluenzaCases;
    }
}