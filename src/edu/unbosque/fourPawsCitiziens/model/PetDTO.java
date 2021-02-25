package edu.unbosque.fourPawsCitiziens.model;

public class PetDTO {

    private String id;
    private long microShip;
    private String species;
    private String sex ;
    private String size ;
    private boolean potentDangerous;
    private String neighborhood;


    public PetDTO(String id, long microShip, String species, String sex, String size, boolean potentDangerous, String neighborhood) {
        this.id = id;
        this.microShip = microShip;
        this.species = species;
        this.sex = sex;
        this.size = size;
        this.potentDangerous = potentDangerous;
        this.neighborhood = neighborhood;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMicroShip() {
        return microShip;
    }

    public void setMicroShip(long microShip) {
        this.microShip = microShip;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isPotentDangerous() {
        return potentDangerous;
    }

    public void setPotentDangerous(boolean potentDangerous) {
        this.potentDangerous = potentDangerous;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public String toString() {
        return
                "id :'" + id + "'\n"+
                "microShip : '" + microShip + "'\n"+
                "species : '" + species + "'\n"+
                "sex :'" + sex + "'\n"+
                "size :'" + size + "'\n"+
                "potentDangerous :'" + potentDangerous + "'\n" +
                "neighborhood :'" + neighborhood + "'\n" + "\n";
    }
}
