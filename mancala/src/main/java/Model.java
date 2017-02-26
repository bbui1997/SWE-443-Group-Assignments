import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

/**
 * Created by delta on 2/25/2017.
 */
public class Model {
    public static void main (String[] args) {
        // Declare classes and their attributes
        ClassModel model = new ClassModel();

        Storyboard storyboard = new Storyboard();
        storyboard.add("");

        /*
        ClassModel model = new ClassModel("net.ulno.sa");

        Clazz universityClass = model.createClazz("University")
                .withAttribute("name", DataType.STRING);

        Clazz studentClass = model.createClazz("Student")
                .withAttribute("name", DataType.STRING)
                .withAttribute("id", DataType.STRING)
                .withAttribute("assignmentPoints", DataType.INT)
                .withAttribute("motivation", DataType.INT)
                .withAttribute("credits", DataType.INT);
        universityClass.withBidirectional(studentClass, "students", Cardinality.MANY, "almaMater", Cardinality.ONE);

        Clazz roomClass = model.createClazz("Room")
                .withAttribute("name", DataType.STRING)
                .withAttribute("topic", DataType.STRING)
                .withAttribute("credits", DataType.STRING);

        universityClass.withBidirectional(roomClass, "rooms", Cardinality.MANY, "university", Cardinality.ONE);
        roomClass.withBidirectional(roomClass, "doors", Cardinality.MANY, "doors", Cardinality.MANY);
        studentClass.withBidirectional(roomClass, "in ", Cardinality.ONE, "students", Cardinality.MANY);

        // Visualize
        Storyboard storyboard = new Storyboard();
        storyboard.add("This is just showing our class diagram");
        storyboard.addClassDiagram(model);
        storyboard.add("No methods are actually specified.");

        // Create small object diagram
        storyboard.add("Karli and Peter are outside");
        University studyRight = new University();
        Student karli = new Student().withName("Karli")
                .withId("123")
                .withAlmaMater(studyRight);
        Student peter = new Student()
                .withName("Peter")
                .withId("124")
                .withAlmaMater(studyRight);
        Room math = new Room ()
                .withName("math")
                .withCredits("5");
        Room algebra = new Room ()
                .withName("algebra")
                .withCredits("10");
        studyRight.withRooms(math, algebra);
        algebra.withDoors(math);
        storyboard.addObjectDiagram(studyRight, karli, peter, math, algebra);

        model.generate();
        storyboard.dumpHTML();
        */
    }
}
