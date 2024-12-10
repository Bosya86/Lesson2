package org.example.shapes;


public interface ColoredShape extends Shape {
    default String getFillColor() {
        return "White";
    }

    default String getBorderColor() {
        return "Black";
    }
}

