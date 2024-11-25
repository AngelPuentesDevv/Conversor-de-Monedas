package domain.currency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.CurrencyConverterController;
import domain.currency.dto.CurrencyConverterDto;
import util.ArchiveGenerator;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverterMenu {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        CurrencyConverterController controller = new CurrencyConverterController();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        boolean continuar = true;
        while (continuar) {
            System.out.println("""
                    ¡Bienvenido a nuestro conversor de monedas! 
                    1. Conversión completa (De una moneda a todas las otras)
                    2. Conversión básica (De una moneda a otra)
                    3. Conversión básica indicando el monto
                    4. Códigos soportados
                    5. Finalizar la conversión
                    Por favor, elija una opción: """);
            int opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Por favor, indique el código de la moneda que desea convertir: ");
                    CurrencyConverterDto conversion = controller.conversion(lectura.nextLine());
                    System.out.println(gson.toJson(conversion));
                    ArchiveGenerator.saveJson(gson.toJson(conversion));
                    break;
                case 2:
                    System.out.println("Por favor, indique la moneda base: ");
                    String baseCode = lectura.nextLine();
                    System.out.println("Ahora indique la moneda objetivo: ");
                    String targetCode = lectura.nextLine();
                    CurrencyConverterDto conversionPar = controller.conversionPar(baseCode, targetCode);
                    System.out.println(gson.toJson(conversionPar));
                    ArchiveGenerator.saveJson(gson.toJson(conversionPar));
                    break;
                case 3:
                    System.out.println("Por favor, indique la moneda base: ");
                    baseCode = lectura.nextLine();
                    System.out.println("Ahora indique la moneda objetivo: ");
                    targetCode = lectura.nextLine();
                    System.out.println("Por último, indique el monto a convertir: ");
                    double amount = lectura.nextDouble();
                    lectura.nextLine();
                    CurrencyConverterDto conversionParMonto = controller.conversionParMonto(baseCode, targetCode, amount);
                    System.out.println(gson.toJson(conversionParMonto));
                    ArchiveGenerator.saveJson(gson.toJson(conversionParMonto));
                    break;
                case 4:
                    System.out.println("Los códigos soportados son: ");
                    controller.printSupportedCodes();
                    break;
                case 5:
                    System.out.println("Gracias por utilizar nuestro conversor de monedas. Excelente día!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción invalida. Por favor elije una opción del menú.");
                    break;
            }
        }

    }

}
