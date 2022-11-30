public class Parallelogram extends Quadrangle implements Figure{
    double alpha;
    double beta;

    public Parallelogram(int a, int b, double alpha, double beta, String color) {
        super(a, b, color);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public double getArea() {
        return (Math.min(a, b) * Math.sin(Math.min(alpha, beta)) * Math.max(a, b)) / 2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (a + b);
    }

    @Override
    double getLargeDiagonal() {
        double diog1 = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - (2 * a * b * Math.cos(alpha)));
        double diog2 = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - (2 * a * b * Math.cos(beta)));
        return diog1 > diog2 ? diog1 : diog2;
    }

    @Override
    double getHeight() {
        return Math.min(a, b) * Math.sin(Math.min(alpha, beta));
    }

    @Override
    String getColor() {
        return color;
    }
}