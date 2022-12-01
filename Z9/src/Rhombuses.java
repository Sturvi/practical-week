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
        return (diogonal1 * diogonal2 ) / 2;
    }

    @Override
    public double perimeter() {
        return a * 4;
    }

    @Override
    double getLargeDiagonal() {
        double diogonal1 = 2 * a * Math.sin(Math.toRadians(alpha / 2));
        double diogonal2 = 2 * a * Math.sin(Math.toRadians(beta / 2));
        return Math.max(diogonal2, diogonal1);
    }

    @Override
    double getHeight() {
        return area() / a;
    }

    @Override
    String getColor() {
        return color;
    }
}
