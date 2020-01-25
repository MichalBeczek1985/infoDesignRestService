package pl.michal.motorest.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pl.michal.motorest.domain.Cars;

import java.io.*;
import java.util.HashMap;

@Service
public class DataService{
    private final String FILE_NAME="samochody.csv" ;
    HashMap<Long,Cars> carsMap = new HashMap<>();

    private void ReadData(){
        File file = null;
        try {
            file = new ClassPathResource(FILE_NAME).getFile();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF8"));
            String line;
            in.readLine();
            while ((line = in.readLine()) != null) {
                if (line.trim().length() > 0) {
                    String[] personCsv = line.split(",");
                    Cars car = new Cars();
                    car.setId(Long.valueOf(personCsv[0]));
                    car.setNazwa(personCsv[1]);
                    car.setDataZakupu(personCsv[2]);
                    car.setKolor(personCsv[3]);
                    carsMap.put(car.getId(),car);
                }}
            } catch(IOException e){
                System.out.println("brak pliku z danymi");
            }
        }



    public HashMap<Long, Cars> getAll() {
        ReadData();
        return carsMap;
    }


    public Cars getById(Long id) {
        ReadData();
        return carsMap.get(id);
    }


    public Cars add(Cars car) {
        ReadData();
        Cars cars = carsMap.get(car.getId());
        if (cars==null){
            saveCar(car);
        }
        else if (!cars.equals(car)){
            removeById(cars.getId());
            saveCar(car);
        }
        return car;
    }

    private void saveCar(Cars car) {
        String s = car.toString();
        try {
            File file = new ClassPathResource(FILE_NAME).getFile();
            FileWriter writer = new FileWriter(file,true);
            writer.append("\n\n");
            writer.append(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeById(Long id){
        ReadData();
        Cars cars = carsMap.get(id);
        if(cars!=null){
            carsMap.remove(cars.getId());
            try {
            File file = new ClassPathResource(FILE_NAME).getFile();
            BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF8"));
                String line;
                String firstLine = in.readLine();
                FileWriter writer = new FileWriter(file,false);
                writer.append(firstLine);
                for (Cars c : carsMap.values()){
                    String s = c.toString();
                writer.append("\n\n");
                writer.append(s);
                }
                writer.flush();
                writer.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
