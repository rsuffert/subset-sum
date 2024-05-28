import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 14, 22};

        List<Byte[]> solutions = SubsetSum.calculate(numbers, 30);     
        
        System.out.printf("Sorted list: %s\n", Arrays.toString(Arrays.stream(numbers).sorted().toArray()));
        System.out.println("Results: ");
        int counter = 0;
        for (Byte[] solution : solutions) {
            counter++;
            System.out.printf("\t%d. %s\n", counter, Arrays.toString(solution));
        }
    }
}