package edu.unbosque.fourPawsCitiziens.model.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReadCSV {

    Scanner sc = new Scanner(System.in);
    ArrayList<ArrayList<String>> data;
    Path filePath;
    String urlPath ;

    public ReadCSV(){

    }

    public void readCSV (String m ){


        try {
            data  = new ArrayList<>();
            filePath = Paths.get(m);

            BufferedReader br = Files.newBufferedReader(filePath);
            String line;

            while ((line = br.readLine()) != null){
                String [] dataInLine  = line.split(";");
                ArrayList<String> temporalData = new ArrayList<>();
                Collections.addAll(temporalData, dataInLine);
                data.add(temporalData);
            }

        }catch (IOException e ){

            System.out.println("El path no es correcto, ingreselo nuevamente");
            urlPath = sc.next();
            readCSV(urlPath);
            sc.nextLine();

        }catch (Exception e){

            System.out.println("El path no es correcto, ingreselo nuevamente");
            urlPath = sc.next();
            readCSV(urlPath);
            sc.nextLine();
        }

    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
}
