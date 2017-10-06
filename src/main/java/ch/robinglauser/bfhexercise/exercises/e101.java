package ch.robinglauser.bfhexercise.exercises;

public class e101 {

    private static String[] names = {
            "Robin Glauser",
            "Niesenstrasse 26C",
            "3600 Thun"
    };

    public static void main(String[] args) {
        e101 e101 = new e101();
        e101.printName(names);
    }

    /**
     * Prints address with a surrounding box.
     *
     * @param address Array with address to print
     */
    public void printName(String[] address) {
        int width = getLongestStringLength(address);
        printFrame(width + 2);
        for (String line : address) {
            System.out.print("| " + line);
            printString(width - line.length(), " ");
            System.out.print(" |\n");
        }
        printFrame(width + 2);
    }

    /**
     * Get the longest string length from an array of strings
     *
     * @param array Array with strings
     * @return The longest string length
     */
    public int getLongestStringLength(String[] array) {
        int length = 0;
        for (String value : array) {
            if (value.length() > length) {
                length = value.length();
            }
        }
        return length;
    }


    /**
     * Print top and bottom frame
     *
     * @param width Width of frame
     */
    public void printFrame(int width) {
        System.out.print("+");
        printString(width, "-");
        System.out.print("+\n");
    }

    /**
     * Print a string multiple times to the standard output
     *
     * @param count  Number of times the string should be printed
     * @param string String to print multiple times
     */
    public void printString(int count, String string) {
        for (int i = 0; i < count; i++) {
            System.out.print(string);
        }
    }


}
