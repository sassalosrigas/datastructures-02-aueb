/* file: Influenza_k.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Influenza_k {

    public static float calculateDensity(int population, int InfluenzaCases) {
        float cases = InfluenzaCases / population;
        float density = cases * 50000;
        return density;
    }

    public static float[] quicksort(float[] density) {
        // this is bubble sort not quicksort
        boolean flag = true;
        float temp;
        do {
            flag = true;
            for (int i = 0; i < density.length - 2; i++) {
                if (density[i] > density[i + 1]) {

                    temp = density[i];
                    density[i] = density[i + 1];
                    density[i + 1] = temp;
                    flag = false;
                }

            }

        } while (flag == true);
        return density;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please give the .txt file name with the data:");
        String file = input.nextLine();

        System.out.println("Please give the parameter k:");
        int k = input.nextInt(); // prepei to k na einai mikrotero tou plhthous twn polewn sto txt arxeio

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int ID[];
        String name[];
        int population[];
        int InfluenzaCases[];
        float density[];
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(" ");
            ID[i] = Integer.parseInt(data[0]);
            name[i] = String.join(" ", Arrays.copyOfRange(data, 1, data.length - 2));
            population[i] = Integer.parseInt(data[2]);
            InfluenzaCases[i] = Integer.parseInt(data[3]);
            City city = new City(ID[i], name[i], population[i], InfluenzaCases[i]);
            density[i] = calculateDensity(population[i], InfluenzaCases[i]);
            i += 1;
        }
        float densitySorted[] = quicksort(density);

    }
}