public class Square extends Quadrangle implements Figure {
    public Square(int a, String color) {
        super(a, a, color);
    }

    @Override
    double getLargeDiagonal() {
        return a * Math.sqrt(a);
    }

    @Override
    double getHeight() {
        return a;
    }

    @Override
    String getColor() {
        return color;
    }

    @Override
    public double getArea() {
        return a*a;
    }

    @Override
    public double getPerimeter() {
        return a * 4;
    }
}
