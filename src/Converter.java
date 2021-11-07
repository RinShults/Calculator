public class Converter {
    private final int[] arabicNumbers = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100};
    private final String[] romanNumbers =
            new String[]{"I ", "IV", "V ", "IX", "X ", "XL", "L ", "XC", "C "};

    public String arabicToRomanConverter(int arabicInput) {
        if (arabicInput == 0) {
            return "O";
        }
        int j = 8;
        int i = 0;
        StringBuilder result = new StringBuilder();

        while (i != arabicInput) {
            i += arabicNumbers[j];
            result.append(romanNumbers[j]);
            if (i == arabicInput) {
                break;
            } else if (i > arabicInput) {
                i -= arabicNumbers[j];
                result.delete(result.length() - 2, result.length());
                j--;
            }
        }
        return result.toString().replaceAll(" ", "");
    }


    public int decodeSingle(char letter) {
        switch (letter) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                throw new NumberFormatException("некорректный ввод уравнения");
        }
    }

    public int romanToArabicConverter(String roman) {
        int result = 0;
        String uRoman = roman.toUpperCase();
        for (int i = 0; i < uRoman.length() - 1; i++) {
            if (decodeSingle(uRoman.charAt(i)) < decodeSingle(uRoman.charAt(i + 1))) {
                result -= decodeSingle(uRoman.charAt(i));
            } else {
                result += decodeSingle(uRoman.charAt(i));
            }
        }
        result += decodeSingle(uRoman.charAt(uRoman.length() - 1));
        return result;
    }
}
