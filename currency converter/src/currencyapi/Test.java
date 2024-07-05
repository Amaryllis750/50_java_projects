package currencyapi;

import java.net.URISyntaxException;

public class Test {
    public static void main(String[] args) {
        String oldCurrency = "US Dollars";
        String newCurrency = "Japanese Yen";
        double value = 43.54;

        CurrencyAPI.setupCurrencySymbolMap();
        
        try{
            double newValue = CurrencyAPI.convert(oldCurrency, newCurrency, value);
            System.out.println(newValue);
        }
        catch(URISyntaxException e){
            e.printStackTrace();
        }
    }
}
