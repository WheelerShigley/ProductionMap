package graph;

public class StringHelper {
    public static String getNumberString(double number) {
        if(number%1.0 == 0.0) {
            return Integer.toString( (int)number );
        } else {
            double roundedNumber = Math.round(number*10_000.0)/10_000.0;
            String readableNumber = "";
            if(roundedNumber != number) {
                readableNumber += "~";
            }
            readableNumber += Double.toString(
                Math.round(number*10_000.0)/10_000.0
            );
            return readableNumber;
        }
    }
    public static String getNumberString(int number) {
        return Integer.toString(number);
    }
}
