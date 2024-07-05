package currencyapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;

import com.google.gson.Gson;

import currencyapi.AvailableCurrency;
import currencyapi.ConversionIndex;
import static currencyapi.APIConstants.*;

public class CurrencyAPI {
    public static HashMap<String, String> currencySymbols = new HashMap<>();

    public static void setupCurrencySymbolMap(){
        HttpResponse<String> response = connectToAPI("https://api.freecurrencyapi.com/v1/currencies", "apikey", APIKEY);
        Gson gson = new Gson();
        AvailableCurrency availableCurrencies = gson.fromJson(response.body(), AvailableCurrency.class);
        HashMap<String, Currency> data = availableCurrencies.getData();
        for(String key : data.keySet()){
            String currencyName = availableCurrencies.getCurrencyName(key);
            String currencySymbol = availableCurrencies.getCurrencyCode(key);
            currencySymbols.put(currencyName, currencySymbol);
        }
    }

    // this method is used to connect to the api
    private static HttpResponse<String> connectToAPI(String URI, String...headers){
        try{
            HttpRequest getRequest = HttpRequest.newBuilder()
                                    .uri(new URI(URI))
                                    .headers(headers)
                                    .GET()
                                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse;
            try{
                httpResponse = httpClient.send(getRequest, BodyHandlers.ofString());
                return httpResponse;
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            
        }
        catch(URISyntaxException e){
            System.out.println("This is not a valid URI");
        }
        return null;
    }

    public static List<String> getAvailbleCurrencies(){
        // create the list of currencies that will be returned
        List<String> currencies = new ArrayList<>();
        Gson gson = new Gson();
        HttpResponse<String> response =  connectToAPI("https://api.freecurrencyapi.com/v1/currencies", "apikey", APIKEY);
        
        // check if the response is not null
        if(response != null){
            AvailableCurrency availableCurrencies = gson.fromJson(response.body(), AvailableCurrency.class);
            for(String key : availableCurrencies.getData().keySet()){
                // get the name of each currency and add it to the list
                String currencyName = availableCurrencies.getCurrencyName(key);
                String currencySymbol = availableCurrencies.getCurrencyCode(key);
                currencies.add(currencyName);
                currencySymbols.put(currencyName, currencySymbol);
        }
        return currencies;
        }
        else{
            System.out.println("Sorry there must have been an issue with getting the result from the server");
            System.out.println("We were not able to get the list of available currencies");
            return currencies;
        }
    }

    public static double convert(String oldCurrency, String newCurrency,double oldCurrencyValue) throws URISyntaxException{
        double newCurrencyValue = 0.00;
        HttpResponse<String> httpResponse = connectToAPI("https://api.freecurrencyapi.com/v1/latest", "apikey", APIKEY, "base_currency", oldCurrency);


        // check if the response is not null
        if(httpResponse != null){
            Gson gson = new Gson();
            ConversionIndex indexes = gson.fromJson(httpResponse.body(), ConversionIndex.class);
            HashMap<String, Double> data = indexes.getData();
            System.out.println(data);
            // get the symbol of the currency that you want to convert to
            String currencySymbol = currencySymbols.get(newCurrency);
            System.out.println("Currency Symbol: " + currencySymbol + "\n");
            // get the conversion index of that currency symbol
            double index = data.get(currencySymbol);
            System.out.println("Index: " + index + "\n");

            // conversion...
            newCurrencyValue = oldCurrencyValue * index;
            return newCurrencyValue;
        }
        else{
            System.out.println("Sorry, there was an error when trying to get the response from the server");
            System.out.println("We are not able to convert right now");
            return newCurrencyValue;
        }
    }
}
