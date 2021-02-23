package edu.unbosque.fourPawsCitiziens.controller;

import edu.unbosque.fourPawsCitiziens.model.PetDTO;
import edu.unbosque.fourPawsCitiziens.model.persistence.PetDAO;
import edu.unbosque.fourPawsCitiziens.model.persistence.FunctionsCSV;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

//C:\Users\paula\FourPawsCitiziens
public class Manager {

    private PetDTO pet;
    private ArrayList<PetDTO> petList;
    private FunctionsCSV functionsCSV;


    public Manager() {
        functionsCSV = new FunctionsCSV();
        petList = new ArrayList<>();

        initializeComponents();

    }

    public void initializeComponents() {

        functionsCSV.readCSV();
        uploadData();
        assignID();
       //findByMicroChip(966000101133300L);
        // countBySpecies("FELINO");
       // countBySpecies("CANINO");
        //findByMultipleFields("HEMBRA" , "FELINO", "GRANDE", "F");

    }

    public void uploadData() {

        functionsCSV.readCSV();

        for (int i = 0; i < functionsCSV.getData().size(); i++) {

            String m = String.valueOf(functionsCSV.getData().get(i));
            m = m.substring(1);
            m = m.substring(0, m.length() - 1);


            String[] splitData = m.split(",");

            try {
                String micropShip = splitData[0];
                long ship = Long.parseLong(micropShip);
                boolean danger;

                if (splitData[4].equals("SI")) {
                    danger = true;
                } else {
                    danger = false;
                }
                pet = new PetDTO("", ship, splitData[1], splitData[2], splitData[3], danger, splitData[5]);
                petList.add(pet);

            } catch (NumberFormatException o) {


            } catch (ArrayIndexOutOfBoundsException p) {
                p.printStackTrace();
            }
        }

        System.out.println("Proceso de carga finalizado");

    }

    public void assignID() {

        generateID();

        System.out.println("El proceso de asignacion de ids ha finalizado");
    }

    public void findByMicroChip(long microChip) {

        for (PetDTO petDTO : petList) {

            if (petDTO.getMicroShip() == microChip) {

                System.out.println(petDTO);

            }

        }

    }

    public void countBySpecies(String species) {

        int dogs = 0;
        int cats = 0;

        for (PetDTO petDTO : petList) {

            if (petDTO.getSpecies().trim().equals("CANINO")) {
                dogs++;
            } else if (petDTO.getSpecies().trim().equals("FELINO")) {
                cats++;
            }
        }

        if (species.equals("CANINO")) {
            System.out.println(dogs);
        } else if (species.equals("FELINO")) {
            System.out.println(cats);
        }

    }

    public void findBypotentDangerousInNeighborhood(int n, String TopLast, String neighborhood) {

    }

    public void findByMultipleFields(String sex, String species, String size, String potentDangerous) {

        StringBuilder ids = new StringBuilder();

        for (PetDTO petDTO : petList) {

            if (petDTO.getSex().equals(sex) && petDTO.getSpecies().equals(species) &&
                    petDTO.getSize().equals(size)) {

                ids.append("\n").append(petDTO.getId());
            }


        }

        System.out.println(ids);

    }

    public void generateID() {

        for (PetDTO petDTO : petList) {

            String microShip = Long.toString(petDTO.getMicroShip());
            microShip = microShip.substring(microShip.length() - 3);

            String specie = petDTO.getSpecies().trim();
            specie = specie.substring(0, 1);

            String sex = petDTO.getSex().trim();
            sex = sex.substring(0, 1);

            String size = petDTO.getSize().trim();

            if (petDTO.getSize().equals("MINIATURA")) {
                size = "MI";
            } else {
                size = size.substring(0, 1);
            }

            String danger;

            if (petDTO.isPotentDangerous()) {
                danger = "T";
            } else {
                danger = "F";
            }

            String zone = petDTO.getNeighborhood().trim();

            String id = microShip + "-" + specie + sex + size + danger + "-" + zone;

            petDTO.setId(id);

        }

    }

}
