import controller.ProductController;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        ArrayList<Product> products = productController.filter();

        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getSequence().compareTo(o2.getSequence());
            }
        });

        for ( Product product : products
             ) {
            System.out.print(product.toString());
        }
    }
}