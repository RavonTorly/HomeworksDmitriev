package Geometry;

public class Geometry {
    public static void main(String[] args) {
        FigureGeometry circle = new Circle(7.0, "зеленый", "черный");
        FigureGeometry rectangle = new Rectangle(5.0, 8.0, "фиолетовый", "желтый");
        FigureGeometry tringle = new Tringle(4.0, 6.0, 7.0, "красный", "оранжевый");

        System.out.println("Круг:");
        circle.resultInfo();
        System.out.println("\nПрямоугольник:");
        rectangle.resultInfo();
        System.out.println("\nТреугольник:");
        tringle.resultInfo();
    }

}

interface FigureGeometry {
    String getBodyColor();

    String getBorderColor();

    default double calcPerimeter() {
        return 0.0;
    }

    default double calcArea() {
        return 0.0;
    }

    default void resultInfo() {
        System.out.println("Периметр " + String.format("%.2f", calcPerimeter()) + ", Площадь: " + String.format("%.2f", calcArea()) + ", Цвет фона: " + getBodyColor() + ", Цвет границ: " + getBorderColor());
    }
}

class Circle implements FigureGeometry {
    private double R;
    private String fillColor;
    private String borderColor;

    public Circle(double R, String fillcolor, String borderColor) {
        this.R = R;
        this.fillColor = fillcolor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return 2 * Math.PI * R;
    }

    @Override
    public double calcArea() {
        return Math.PI * Math.pow(R, 2);
    }

    @Override
    public String getBodyColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

}

class Rectangle implements FigureGeometry {
    private double len;
    private double width;
    private String fillColor;
    private String borderColor;

    public Rectangle(double len, double width, String fillcolor, String borderColor) {
        this.len = len;
        this.width = width;
        this.fillColor = fillcolor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return 2 * (len + width);
    }

    @Override
    public double calcArea() {
        return len * width;
    }

    @Override
    public String getBodyColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

}

class Tringle implements FigureGeometry {
    private double a;
    private double b;
    private double c;
    private String fillColor;
    private String borderColor;

    public Tringle(double a, double b, double c, String fillcolor, String borderColor) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillcolor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return a + b + c;
    }

    @Override
    public double calcArea() {
        double p = calcPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String getBodyColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

}