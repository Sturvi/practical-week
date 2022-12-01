public class Main {
    public static void main(String[] args) {

        double diogonal1 = 2 * 50 * Math.sin(Math.toRadians(10 / 2));
        double diogonal2 = 2 * 50 * Math.sin(Math.toRadians(170 / 2));
        System.out.println("Dünen 1 " + diogonal1);
        System.out.println("Dünen 2 " + diogonal2);

         diogonal1 = 50 * Math.sqrt(2 - (2 * Math.cos(Math.toRadians(10))));
         diogonal2 = 50 * Math.sqrt(2 - (2 * Math.cos(Math.toRadians(170))));
        System.out.println("bugun 1 " + diogonal1);
        System.out.println("bugun 2 " + diogonal2);

    }
}