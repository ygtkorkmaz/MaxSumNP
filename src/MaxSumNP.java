import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxSumNP {

    public static ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<ArrayList<Integer>> org = new ArrayList<ArrayList<Integer>>();


    private static int findMaxSum(String fileName) {
        try {

            InputStream inputStream = new FileInputStream(fileName);

            Scanner scanner = new Scanner(inputStream);


            while (scanner.hasNextLine()) { //storing the numbers in the txt file in an dynamic arraylist
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");
                ArrayList<Integer> aux1 = new ArrayList<Integer>();
                ArrayList<Integer> aux2 = new ArrayList<Integer>();
                for (int i = 0; i < numbers.length; ++i) {
                    aux1.add(Integer.parseInt(numbers[i]));
                    aux2.add(Integer.parseInt(numbers[i]));
                }
                array.add(aux1);
                org.add(aux2);

            }
        } catch (
                FileNotFoundException e) {
            System.out.println("File not found!");
            java.lang.System.exit(1);
        } catch (
                ArrayIndexOutOfBoundsException e) {
            System.out.println("Please check your input file or row and column sizes!");
            java.lang.System.exit(1);
        }

        int[] advancement = new int[array.size()];
        if (isPrime(org.get(0).get(0))) { //checking if the first number is prime or not
            return 0;
        }
        for (int i = array.size() - 2; i >= 0; i--) { //applying bottom-up dynamic programming while checking prime conditions
            for (int j = 0; j <= array.get(i).size() - 1; j++) {

                if (array.get(i + 1).get(j) >= array.get(i + 1).get(j + 1)) {
                    if (!isPrime(org.get(i + 1).get(j))) {
                        array.get(i).set(j, array.get(i).get(j) + array.get(i + 1).get(j));
                    } else if (!isPrime(org.get(i + 1).get(j + 1))) {
                        array.get(i).set(j, array.get(i).get(j) + array.get(i + 1).get(j + 1));
                    } else { //if both numbers are prime, changing value with MIN_VALUE to prevent addition
                        array.get(i).set(j, Integer.MIN_VALUE);
                    }
                } else {
                    if (!isPrime(org.get(i + 1).get(j + 1))) {
                        array.get(i).set(j, array.get(i).get(j) + array.get(i + 1).get(j + 1));
                    } else if (!isPrime(org.get(i + 1).get(j))) {
                        array.get(i).set(j, array.get(i).get(j) + array.get(i + 1).get(j));
                    } else {
                        array.get(i).set(j, Integer.MIN_VALUE);
                    }
                }

            }
        }

        return array.get(0).get(0);

    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; ++i) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        System.out.printf("\nValue of Maximum Sum \n%d \n", findMaxSum("test.txt"));


    }
}
