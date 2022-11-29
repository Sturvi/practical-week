
public class Vector2D extends Vector {

    public Vector2D(int x1, int y1, int x2, int y2) {

        super(x1, y1, 0, x2, y2, 0);
    }

    @Override
    public double getLength() {

        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    @Override
    public String getVectorCords() {
        return (x2 - x1) + " " + (y2 - y1);
    }

    public double getScalarProduct(Vector2D vector) {
        return (Integer.parseInt(getVectorCords().split(" ")[0]) *
                Integer.parseInt(vector.getVectorCords().split(" ")[0])) +
                (Integer.parseInt(getVectorCords().split(" ")[1]) *
                        Integer.parseInt(vector.getVectorCords().split(" ")[1]));
    }

    public double getAngle(Vector2D vector) {
        if (getLength() != 0 && vector.getLength() != 0) {
            return getScalarProduct(vector) / (getLength() * vector.getLength());
        } else return -2.0;
    }

}
