public class Rhombuses extends Quadrangle implements Figure {
    double alpha;
    double beta;

    public Rhombuses(int a, double alpha, double beta, String color) {
        super(a, a, color);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public double getArea() {
        double diogonal1 = 2 * a * Math.sin(alpha / 2);
        double diogonal2 = 2 * a * Math.sin(beta / 2);
        return (diogonal1 * diogonal2 ) / 2;
    }

    @Override
    public double getPerimeter() {
        return a * 4;
    }

    @Override
    double getLargeDiagonal() {
        double diogonal1 = 2 * a * Math.sin(alpha / 2);
        double diogonal2 = 2 * a * Math.sin(beta / 2);
        return diogonal1 > diogonal2 ? diogonal1 : diogonal2;
    }

    @Override
    double getHeight() {
        double diogonal1 = 2 * a * Math.sin(alpha / 2);
        double diogonal2 = 2 * a * Math.sin(beta / 2);
        double s = (diogonal1 * diogonal2 ) / 2;
        return s / a;
    }

    @Override
    String getColor() {
        return color;
    }
}
