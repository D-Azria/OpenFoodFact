package fr.diginamic.entites;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class OpenFoodFactProductData {

    List<String> OpenFoodFactProductData;

    public List<String> getOpenFoodFactProductDatas() {
        return OpenFoodFactProductData;
    }

    public void setProduits(List<String> OpenFoodFactProductData) {
        this.OpenFoodFactProductData = OpenFoodFactProductData;
    }

    public void addSingleProduct(String singleProduct){
        this.OpenFoodFactProductData.add(singleProduct);
    }
}
