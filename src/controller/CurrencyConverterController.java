package controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import domain.currency.dto.CurrencyConverterDto;
import domain.currency_code.CurrencyCodeRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverterController {

    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/a582d4218e79398607eecb06";
    private final HttpClient client;
    private final Gson gson;

    public CurrencyConverterController() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    private String sendRequest(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + endpoint))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonResponse = response.body();
        System.out.println("Respuesta: " + jsonResponse);
        return jsonResponse;
    }

    public CurrencyConverterDto conversion(String baseCode) throws IOException, InterruptedException {
        String jsonResponse = sendRequest("latest/" + baseCode);
        return deserializeJson(jsonResponse);
    }

    public CurrencyConverterDto conversionPar(String baseCode, String targetCode) throws IOException, InterruptedException {
        String jsonResponse = sendRequest("/pair/" + baseCode + "/" + targetCode);
        return deserializeJson(jsonResponse);
    }

    public CurrencyConverterDto conversionParMonto(String baseCode, String targetCode, Double conversionRate) throws IOException, InterruptedException {
        String jsonResponse = sendRequest("/pair/" + baseCode + "/" + targetCode + "/" + conversionRate);
        return deserializeJson(jsonResponse);
    }

    public void printSupportedCodes() {
        CurrencyCodeRepository.getAllCurrencyCodes().values().forEach(System.out::println);
    }

    private CurrencyConverterDto deserializeJson(String jsonResponse) {
        try {
            return gson.fromJson(jsonResponse, CurrencyConverterDto.class);
        } catch (JsonSyntaxException e) {
            System.err.println("Failed to deserialize JSON: " + e.getMessage());
            return null;
        }
    }

}
