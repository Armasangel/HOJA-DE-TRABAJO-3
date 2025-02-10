import java.util.*;
import java.io.*;

public class Sorts {
    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // Método para generar números aleatorios y guardarlos en un archivo
    public static void generarArchivo(String nombreArchivo, int cantidad) throws IOException {
        Random rand = new Random();
        BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
        for (int i = 0; i < cantidad; i++) {
            writer.write(rand.nextInt(10000) + "\n");
        }
        writer.close();
    }

    // Método para leer números de un archivo
    public static int[] leerArchivo(String nombreArchivo) throws IOException {
        List<Integer> lista = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            lista.add(Integer.parseInt(linea));
        }
        reader.close();
        return lista.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) throws IOException {
        String archivo = "datos.txt";
        generarArchivo(archivo, 3000);
        int[] datos = leerArchivo(archivo);
        int[] copia = Arrays.copyOf(datos, datos.length);
        
        long start, end;
        
        start = System.nanoTime();
        insertionSort(copia);
        end = System.nanoTime();
        System.out.println("Insertion Sort: " + (end - start) + " ns");

        copia = Arrays.copyOf(datos, datos.length);
        start = System.nanoTime();
        mergeSort(copia);
        end = System.nanoTime();
        System.out.println("Merge Sort: " + (end - start) + " ns");

        copia = Arrays.copyOf(datos, datos.length);
        start = System.nanoTime();
        quickSort(copia, 0, copia.length - 1);
        end = System.nanoTime();
        System.out.println("Quick Sort: " + (end - start) + " ns");

        copia = Arrays.copyOf(datos, datos.length);
        start = System.nanoTime();
        heapSort(copia);
        end = System.nanoTime();
        System.out.println("Heap Sort: " + (end - start) + " ns");
    }
}
