package currencyapi;

import java.util.*;

public class ConversionIndex {
    private HashMap<String, Double> data;

    public Double getIndex(String currencySymbol){
        return data.get(currencySymbol);
    }

    public HashMap<String, Double> getData(){
        return this.data;
    }
}
