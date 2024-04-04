package JogoCartas;

import java.util.Scanner;

public class JogoCartas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cartas = new int[7];

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Valores das cartas");
            System.out.println("2 - Método Bubblesort");
            System.out.println("3 - Método Selectionsort");
            System.out.println("4 - Método InserctionSort");
            System.out.println("5 - Método de Quicksort");
            System.out.println("6 - Método de Heapsort");
            System.out.println("7 - Método de Mergesort");
            System.out.println("8 - Comparar eficiência dos métodos");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    preencherCartas(scanner, cartas);
                    break;
                case 2:
                    ordenarEMostrarPassos("Bubblesort", bubbleSort(cartas.clone()));
                    break;
                case 3:
                    ordenarEMostrarPassos("Selectionsort", selectionSort(cartas.clone()));
                    break;
                case 4:
                    ordenarEMostrarPassos("InsertionSort", insertionSort(cartas.clone()));
                    break;
                case 5:
                    ordenarEMostrarPassos("Quicksort", quickSort(cartas.clone(), 0, cartas.length - 1));
                    break;
                case 6:
                    ordenarEMostrarPassos("Heapsort", heapSort(cartas.clone()));
                    break;
                case 7:
                    ordenarEMostrarPassos("Mergesort", mergeSort(cartas.clone()));
                    break;
                case 8:
                    compararEficiencia(cartas.clone());
                    break;
                case 9:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void preencherCartas(Scanner scanner, int[] cartas) {
        System.out.println("Informe os valores das cartas (de 1 a 13):");
        for (int i = 0; i < cartas.length; i++) {
            System.out.print("Carta " + (i + 1) + ": ");
            cartas[i] = scanner.nextInt();
        }
        System.out.println("Valores das cartas preenchidos.");
    }

    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public static int[] quickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int pi = partitionQuickSort(array, inicio, fim);
            quickSort(array, inicio, pi - 1);
            quickSort(array, pi + 1, fim);
        }
        return array;
    }

    private static int partitionQuickSort(int[] array, int inicio, int fim) {
        int pivot = array[fim];
        int i = (inicio - 1);
        for (int j = inicio; j < fim; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;
        return i + 1;
    }

    public static int[] heapSort(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
        return array;
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && array[l] > array[largest]) {
            largest = l;
        }
        if (r < n && array[r] > array[largest]) {
            largest = r;
        }
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);
        }
    }

    public static int[] mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    private static void mergeSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(array, inicio, meio);
            mergeSort(array, meio + 1, fim);
            merge(array, inicio, meio, fim);
        }
    }

    private static void merge(int[] array, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = array[inicio + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = array[meio + 1 + j];
        }

        int i = 0, j = 0;
        int k = inicio;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void exibirVetor(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void ordenarEMostrarPassos(String metodo, int[] ordenado) {
        System.out.println("\nMétodo de ordenação: " + metodo);
        System.out.println("Vetor original:");
        exibirVetor(ordenado);
        ordenacaoComPassos(ordenado, metodo);
        System.out.println("Vetor ordenado:");
        exibirVetor(ordenado);
    }

    public static void ordenacaoComPassos(int[] array, String metodo) {
        switch (metodo) {
            case "Bubblesort":
                bubbleSortComPassos(array);
                break;
            case "Selectionsort":
                selectionSortComPassos(array);
                break;
            case "InsertionSort":
                insertionSortComPassos(array);
                break;
            case "Quicksort":
                quickSortComPassos(array, 0, array.length - 1);
                break;
            case "Heapsort":
                heapSortComPassos(array);
                break;
            case "Mergesort":
                mergeSortComPassos(array);
                break;
            default:
                System.out.println("Método de ordenação inválido.");
        }
    }

    public static void bubbleSortComPassos(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    exibirVetor(array);
                }
            }
        }
    }

    public static void selectionSortComPassos(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            exibirVetor(array);
        }
    }

    public static void insertionSortComPassos(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
            exibirVetor(array);
        }
    }

    public static void quickSortComPassos(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int pi = partitionQuickSort(array, inicio, fim);
            quickSortComPassos(array, inicio, pi - 1);
            quickSortComPassos(array, pi + 1, fim);
        }
    }

    public static void heapSortComPassos(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
            exibirVetor(array);
        }
    }

    public static void mergeSortComPassos(int[] array) {
        mergeSortComPassos(array, 0, array.length - 1);
    }

    public static void mergeSortComPassos(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSortComPassos(array, inicio, meio);
            mergeSortComPassos(array, meio + 1, fim);
            mergeComPassos(array, inicio, meio, fim);
        }
    }

    public static void mergeComPassos(int[] array, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = array[inicio + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = array[meio + 1 + j];
        }

        int i = 0, j = 0;
        int k = inicio;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
            exibirVetor(array);
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            exibirVetor(array);
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            exibirVetor(array);
        }
    }

    public static void compararEficiencia(int[] cartas) {
        System.out.println("Comparando eficiência dos métodos de ordenação...");

        long startTime, endTime;
        int[] clone;

        clone = cartas.clone();
        startTime = System.nanoTime();
        bubbleSort(clone);
        endTime = System.nanoTime();
        long bubbleSortTime = endTime - startTime;

        clone = cartas.clone();
        startTime = System.nanoTime();
        selectionSort(clone);
        endTime = System.nanoTime();
        long selectionSortTime = endTime - startTime;

        clone = cartas.clone();
        startTime = System.nanoTime();
        insertionSort(clone);
        endTime = System.nanoTime();
        long insertionSortTime = endTime - startTime;

        clone = cartas.clone();
        startTime = System.nanoTime();
        quickSort(clone, 0, clone.length - 1);
        endTime = System.nanoTime();
        long quickSortTime = endTime - startTime;

        clone = cartas.clone();
        startTime = System.nanoTime();
        heapSort(clone);
        endTime = System.nanoTime();
        long heapSortTime = endTime - startTime;

        clone = cartas.clone();
        startTime = System.nanoTime();
        mergeSort(clone);
        endTime = System.nanoTime();
        long mergeSortTime = endTime - startTime;

        System.out.println("Tempo gasto pelo Bubblesort: " + bubbleSortTime + " nanosegundos");
        System.out.println("Tempo gasto pelo Selectionsort: " + selectionSortTime + " nanosegundos");
        System.out.println("Tempo gasto pelo InsertionSort: " + insertionSortTime + " nanosegundos");
        System.out.println("Tempo gasto pelo Quicksort: " + quickSortTime + " nanosegundos");
        System.out.println("Tempo gasto pelo Heapsort: " + heapSortTime + " nanosegundos");
        System.out.println("Tempo gasto pelo Mergesort: " + mergeSortTime + " nanosegundos");

        long[] tempos = {bubbleSortTime, selectionSortTime, insertionSortTime, quickSortTime, heapSortTime, mergeSortTime};
        String[] metodos = {"Bubblesort", "Selectionsort", "InsertionSort", "Quicksort", "Heapsort", "Mergesort"};

        long menorTempo = Long.MAX_VALUE;
        String metodoMaisEficiente = "";

        for (int i = 0; i < tempos.length; i++) {
            if (tempos[i] < menorTempo) {
                menorTempo = tempos[i];
                metodoMaisEficiente = metodos[i];
            }
        }

        System.out.println("\nO método mais eficiente para este conjunto de cartas é o " + metodoMaisEficiente + ".");
    }
}
