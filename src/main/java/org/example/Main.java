package org.example;

import org.example.shapes.Circle;
import org.example.shapes.Rectangle;
import org.example.shapes.Triangle;
import org.example.shapes.ColoredShape;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0);
        circle.setFillColor("Red");
        circle.setBorderColor("Blue");

        Rectangle rectangle = new Rectangle(6.0, 8.0);
        rectangle.setFillColor("Green");
        rectangle.setBorderColor("Yellow");

        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        triangle.setFillColor("Orange");
        triangle.setBorderColor("Purple");

        printShapeDetails(circle);
        printShapeDetails(rectangle);
        printShapeDetails(triangle);
    }

    private static void printShapeDetails(ColoredShape shape) {
        System.out.println("Фигура: " + shape.getClass().getSimpleName());
        System.out.println("Периметр: " + shape.calculatePerimeter());
        System.out.println("Площадь: " + shape.calculateArea());
        System.out.println("Цвет заливки: " + shape.getFillColor());
        System.out.println("Цвет границы: " + shape.getBorderColor());
        System.out.println();
    }
}
