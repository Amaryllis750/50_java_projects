package currencyapi;
import java.util.*;
import currencyapi.Currency;

public class AvailableCurrency {
    private HashMap<String, Currency> data;

    public HashMap<String, Currency> getData() {
        return data;
    }
    
    public String getCurrencyName(String key){
        return this.getData().get(key).getName();
    }
    
    public String getCurrencyCode(String key){
        // get's the currency symbol for a particular currency
        return this.getData().get(key).getCode();
    }
}


