/* file: PQ.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PQ {
    private City[] array;
    private int size;

    public PQ(int capacity) {
        array = new City[capacity];
        size = 0;
    }

    boolean isEmpty() {
        if(size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    int size() {
        return size;
    }

    void insert(City x) {
        if((double)size() == array.length * 0.75) {
            resize();
        }
        array[size] = x;
        size++;   

        swim(size()-1);     
    }

    void resize() {
        City[] newArray = new City[2*array.length];
        for(int i = 0; i < size(); i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    City min() {
        if(isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        } else {
            return array[0];
        }

    }

    City getmin() {
        City minCity = min();
        remove(minCity.getID());
        return minCity;
    }

    void remove(int ID) {
        if(isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        } else {
            int index = -1;
            for(int i = 0; i < size(); i++) {
                if(array[i].getID() == ID) {
                    index = i;
                    break;
                }
            }
            if(index == -1) {
                throw new IllegalStateException("City not found");
            } else {
                swap(index, size()-1);
                array[size()-1] = null;
                size--;
                sink(index);
            }
        }
    }

    private void swim(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (array[index].compareTo(array[parentIndex]) < 0) {
                swap(index, parentIndex);
                break;
            } else {
                break;
            }
        }
    }

    private void sink(int index) {
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallestIndex = index;

            if (leftChildIndex < size() && array[leftChildIndex].compareTo(array[smallestIndex]) < 0) {
                smallestIndex = leftChildIndex;
            }

            if (rightChildIndex < size() && array[rightChildIndex].compareTo(array[smallestIndex]) < 0) {
                smallestIndex = rightChildIndex;
            }

            if (smallestIndex != index) {
                swap(index, smallestIndex);
                index = smallestIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        City temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }






    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Influenza_k <k> <file>");
            System.exit(1);
        } 

        int k = Integer.parseInt(args[0]);
        String file = args[1];

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        ArrayList<Integer> ID = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> population = new ArrayList<>();
        ArrayList<Integer> influenzaCases = new ArrayList<>(); 

        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(" ");
            ID.add(Integer.parseInt(data[0]));
            name.add(String.join(" ", Arrays.copyOfRange(data, 1, data.length - 2)));
            population.add(Integer.parseInt(data[2]));
            influenzaCases.add(Integer.parseInt(data[3]));
        }
        
        bufferedReader.close();

        City[] cities = new City[ID.size()];

        for (int j = 0; j < ID.size(); j++) {
            cities[j] = new City(ID.get(j), name.get(j), population.get(j), influenzaCases.get(j)); 
        }
 

        // ensures that k is not greater than the number of cities
        if (k > cities.length) { 
            System.out.println("Error!");
            System.exit(0);
        }

        System.out.println("The top " + k + " cities are:");
        for (int i = 0; i < k; i++) {
            System.out.println(cities[i].getName());
        }

    }

}