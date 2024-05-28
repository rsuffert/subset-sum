import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetSum {
    /**
     * Solves the Subset Sum problem.
     * @param numbers the list of numbers to investigate.
     * @param desiredSum the desired sum to be found in {@code numbers}.
     * @return a list of lists of integers where each list represents a possible set of numbers in {@code numbers} that add up to {@code desiredSum}.
     */
    public static List<List<Integer>> calculate(int[] numbers, int desiredSum) {
        List<List<Integer>> solutions = new LinkedList<>();

        // sort original array
        if (numbers == null || numbers.length == 0) return solutions;
        int[] numbersSorted = Arrays.stream(numbers).sorted().toArray();
        
        // check if there is a possible solution
        int numbersSum = Arrays.stream(numbersSorted).sum();
        if(numbersSorted[0] > desiredSum || numbersSum < desiredSum) return solutions; // no solution!

        // calculate solution
        sumOfSubset(numbersSorted, desiredSum, 0, 0, numbersSum, new Boolean[numbersSorted.length], solutions);
        return solutions;
    }

    /**
     * Effectively calculates the Subset Sum problem using backtracking.
     * @param numbers the list of numbers to investigate.
     * @param desiredSum the sum desired to be found in {@code numbers}.
     * @param sumOfAnalyzedItems the current sum of all items that have already been analyzed.
     * @param currentItemIdx the index of the current element in {@code numbers} being investigated.
     * @param sumOfLeftItems the sum of all items that still haven't been investigated.
     * @param currentSolution the current solution being built.
     * @param solutions all solutions currently found.
     */
    private static void sumOfSubset(int[] numbers, int desiredSum, int sumOfAnalyzedItems, int currentItemIdx, int sumOfLeftItems, Boolean[] currentSolution, List<List<Integer>> solutions) {
        // generate left child
        currentSolution[currentItemIdx] = true;
        if (sumOfAnalyzedItems+numbers[currentItemIdx] == desiredSum) { // solution has been found
            // create a new list with the numbers in this solution and add it to the solutions list
            List<Integer> list = new LinkedList<>();
            for (int i=0; i<numbers.length; i++) {
                if (currentSolution[i] == null) break; // from now on numbers have not been investigated, so stop iterating
                if (currentSolution[i]) list.add(numbers[i]);
            }
            solutions.add(list);
        }
        else if (sumOfAnalyzedItems+numbers[currentItemIdx]+numbers[currentItemIdx+1] <= desiredSum) // bounding function (check expansion to the left)
            sumOfSubset(numbers, desiredSum, sumOfAnalyzedItems+numbers[currentItemIdx], currentItemIdx+1,
                        sumOfLeftItems-numbers[currentItemIdx], currentSolution, solutions);
        
        // generate right child
        if ((sumOfAnalyzedItems+sumOfLeftItems-numbers[currentItemIdx] >= desiredSum) && 
            (sumOfAnalyzedItems+numbers[currentItemIdx+1] <= desiredSum)) { // bounding function (check expansion to the right)
            currentSolution[currentItemIdx] = false;
            sumOfSubset(numbers, desiredSum, sumOfAnalyzedItems, currentItemIdx+1,
                        sumOfLeftItems-numbers[currentItemIdx], currentSolution, solutions);
        }
    }
}