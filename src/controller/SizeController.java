package controller;

import Service.File;
import model.Size;
import model.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SizeController implements File {
    private BufferedReader br;
    private String row;
    private String rowAux[] = null;
    private ArrayList<Size> sizes = new ArrayList<Size>();
    private StockController stockController = new StockController();

    public SizeController() {
        sizes = this.load();
    }

    @Override
    public ArrayList load() {
        try {
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/resources/size-1.csv")); // PRODUCTOS CSV
            while((row = br.readLine()) != null){
                rowAux = row.split(",");
                sizes.add(new Size(Integer.parseInt(rowAux[0]),Integer.parseInt(rowAux[1].trim()),Boolean.parseBoolean(rowAux[2].trim()),Boolean.parseBoolean(rowAux[3].trim())));
            }
            br.close();
            row = null;
            rowAux = null;
        } catch (Exception e) {
            System.out.println("No se encontro el archivo");
        }
        return sizes;
    }
    public ArrayList getSpecialSizes() {
        ArrayList<Size> specialSizesAux = new ArrayList<Size>();// Especiales
        for ( Size size : sizes ) {
            if(size.getSpecial())  specialSizesAux.add(size);
        }
        return specialSizesAux;
    }

    public ArrayList getNotSpecialSizes() {
        ArrayList<Size> specialNotSizesAux = new ArrayList<Size>();// Especiales
        for ( Size size : sizes ) {
            if(!size.getSpecial())  specialNotSizesAux.add(size);
        }
        return specialNotSizesAux;
    }

    public boolean tieneStockEspecial(Integer productId) {

        ArrayList<Size> notSpecialSizesAux = this.getNotSpecialSizes();// No especiales

        for ( Size notSpecialSizeAux : notSpecialSizesAux
             ) {
            if (notSpecialSizeAux.getProductId() == productId && (stockController.tieneStock(notSpecialSizeAux.getId()) || notSpecialSizeAux.getBackSoon()))
                return true;
        }
        return false;
    }
// Busca todas las tallas con stocks disponible
    public ArrayList getStockFilter(){
        ArrayList<Size> specialSizes = this.getSpecialSizes();
        ArrayList<Size> notSpecialSizes = this.getNotSpecialSizes();
        ArrayList<Size> sizesFilters = new ArrayList<Size>();

        for (Size notSpecialSize : notSpecialSizes
             ) {
            if (stockController.tieneStock(notSpecialSize.getId()) || notSpecialSize.getBackSoon()) sizesFilters.add(notSpecialSize);
        }
            for (Size specialSize : specialSizes) {
                if ( (stockController.tieneStock(specialSize.getId()) || specialSize.getBackSoon()) && this.tieneStockEspecial(specialSize.getProductId()))
                    sizesFilters.add(specialSize);
            }
        return sizesFilters;
    }

    // Comprueba si existe el producto en las tallas con stocks disponible
    public Boolean existeElProducto( Integer productId) {
        ArrayList<Size> sizesFiltersAux = this.getStockFilter();
        Size sizeAux =  sizesFiltersAux.stream().filter(size -> productId.equals(size.getProductId())).findFirst().orElse(null);
        return (sizeAux == null) ? false : true;// if ternario que devuelve verdadero solo si  existe el producto
    }
}
