package domain.currency.dto;

import domain.currency.CurrencyConverter;

public record CurrencyConverterDto(
        String baseCode,
        String targetCode,
        Double conversionRate,
        Double conversionResult
) {
    public CurrencyConverterDto(CurrencyConverter currencyConverter) {
        this(currencyConverter.getBaseCode(), currencyConverter.getTargetCode(), currencyConverter.getConversionRate(), currencyConverter.getConversionRate());
    }
}
