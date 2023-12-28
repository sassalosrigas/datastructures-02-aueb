/* file: DynamicInfluenza_k_withPQ.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DynamicInfluenza_k_withPQ {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java DynamicInfluenza_k_withPQ <k> <file>");
            System.exit(1);
        } 

        int k = Integer.parseInt(args[0]);
        String file = args[1];

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Create a priority queue and insert the first k cities
        PQ pq = new PQ(k);
        for (int i = 0; i < k; i++) {
            String line = br.readLine();

            String[] data = line.split(" ");
            int id = Integer.parseInt(data[0]);
            String name = String.join(" ", Arrays.copyOfRange(data, 1, data.length - 2));
            int population = Integer.parseInt(data[2]);
            int influenzaCases = Integer.parseInt(data[3]);

            City city = new City(id, name, population, influenzaCases);
            pq.insert(city);
        }

        // For each remaining city, compare it with the minimum city in the
        // priority queue. If it is closer to the query point, remove the
        // minimum city and insert the new city.
        String line;
        while ((line = br.readLine()) != null) {

            String[] data = line.split(" ");
            int id = Integer.parseInt(data[0]);
            String name = String.join(" ", Arrays.copyOfRange(data, 1, data.length - 2));
            int population = Integer.parseInt(data[2]);
            int influenzaCases = Integer.parseInt(data[3]);

            City city = new City(id, name, population, influenzaCases);
            City min = pq.min();
            if (city.compareTo(min) == -1) {
                pq.remove(min.getID());
                pq.insert(city);
            }
        }

        // Print the k cities in the priority queue
        System.out.println("The top " + k + " cities are:");
        while (!pq.isEmpty()) {
            pq.getmin();
        }
    }
}