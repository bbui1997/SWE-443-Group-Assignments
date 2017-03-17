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
    /**
     *
     * @see <a href='../../../../doc/Model.html'>Model.html</a>
     */
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

        // Bidirectional Association Declaration
        houseClass.withBidirectional(houseClass, "houseAcross", Cardinality.ONE, "house", Cardinality.ONE);
        houseClass.withBidirectional(houseClass, "houseNext", Cardinality.ONE, "housePrev", Cardinality.ONE);

        playerClass.withBidirectional(mancalaClass, "game", Cardinality.ONE, "players", Cardinality.MANY);
        mancalaClass.withBidirectional(houseClass, "p1Houses", Cardinality.MANY, "game", Cardinality.ONE);
        mancalaClass.withBidirectional(houseClass, "p2Houses", Cardinality.MANY, "game", Cardinality.ONE);
        mancalaClass.withBidirectional(houseClass, "p1Store", Cardinality.ONE, "game", Cardinality.ONE);
        mancalaClass.withBidirectional(houseClass, "p2Store", Cardinality.ONE, "game", Cardinality.ONE);

        model.generate();
/*
        // Visualize
        Storyboard storyboard = new Storyboard();
        storyboard.add("Mancala Class Diagram");
        storyboard.addClassDiagram(model);

        model.generate();
        storyboard.dumpHTML();
        */
    }
}
