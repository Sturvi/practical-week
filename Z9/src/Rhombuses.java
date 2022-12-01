public class Rhombuses extends Quadrangle implements Figure {
    double alpha;
    double beta;

    public Rhombuses(int a, double alpha, double beta, String color) {
        super(a, a, color);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public double area() {
        double diogonal1 = 2 * a * Math.sin(Math.toRadians(alpha / 2));
        double diogonal2 = 2 * a * Math.sin (Math.toRadians(beta / 2));
        return round((diogonal1 * diogonal2 ) / 2, 4) ;
    }

    @Override
    public double perimeter() {
        return a * 4;
    }

    @Override
    double getLargeDiagonal() {
        double diogonal1 = 2 * a * Math.sin(Math.toRadians(alpha / 2));
        double diogonal2 = 2 * a * Math.sin(Math.toRadians(beta / 2));
        return round(Math.max(diogonal2, diogonal1), 4) ;
    }

    @Override
    double getHeight() {
        return round(area() / a, 5) ;
    }

    @Override
    String getColor() {
        return color;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

