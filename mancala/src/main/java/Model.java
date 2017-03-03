import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

/**
 * Created by delta on 2/25/2017.
 */
public class Model {
    public static void main (String[] args) {
        // Class Declaration
        ClassModel model = new ClassModel();
        Clazz playerClass = model.createClazz("Player")
                .withAttribute("name", DataType.STRING);

        Clazz mancalaClass = model.createClazz("MancalaGame")
                .withMethod("makeBoard", DataType.VOID, new Parameter(DataType.INT).with("x"));

        Clazz houseClass = model.createClazz("House")
                .withAttribute("pebbles", DataType.INT)
                .withAttribute("isStore", DataType.BOOLEAN)
                .withMethod("takeOppositePebbles", DataType.VOID)
                .withMethod("redistributeCounterClockwise", DataType.VOID)
                .withMethod("redistributeCounterClockwiseRecurse", DataType.VOID, new Parameter(DataType.INT).with("x"), new Parameter(DataType.OBJECT)).with("house");

        // Unidirectional and Bidirectional Association Declaration
        houseClass.withBidirectional(mancalaClass, "game", Cardinality.ONE, "leftStore", Cardinality.ONE);
        houseClass.withBidirectional(mancalaClass, "game", Cardinality.ONE, "rightStore", Cardinality.ONE);
        houseClass.withBidirectional(houseClass, "houseAcross", Cardinality.ONE, "house", Cardinality.ONE);
        houseClass.withUniDirectional(houseClass, "houseNext", Cardinality.ONE);

        playerClass.withBidirectional(mancalaClass, "game", Cardinality.ONE, "players", Cardinality.MANY);
        mancalaClass.withBidirectional(houseClass, "leftHouses", Cardinality.MANY, "game", Cardinality.ONE);
        mancalaClass.withBidirectional(houseClass, "rightHouses", Cardinality.MANY, "game", Cardinality.ONE);


        
        //model.generate();

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
