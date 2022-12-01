public class Parallelogram extends Quadrangle implements Figure{
    double alpha;
    double beta;

    public Parallelogram(int a, int b, double alpha, double beta, String color) {
        super(a, b, color);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public double area() {
        return (Math.min(a, b) * Math.sin(Math.toRadians(Math.min(alpha, beta))) * Math.max(a, b)) / 2;
    }

    @Override
    public double perimeter() {
        return 2 * (a + b);
    }

    @Override
    double getLargeDiagonal() {
        double diag1 = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - (2 * a * b * Math.cos(Math.toRadians(alpha))));
        double diag2 = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - (2 * a * b * Math.cos(Math.toRadians(beta))));
        return diag1 > diag2 ? diag1 : diag2;
    }

    @Override
    double getHeight() {
        return Math.min(a, b) * Math.sin(Math.toRadians(Math.min(alpha, beta)));
    }

    @Override
    String getColor() {
        return color;
    }
}
