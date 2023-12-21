/* file: Influenza_k.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Influenza_k {

    public static float calculateDensity(int population, int InfluenzaCases) {
        float cases = (float) InfluenzaCases / population;
        float density = cases * 50000;
        return density;
    }

    // Methodos pou kanei swap 2 stoixeia tou Array kata to quicksort
    public static void swap(float array[], int i, int j)
    {
        float temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static int partition(float array[], int p, int r) {
        
        int i = p -1, j = r; 
        float v = array[r];
            
        while (i < j) {
            while (array[++i] < v)
                ;
            while (v < array[--j])
                if (j == p)
                    break;

            if (i >= j)
                break;

            swap(array, i, j);
        }

        swap(array, i, r);
        return i;

    }

    public static void quicksort(float array[], int p, int r) {

    { if (r > p) {
        int i = partition(array, p, r);
        // splits the array and puts the pivot element in position a[i]
        quicksort(array, p, i-1);
        quicksort(array, i+1, r);
            }
       }
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please give the .txt file path with the data:");
        String file = input.nextLine();

        System.out.println("Please give the parameter k:");
        int k = input.nextInt(); 

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int[] ID[];
        String[] name[];
        int[] population[];
        int[] InfluenzaCases[];
        float[] density[];

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
        
        bufferedReader.close();

        // prepei to k na einai mikrotero tou plhthous twn polewn sto txt arxeio:
        if (k > density.length) { 
            System.out.println("Error!");
            System.exit(0);
        }
        
        float[] densitySorted = Arrays.copyOf(density, density.length);
        quicksort(densitySorted, 0, density.length - 1);

    }

}