public class Parser {
    private Converter convert = new Converter();
    public boolean checkRomanFlag = true;

    public String[] parsArray(String[] line) {
        try {
            line[0] = Integer.toString(checkArabic(line[0]));
            line[2] = Integer.toString(checkArabic(line[2]));
        } catch (NumberFormatException exception) {
            line[0] = String.valueOf(checkRoman(line[0]));
            line[2] = String.valueOf(checkRoman(line[2]));
            checkRomanFlag = false;
        }
        return line;
    }

    private int checkArabic(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    private int checkRoman(String str) {
        return convert.romanToArabicConverter(str);
    }
}