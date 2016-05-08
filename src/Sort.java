public class Sort
{
    public double[] selection(double[] list)
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

    public String[] selection(String[] list)
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

    public double[] insertion(double[] list)
    {
        double key;
        int i;

        for (int j = 1; j < list.length; j++) {
            key = list[j];

            for (i = j - 1; (i >= 0) && (list[i] > key); i--) {
                list[i + 1] = list[i];
            }
            list[i + 1] = key;
        }

        return list;
    }

    public String[] insertion(String[] list)
    {
        String key;
        int i;

        for (int j = 1; j < list.length; j++) {
            key = list[j];

            for (i = j - 1; (i >= 0) && (list[i].compareTo(key) > 0); i--) {
                list[i + 1] = list[i];
            }
            list[i + 1] = key;
        }

        return list;
    }

    public double[] quickSort(double[] list, int low, int high)
    {
        int middle = low + (high - low) / 2;
        double pivot = list[middle];
        int i = low, j = high;

        while (i <= j) {
            while (list[i] < pivot) {
                i++;
            }

            while (list[j] > pivot) {
                j--;
            }

            if (i <= j) {
                double temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(list, low, j);
        }

        if (high > i) {
            quickSort(list, i, high);
        }

        return list;
    }

    public String[] quickSort(String[] list, int low, int high)
    {
        int middle = low + (high - low) / 2;
        String pivot = list[middle];
        int i = low, j = high;

        while (i <= j) {

            while (list[i].compareTo(pivot) < 0) {
                i++;
            }

            while (list[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                String temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(list, low, j);
        }

        if (high > i) {
            quickSort(list, i, high);
        }

        return list;
    }

    public double[] shellSort(double[] list)
    {
        int h=1;

        while ( h <= list.length / 3) {
            h = 3 * h + 1;
        }

        while (h > 0) {

            for (int i = 0; i < list.length; i++) {
                double temp = list[i];
                int j;

                for (j = i; j > h-1 && list[j - h] >= temp; j = j - h) {
                    list[j] = list[j - h];
                }
                list[j] = temp;
            }
            h = (h - 1) / 3;
        }

        return list;
    }

    public String[] shellSort(String[] list)
    {
        int h = 1;
        while (h <= list.length / 3) {
            h = 3 * h + 1;
        }

        while (h > 0) {

            for (int i = 0; i < list.length; i++) {
                String temp = list[i];
                int j;

                for (j = i; j > h - 1 && (list[j - h].compareTo(temp) >= 0); j = j - h) {
                    list[j] = list[j - h];
                }
                list[j] = temp;
            }
            h = (h - 1) / 3;
        }

        return list;
    }
}