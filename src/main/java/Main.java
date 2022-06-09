import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final short[] array = new short[50000];

    public static void main(String[] args) {
        System.out.println("Использовать строку по умолчанию? (Y/N) ");
        Scanner scanner = new Scanner(System.in);
        StringBuilder line = new StringBuilder("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("n")) {
            System.out.println("Ввод строки: ");
            line = new StringBuilder(scanner.nextLine());
        } else if (!answer.equals("y")) {System.out.println("Некоррентный ответ"); System.exit(0);}

        System.out.println(interprete(line.toString()));
    }
    public static String interprete(String line) {
        StringBuilder outputString = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] charArray = line.toCharArray();
        int line_pointer = 0;
        int array_pointer = 0;
        List<Integer> list = new ArrayList<>();

        while (line_pointer < charArray.length) {
            switch (charArray[line_pointer]) {
                case '+' -> {
                    array[array_pointer] = (short) ((array[array_pointer] + 1) > 255 ? 0 : array[array_pointer] + 1);
                    line_pointer++;
                }
                case '-' -> {
                    array[array_pointer] = (short) ((array[array_pointer] - 1) < 0 ? 255 : array[array_pointer] - 1);
                    line_pointer++;
                }
                case '>' -> {
                    array_pointer++;
                    line_pointer++;
                }
                case '<' -> {
                    array_pointer--;
                    line_pointer++;
                }
                case '.' -> {
                    outputString.append((char) array[array_pointer]);
                    line_pointer++;
                }
                case ',' -> {
                    try {
                        array[array_pointer] = (short) Integer.parseInt(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    line_pointer++;
                }
                case '[' -> {
                    list.add(line_pointer);
                    line_pointer++;
                }
                case ']' -> line_pointer = array[array_pointer] > 0 ? list.remove(list.size() - 1) : line_pointer + 1;
                default -> line_pointer++;
            }
        }
        return outputString.toString();
    }
}
