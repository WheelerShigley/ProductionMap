package graph;

public class StringHelper {
    public static String getNumberString(double number) {
        //enforce a minimum
        if(number < 0.0001) {
            return "~0";
        }

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
