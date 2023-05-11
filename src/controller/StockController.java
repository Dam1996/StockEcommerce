package controller;

import Service.File;
import model.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StockController implements File {
    private BufferedReader br;
    private String row;
    private String rowAux[] = null;
    private ArrayList<Stock> stocks = new ArrayList<Stock>();

    public StockController() {
        stocks = this.load();
    }

    @Override
    public ArrayList load() {
        try {
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/resources/stock.csv")); // PRODUCTOS CSV
            while((row = br.readLine()) != null){
                rowAux = row.split(",");
                stocks.add(new Stock(Integer.parseInt(rowAux[0]),Integer.parseInt(rowAux[1].trim())));
            }
            br.close();
            row = null;
            rowAux = null;
        } catch (Exception e) {
            System.out.println("No se encontro el archivo");
        }
        return stocks;
    }

    // Comprueba si una talla tiene stock
    public Boolean tieneStock( Integer sizeId) {
        Stock stockR =  stocks.stream().filter(stock -> sizeId.equals(stock.getSizeId())).findFirst().orElse(null);
        return (stockR == null) ? false : stockR.getQuantity() > 0 ? true : false;// if ternario que devuelve verdadero solo si hay stock disponible(mayor a cero)
    }
}
