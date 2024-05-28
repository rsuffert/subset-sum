import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetSum {
    /**
     * Solves the Subset Sum problem.
     * @param numbers the set of numbers to investigate.
     * @param desiredSum the sum desired to be found in {@code numbers}.
     * @return a list containing {@code false} at the positions of elements not included in the solution and {@code true} at the positions of elements included, relative to the
     * SORTED {@code numbers} list.
     */
    public static List<Boolean[]> calculate(int[] numbers, int desiredSum) {
        List<Boolean[]> solutions = new LinkedList<>();

        // sort original array
        if (numbers == null || numbers.length == 0) return solutions;
        int[] numbersSorted = Arrays.stream(numbers).sorted().toArray();
        
        // check if there is a possible solution
        int numbersSum = Arrays.stream(numbersSorted).sum();
        if(numbersSorted[0] > desiredSum || numbersSum < desiredSum) return solutions;

        // calculate solution
        sumOfSubset(numbersSorted, desiredSum, 0, 0, numbersSum, new Boolean[numbersSorted.length], solutions);
        return solutions;
    }

    public static void sumOfSubset(int[] numbers, int desiredSum, int sumOfAnalyzedItems, int currentItemIdx, int sumOfLeftItems, Boolean[] currentSolution, List<Boolean[]> solutions) {
        // generate left child
        currentSolution[currentItemIdx] = true;
        if (sumOfAnalyzedItems+numbers[currentItemIdx] == desiredSum) { // solution has been found
            for (int i=currentItemIdx+1; i<currentSolution.length; i++) // fill with 'false' the remaining positions (previously 'null')
                currentSolution[i] = false;
            solutions.add(Arrays.copyOf(currentSolution, currentSolution.length));
        }
        else if (sumOfAnalyzedItems+numbers[currentItemIdx]+numbers[currentItemIdx+1] <= desiredSum) // bounding function (check expansion to the left)
            sumOfSubset(numbers, desiredSum, sumOfAnalyzedItems+numbers[currentItemIdx], currentItemIdx+1, sumOfLeftItems-numbers[currentItemIdx], currentSolution, solutions);
        
        // generate right child
        if ((sumOfAnalyzedItems+sumOfLeftItems-numbers[currentItemIdx] >= desiredSum) && 
            (sumOfAnalyzedItems+numbers[currentItemIdx+1] <= desiredSum)) { // bounding function (check expansion to the right)
            currentSolution[currentItemIdx] = false;
            sumOfSubset(numbers, desiredSum, sumOfAnalyzedItems, currentItemIdx+1, sumOfLeftItems-numbers[currentItemIdx], currentSolution, solutions);
        }
    }
}