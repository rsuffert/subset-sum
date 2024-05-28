import java.util.List;

public class App {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 14, 22};

        List<List<Integer>> solutions = SubsetSum.calculate(numbers, 30);

        System.out.println("Solutions found: ");
        int counter = 0;
        for (List<Integer> solution : solutions) {
            counter++;
            System.out.printf("\t%d. %s\n", counter, solution);
        }
    }
}