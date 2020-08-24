package esad.ex04;

public class BuilderDemo {
    public static void main(String[] args){

        ShapeBuilder rectangleBuilder = new RectangleBuilder(new Rectangle());
        Artist artist = new Artist(rectangleBuilder);
        artist.constructShape();

        ShapeBuilder circleBuilder = new CicleBuilder(new Circle());
        Artist artist2 = new Artist(circleBuilder);
        artist2.constructShape();
    }
}