package esad.ex02;

/**
 * @author ashan on 2020-08-16
 */

interface Shape {
    public void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside the Circle: draw() method");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside the Rectangle: draw() method");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside the Square: draw() method");
    }
}


interface Color {
    public void fill();
}

class ColorRed implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red: fill() method");
    }
}

class ColorGreen implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green: fill() method");
    }
}

class ColorBlue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue: fill() method");
    }
}


abstract class AbstractFactory {
    abstract Shape getShape(String shape);

    abstract Color getColor(String color);
}

class ShapeFactory extends AbstractFactory {
    @Override
    Shape getShape(String shape) {
        switch (shape) {
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            case "SQUARE":
                return new Square();
            default:
                return new Circle();
        }
    }

    @Override
    Color getColor(String color) {
        return null;
    }
}

class ColorFactory extends AbstractFactory {

    @Override
    Shape getShape(String shape) {
        return null;
    }

    @Override
    Color getColor(String color) {
        switch (color) {
            case "RED":
                return new ColorRed();
            case "GREEN":
                return new ColorGreen();
            case "BLUE":
                return new ColorBlue();
            default:
                return new ColorRed();
        }
    }
}

class FactoryProducer {
    public static AbstractFactory getFactory(String factory) {
        if (factory.equals("SHAPE"))
            return new ShapeFactory();
        else if (factory.equals("COLOR"))
            return new ColorFactory();
        return null;
    }
}

public class AbstractFactoryPatternDemo {
    private static final String SHAPE = "SHAPE";
    private static final String RECTANGLE = "RECTANGLE";
    private static final String CIRCLE = "CIRCLE";
    private static final String SQUARE = "SQUARE";

    private static final String COLOR = "COLOR";
    private static final String RED = "RED";
    private static final String GREEN = "GREEN";
    private static final String BLUE = "BLUE";

    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory(SHAPE);
        Shape shape = shapeFactory.getShape(CIRCLE);
        shape.draw();

        FactoryProducer.getFactory(SHAPE).getShape(RECTANGLE).draw();
        FactoryProducer.getFactory(SHAPE).getShape(SQUARE).draw();

        FactoryProducer.getFactory(COLOR).getColor(RED).fill();
        FactoryProducer.getFactory(COLOR).getColor(GREEN).fill();
        FactoryProducer.getFactory(COLOR).getColor(BLUE).fill();
    }
}




