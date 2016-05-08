public class Sort
{
    public double[] selecao(double[] list)
    {
        double smaller;
        int smallerIndex;

        for (int i = 0; i < list.length - 1; i++) {
            smaller = list[i];
            smallerIndex = i;

            for (int j = i + 1; j < list.length; j++) {

                if (list[j] < smaller) {
                    smaller = list[j];
                    smallerIndex = j;
                }
            }
            list[smallerIndex] = list[i];
            list[i] = smaller;
        }

        return list;
    }

    public String[] selecao(String[] list)
    {
        String smaller;
        int smallerIndex;

        for (int i = 0; i < list.length - 1; i++) {
            smaller = list[i];
            smallerIndex = i;

            for (int j = i + 1; j < list.length; j++){
                if (list[j].compareTo(smaller) < 0){
                    smaller = list[j];
                    smallerIndex = j;
                }
            }

            list[smallerIndex] = list[i];
            list[i] = smaller;
        }

        return list;
    }

    public double[] bubble(double[] list)
    {
        int j;
        boolean flag = true;
        double temp;

        while (flag) {
            flag = false;
            for (j = 0; j < list.length - 1; j++) {
                if (list[j] < list[j + 1]) {
                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    flag = true;
                }
            }
        }

        return list;
    }

    public String[] bubble(String[] vetor)
    {
        int j;
        boolean flag = true;
        String temp;

        while (flag) {
            flag = false;
            for(j = 0; j < vetor.length - 1; j++) {
                if (vetor[j].compareTo(vetor[j + 1]) < 0) {
                    temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    flag = true;
                }
            }
        }

        return vetor;
    }

    public double[] insercao(double[] vetor)
    {
        int j;
        double key;
        int i;

        for (j = 1; j < vetor.length; j++) {
            key = vetor[j];

            for (i = j - 1; (i >= 0) && (vetor[i] > key); i--) {
                vetor[i + 1] = vetor[i];
            }
            vetor[i + 1] = key;
        }

        return vetor;
    }

    public String[] insercao(String[] vetor)
    {
        int j;
        String key;
        int i;

        for (j = 1; j < vetor.length; j++) {
            key = vetor[j];

            for (i = j - 1; (i >= 0) && (vetor[i].compareTo(key) > 0); i--) {
                vetor[i + 1] = vetor[i];
            }
            vetor[i + 1] = key;
        }

        return vetor;
    }

    public double[] quickSort(double[] arr, int low, int high) {
        double[] empty = new double[0];

        if (arr == null || arr.length == 0)
            return empty;

        if (low >= high)
            return empty;

        // pick the pivot
        int middle = low + (high - low) / 2;
        double pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);

        return arr;
    }

    public String[] quickSort(String[] arr, int low, int high) {
        String[] empty = new String[0];

        if (arr == null || arr.length == 0)
            return empty;

        if (low >= high)
            return empty;

        // pick the pivot
        int middle = low + (high - low) / 2;
        String pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i].compareTo(pivot) < 0) {
                i++;
            }

            while (arr[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);

        return arr;
    }

    public double[] shellSort(double[] arr)
    {
        int h=1;
        while(h<= arr.length/3){
            h = 3*h + 1;   //h is equal to highest sequence of h<=length/3 (1,4,13,40...)
        }

        //next section
        while(h>0){    //for array of length 10, h=4

            //similar to insertion sort below
            for(int i=0; i<arr.length; i++){

                double temp = arr[i];
                int j;

                for(j=i; j>h-1 && arr[j-h] >= temp; j=j-h){
                    arr[j] = arr[j-h];
                }
                arr[j] = temp;
            }
            h = (h-1)/3;
        }

        return arr;
    }

    public String[] shellSort(String[] arr)
    {
        int h=1;
        while (h <= arr.length / 3) {
            h = 3*h + 1;   //h is equal to highest sequence of h<=length/3 (1,4,13,40...)
        }

        //next section
        while (h > 0) {    //for array of length 10, h=4

            //similar to insertion sort below
            for (int i = 0; i < arr.length; i++) {

                String temp = arr[i];
                int j;

                for (j = i; j > h - 1 && (arr[j - h].compareTo(temp) >= 0); j = j - h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = temp;
            }
            h = (h - 1) / 3;
        }

        return arr;
    }
}