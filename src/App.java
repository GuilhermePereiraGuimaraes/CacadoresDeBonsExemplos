import java.time.LocalDate;
import java.util.Scanner;

public class App {
    private static final Scanner SCN = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // App2.main(args);
        LocalDate myDate = LocalDate.parse("2020-09-07");
        System.out.println(myDate.getMonthValue());
    }
}
