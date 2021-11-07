import java.util.Objects;
import java.util.Scanner;

public class Test {
    static Parser pars = new Parser();
    static Converter conv = new Converter();

    public static void main(String[] args) {
        while (true) {
            Scanner scanString = new Scanner(System.in);
            System.out.println("Введите пример:");
            String stringMain = scanString.nextLine();
            String[] operands = stringMain.split(" ");
            int numb1, numb2;
            String operation;
            try {
                if (operands.length == 1) {
                    throw new Exception("строка не является математической операцией");
                }
                String[] strArr = pars.parsArray(operands);
                numb1 = Integer.parseInt(strArr[0]);
                operation = strArr[1];
                numb2 = Integer.parseInt(strArr[2]);
                calculatedAndCheck(numb1, numb2, operation, operands);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void calculatedAndCheck(int numb1, int numb2, String operation, String[] operands) throws Exception {

        if ((numb1 < 0 || numb1 > 10) || (numb2 < 0 || numb2 > 10)) {
            throw new Exception("Вы ввели недопустимое значение \nДоступный диапазон от 0 до 10 включительно");
        }

        if (((!Objects.equals(operation, "*"))
                && (!Objects.equals(operation, "/"))
                && (!Objects.equals(operation, "+"))
                && (!Objects.equals(operation, "-")))
                || (operands.length > 3)) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        switch (operation) {
            case "*":
                int result1 = numb1 * numb2;
                shortingIf(numb1, numb2, result1, operation);
                break;
            case "/":
                if (numb1 % numb2 != 0) {
                    throw new Exception("Калькулятор не может делить с остатком в итоге");
                } else {
                    int result2 = numb1 / numb2;
                    shortingIf(numb1, numb2, result2, operation);
                }
                break;
            case "+":
                int result3 = numb1 + numb2;
                shortingIf(numb1, numb2, result3, operation);
                break;
            case "-":
                int result4 = numb1 - numb2;
                if (result4 < 1) {
                    throw new Exception("в римской системе нет отрицательных чисел");
                }
                shortingIf(numb1, numb2, result4, operation);
                break;
        }
    }

    private static void shortingIf(int numb1, int numb2, int result, String operation) {
        if (!pars.checkRomanFlag) {
            String resultEnd = conv.arabicToRomanConverter(result);
            String numb01 = conv.arabicToRomanConverter(numb1);
            String numb02 = conv.arabicToRomanConverter(numb2);
            System.out.println("Решение: " + numb01 + " " + operation + " " + numb02 + " = " + resultEnd);
        } else {
            System.out.println("Решение: " + numb1 + " " + operation + " " + numb2 + " = " + result);
        }
    }
}
