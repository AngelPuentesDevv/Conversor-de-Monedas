package controller;

import domain.currency_code.CurrencyCodeRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverterController {

    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/a582d4218e79398607eecb06";
    private final HttpClient client;

    public CurrencyConverterController() {
        this.client = HttpClient.newHttpClient();
    }

    private String sendRequest(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + endpoint))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String conversion(String baseCode) throws IOException, InterruptedException {
        return sendRequest("/latest/"+baseCode);
    }

    public String conversionPar(String baseCode, String targetCode) throws IOException, InterruptedException {
        return sendRequest("/pair/"+baseCode+"/"+targetCode);
    }

    public String conversionParMonto(String baseCode, String targetCode, Double conversionRate) throws IOException, InterruptedException {
        return sendRequest("/pair/"+baseCode+"/"+targetCode+"/"+conversionRate);
    }

    public void printSupportedCodes() {
        CurrencyCodeRepository.getAllCurrencyCodes().values().forEach(System.out::println);
    }

}
