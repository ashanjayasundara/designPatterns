package esad.ex04;

public class CicleBuilder implements ShapeBuilder {
    Shape circleShape;
    private static final String SHAPE = "SHAPE";
    private static final String CIRCLE = "CIRCLE";
    private static final String COLOR = "COLOR";
    private static final String BLUE = "BLUE";

    public CicleBuilder(Shape circleShape){
        this.circleShape = circleShape;
    }

    @Override 
    public void buildShape(){
        circleShape = FactoryProducer.getFactory(SHAPE).getShape(CIRCLE);
        circleShape.draw();
    }

    @Override
    public void fillcolor(){
        FactoryProducer.getFactory(COLOR).getColor(BLUE).fill();
    }

    @Override
    public Shape getShape(){
        return this.circleShape;
    }
}