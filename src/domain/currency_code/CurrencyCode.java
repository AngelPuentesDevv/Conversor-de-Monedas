package domain.currency_code;

public class CurrencyCode {

    //Atributos que representan las características de las monedas
    private String code;
    private String name;
    private String country;

    //Constructor para la clase CurrencyCode
    public CurrencyCode(String code, String name, String country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }

    //Métodos get para acceder a los valores de CurrencyCode


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return  String.format("%s\t%s\t%s", code, name, country);
    }
}
