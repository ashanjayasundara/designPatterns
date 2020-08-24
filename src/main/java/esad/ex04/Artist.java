package esad.ex04;

public class Artist {
    ShapeBuilder shapeBuilder; 

    public Artist(ShapeBuilder shapeBuilder){
        this.shapeBuilder = shapeBuilder;
    }

    public Shape getShape(){
        return shapeBuilder.getShape();
    }

    public void constructShape(){
        shapeBuilder.buildShape();
        shapeBuilder.fillcolor();
    }
}