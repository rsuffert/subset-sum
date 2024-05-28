import java.util.List;

public class App {
    public static void main(String[] args) {
        List<List<Integer>> solutions = SubsetSum.calculate(new int[]{2, 3, 5, 6, 8, 10}, 10);

        System.out.printf("Found %d solution(s):\n", solutions.size());
        int counter = 0;
        for (List<Integer> solution : solutions) {
            counter++;
            System.out.printf("\t%d. %s\n", counter, solution);
        }
    }
}