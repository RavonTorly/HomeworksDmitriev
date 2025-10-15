package org.example;

public class TringleArea {
    public static double calculateArea(double a, double h) {
        if (a <= 0 || h <= 0) {
            throw new IllegalArgumentException("Основание и высота должны быть положительными!");
        }
        return 0.5 * a * h;
    }
}
