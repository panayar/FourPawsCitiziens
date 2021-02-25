package edu.unbosque.fourPawsCitiziens.controller;

import edu.unbosque.fourPawsCitiziens.model.PetDTO;
import edu.unbosque.fourPawsCitiziens.model.persistence.ReadCSV;

import java.nio.file.Paths;
import java.util.*;

public class Manager {

    private PetDTO pet;
    private ArrayList<PetDTO> petList;
    private ReadCSV readCSV;
    Scanner sc = new Scanner(System.in);
    String path;


    public Manager() {

        readCSV = new ReadCSV();
        petList = new ArrayList<>();
        initializeComponents();

    }

    public void initializeComponents() {
        try {
            System.out.println("Ingrese la direccion del archivo ");
            path = sc.next();

            readCSV.readCSV(path);

        } catch (Exception e) {

            e.printStackTrace();

        }
        uploadData();
        start();
    }

    public void uploadData() {

        readCSV.readCSV(path);

        for (int i = 0; i < readCSV.getData().size(); i++) {

            String m = String.valueOf(readCSV.getData().get(i));
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
                pet = new PetDTO("", ship, splitData[1].trim(), splitData[2].trim(), splitData[3].trim(), danger, splitData[5].trim());
                petList.add(pet);

            } catch (NumberFormatException o) {
            } catch (ArrayIndexOutOfBoundsException p) {
                p.printStackTrace();
            } catch (Exception EmptyAttributeException) {
            }

        }

        System.out.println("Proceso de carga finalizado");

    }

    public void assignID() {
        String id;

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

            id = microShip + "-" + specie + sex + size + danger + "-" + zone;
            String goodId = validateID(id);
            petDTO.setId(goodId);
        }

        System.out.println("El proceso de asignacion de ids ha finalizado");
    }

    public void findByMicroChip(long microChip) {

        String existe = "";

        for (PetDTO petDTO : petList) {

            if (petDTO.getMicroShip() == microChip) {

                existe = existe + petDTO;
                System.out.println(petDTO);

            }

        }
      if (existe.equals("")){
          System.out.println("No hay ningun animal con este microship :(");
          askAgain();
      }

    }

    public void countBySpecies(String species) {

        int dogs = 0;
        int cats = 0;

        for (PetDTO petDTO : petList) {

            if (petDTO.getSpecies().equals("CANINO")) {
                dogs++;
            } else if (petDTO.getSpecies().equals("FELINO")) {
                cats++;
            }
        }
        if (species.equals("CANINO")) {
            System.out.println("total caninos : " + dogs);
        } else if (species.equals("FELINO")) {
            System.out.println("total felinos : " + cats);
        } else {
            System.out.println("No tenemos esa especie :0");
        }

    }

    public void findBypotentDangerousInNeighborhood(int n, String topLast, String neighborhood) {

        ArrayList<PetDTO> petByNeighborhood = new ArrayList<>();
        try {
            for (PetDTO petDTO : petList) {

                if (petDTO.getNeighborhood().equals(neighborhood)) {

                    petByNeighborhood.add(petDTO);
                }
            }

            if (topLast.equals("TOP")) {

                for (int j = 0; j < n; j++) {

                    System.out.println(petByNeighborhood.get(j));
                }

            } else if (topLast.equals("LAST")) {

                Collections.reverse(petByNeighborhood);

                for (int j = 0; j < n; j++) {

                    System.out.println(petByNeighborhood.get(j));
                }

            } else {
                System.out.println("No hay registros para este barrio :v ");

            }
        } catch (NumberFormatException o) {
            System.out.println("Opps, metiste un dato mal ");
            start();
        }

    }

    public void findByMultipleFields(String sex, String species, String size, String potentDangerous) {

        boolean danger = false;
        boolean empty = false;

        if (potentDangerous.equals("SI")) {
            danger = true;
        }
        for (PetDTO pet : petList) {

            if (!pet.getId().equals("")) {

                if (pet.getSex().equals(sex) && pet.getSpecies().equals("" + species) &&
                        pet.getSize().equals(size) && pet.isPotentDangerous() == danger) {

                    System.out.println(pet.getId());
                }
                empty = false;
            } else {
                empty = true;

            }
        }

        if (empty) {
            System.out.println("Primero debes asignar los ids ");
        }

    }

    public String validateID(String id) {

        int pos = 3;

        for (PetDTO petDTO : petList) {

            try {

                if (petDTO.getId().equals(id)) {

                    throw new Exception("IdentifierExistsException");
                }

            } catch (Exception IdentifierExistsException) {

                String chip = Long.toString(petDTO.getMicroShip());
                chip = chip.substring(0, pos + 1);

                id = chip + id;

            }
        }
        return id;
    }

    public void start() {

        String opc;

        System.out.println("\n" + "Elige una opcion : " + "\n" +
                "1. Asignar ids a los animales " + "\n" +
                "2. Buscar a un animal por microship " + "\n" +
                "3. Contar cantidad de animales por especie" + "\n" +
                "4. Buscar animales potencialmente peligrosos por localidad (TOP / LAST )" + "\n" +
                "5. Encontrar animales por muchos campos" + "\n");

        opc = sc.next();
        sc.nextLine();

        switch (opc) {

            case "1":
                System.out.println("Esto puede tomar unos segundos :3 ... ");
                assignID();
                askAgain();

                break;
            case "2":
                try {
                System.out.println("Ingrese el microship");
                long ship = sc.nextLong();
                sc.nextLine();
                findByMicroChip(ship);

                }catch (Exception o ){
                    System.out.println("Ingresa un dato valido");

                }
                sc.nextLine();
                askAgain();


                break;
            case "3":
                System.out.println("Ingrese especie a contar (CANINO / FELINO)");
                String specie = sc.next().toUpperCase();
                countBySpecies(specie);
                askAgain();

                break;
            case "4":


                try {
                    System.out.println("Numero de incidencias ");
                    int incidencias = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Quiere obtener los primeros " + incidencias + " o los ultimos " + incidencias + "(TOP/LAST)");
                    String topOrLast = sc.next().toUpperCase();
                    sc.nextLine();
                    System.out.println("Ingrese barrio : \n");
                    String neigborhood = sc.next().toUpperCase();
                    findBypotentDangerousInNeighborhood(incidencias, topOrLast, neigborhood);
                }catch (Exception e){
                    System.out.println("Oops algo salio mal");
                }
                sc.nextLine();
                askAgain();

                break;

            case "5":

                try {
                    System.out.println("Ingrese sexo (MACHO/HEMBRA)");
                    String sex = sc.next().toUpperCase();
                    sc.nextLine();
                    System.out.println("Ingrese la especie (FELINO/CANINO)");
                    specie = sc.next().toUpperCase();
                    sc.nextLine();
                    System.out.println("Ingrese tamano (MINIATURA/PEQUENO/MEDIANO/GRANDE)");
                    String size = sc.next().toUpperCase();
                    sc.nextLine();
                    System.out.println("Es peligroso (SI/NO)");
                    String danger = sc.next().toUpperCase();
                    findByMultipleFields(sex, specie, size, danger);
                }catch (Exception e){
                    System.out.println("Oops algo salio mal");
                }
                askAgain();

                break;

            default:
                System.out.println("Opcion no valida, vuelve a intentar");
                start();
                break;

        }

    }

    public void askAgain() {

        System.out.println("\n" + "Quieres hacer algo mas ? SI/NO" + "\n");
        String resp = sc.next().toUpperCase();

        if (resp.equals("SI")) {
            start();
        } else {
            System.out.println("Chau!");
        }

    }

}
