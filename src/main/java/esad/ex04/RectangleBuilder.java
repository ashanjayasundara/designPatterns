package esad.ex04;

public class RectangleBuilder implements ShapeBuilder{
    Shape rectangleShape;
    private static final String SHAPE = "SHAPE";
    private static final String RECTANGLE = "RECTANGLE";
    private static final String COLOR = "COLOR";
    private static final String GREEN = "GREEN";

    public RectangleBuilder(Shape rectangleShape){
        this.rectangleShape = rectangleShape;
    }

    @Override 
    public void buildShape(){
        rectangleShape = FactoryProducer.getFactory(SHAPE).getShape(RECTANGLE);
        rectangleShape.draw();
    }

    @Override
    public void fillcolor(){
        FactoryProducer.getFactory(COLOR).getColor(GREEN).fill();
    }

    @Override
    public Shape getShape(){
            return this.rectangleShape;
    }
}