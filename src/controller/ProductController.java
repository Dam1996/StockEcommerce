package controller;

import Service.File;
import model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ProductController implements File {
    private BufferedReader br;
    private String row;
    private String rowAux[] = null;
    private ArrayList<Product> products = new ArrayList<Product>();

    public ProductController() {
        products = this.load();
    }

    @Override
    public ArrayList load() {
        try {
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/resources/product.csv")); // PRODUCTOS CSV
            while((row = br.readLine()) != null){
                rowAux = row.split(",");
                products.add(new Product(Integer.parseInt(rowAux[0]),Integer.parseInt(rowAux[1].trim())));
            }
            br.close();
            row = null;
            rowAux = null;
        } catch (Exception e) {
            System.out.println("No se encontro el archivo");
        }
        return products;
    }

    public ArrayList<Product> filter(){

        SizeController sizeController = new SizeController();
        ArrayList<Product> productsFilters = new ArrayList<Product>();


        for ( Product product : products
             ) {
            if (sizeController.existeElProducto(product.getId())) productsFilters.add(product);
        }

        return productsFilters;
    }
}
