package domain.currency;

public class CurrencyConverter {

    //Atributos que representan las características del conversor de monedas

    private String baseCode;
    private String targetCode;
    private Double conversionRate;
    private Double conversionResult;

    //Constructor de la clase conversor

    public CurrencyConverter(String baseCode, String targetCode) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
    }

    //Métodos get para los atributos de la clase


    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public void setConversionResult(Double conversionResult) {
        this.conversionResult = conversionResult;
    }

    @Override
    public String toString() {
        return """
                ¡Conversión exitosa!
                De: %s 
                A: %s 
                Tasa de conversión: %.2f
                Resultado: %.2f
                """.formatted(baseCode, targetCode, conversionRate, conversionResult);
    }
}
