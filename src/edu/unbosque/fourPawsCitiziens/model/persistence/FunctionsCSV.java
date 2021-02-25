package edu.unbosque.fourPawsCitiziens.model.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class FunctionsCSV {

    ArrayList<ArrayList<String>> data;

    public FunctionsCSV(){

    }

    public void readCSV (){

        data  = new ArrayList<>();
        Path filePath = Paths.get("C:\\Users\\paula\\FourPawsCitiziens\\src\\datos\\pets-citizens.csv");


        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String line;

            while ((line = br.readLine()) != null){
                String [] dataInLine  = line.split(";");
                ArrayList<String> temporalData = new ArrayList<>();
                Collections.addAll(temporalData, dataInLine);
                data.add(temporalData);
            }

        }catch (IOException e ){

            e.printStackTrace();
        }

    }

    public void editCSV (){




    }


    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }
}
