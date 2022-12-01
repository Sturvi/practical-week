public class Square extends Quadrangle implements Figure {
    public Square(int a, String color) {
        super(a, a, color);
    }

    @Override
    double getLargeDiagonal() {
        return a * Math.sqrt(2);
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
    public double area() {
        return a*a;
    }

    @Override
    public double perimeter() {
        return a * 4;
    }
}
