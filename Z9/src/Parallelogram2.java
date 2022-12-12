public class Parallelogram2 extends Quadrangle implements Figure{
    double alpha;
    double beta;

    public Parallelogram2(int a, int b, double alpha, double beta, String color) {
        super(a, b, color);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public double area() {
        return Math.max(a,b) * getHeight();
    }

    @Override
    public double perimeter() {
        return 2 * a + 2 * b;
    }

    @Override
    double getLargeDiagonal() {
        double diag1 = Math.sqrt(a * a + b * b + 2 * a * b * Math.cos(Math.toRadians(alpha)));
        double diag2 = Math.sqrt(a * a + b * b  - 2 * a * b * Math.cos(Math.toRadians(beta)));
        return Math.max(diag1, diag2);
    }

    @Override
    double getHeight() {
        return Math.min(a * Math.sin(Math.toRadians(alpha)), b * Math.sin(Math.toRadians(beta)));
    }

    @Override
    String getColor() {
        return color;
    }

}
