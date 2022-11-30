public class Main {
    public static void main(String[] args) {

        System.out.println(persistence((long) 677464));
        System.out.println(persistence2(677464));

    }

    public static int persistence(long n) {
        if (n < 10)
            return  0;
        int i = 1;
        long product = 1;
        while (n != 0) {
            int p = (int) n % 10;
            product = product * p;
            n = n / 10;
            if (n == 0 && product / 10 != 0) {
                n = product;
                product = 1;
                i++;
            }
        }
        return i;
    }


    public static int persistence2 (int num){
        if (num < 10 ) return 0;
        int countOperations = 0;
        String[] tmpStr = String.valueOf(num).split(""); // разделяем число на цифры и помещаем цифры в массив стринг
        int[] tmpInt = new int[tmpStr.length];
        for (int i = 0; i < tmpInt.length; i++) {
            tmpInt[i] = Integer.parseInt(tmpStr[i]);           // конвертируем массив стринг в массив инт
        }
        while (true) {
            int result = 1;
            for (int i = 0; i < tmpInt.length; i++) {
                result *= tmpInt[i];                            // перемножаем все элементы массива инт
            }
            countOperations++;
            if (result < 10 ) {
                return countOperations;
            } else {
                tmpStr = String.valueOf(result).split("");
                tmpInt = new int[tmpStr.length];
                for (int i = 0; i < tmpInt.length; i++) {
                    tmpInt[i] = Integer.parseInt(tmpStr[i]);
                }
            }
        }
    }
}