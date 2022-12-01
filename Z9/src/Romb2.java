public class Romb2 extends Quadrangle implements Figure {
    double alpha;
    double beta;

    public Romb2 (int a, double alpha, double beta, String color) {
        super(a, a, color);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public double area() {
        return b * getHeight();
    }

    @Override
    public double perimeter() {
        return 2*a+2*b;
    }

    @Override
    double getLargeDiagonal() {
        double diogonal1 = a * Math.sqrt(2 - (2 * Math.cos(Math.toRadians(alpha))));
        double diogonal2 = a * Math.sqrt(2 - (2 * Math.cos(Math.toRadians(beta))));
        return Math.max(diogonal2, diogonal1);
    }

    @Override
    double getHeight() {
        return a*Math.sin(Math.toRadians(alpha));
    }

    @Override
    String getColor() {
        return color;
    }
}
