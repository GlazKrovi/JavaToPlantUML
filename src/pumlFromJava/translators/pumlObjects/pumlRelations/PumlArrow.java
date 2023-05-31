package pumlFromJava.translators.pumlObjects.pumlRelations;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and gives the arrows corresponding to each possible relationship in plantuml
 */
public class PumlArrow {

    private final PumlArrowLook type;
    private final String arrow;

    public PumlArrow(PumlArrowLook type) {
        // Different arrows' look
        Map<PumlArrowLook, String> looks = new HashMap<>() {
            {
                put(PumlArrowLook.DOTTED, "..");
                put(PumlArrowLook.SOLID, "--");
                put(PumlArrowLook.HEADFULL_SOLID, "<--");
                put(PumlArrowLook.HEADFULL_DOTTED, "<..");
                put(PumlArrowLook.HEADSOCKET_SOLID, "<|--");
                put(PumlArrowLook.HEADSOCKET_DOTTED, "<|..");
                put(PumlArrowLook.DIAMOND_EMPTY, "<--o");
                put(PumlArrowLook.DIAMOND_FULL, "<--*");
            }
        };
        // Stores
        if (looks.containsKey(type)) {
            this.type = type;
            this.arrow = looks.get(type);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public PumlArrowLook getType() {
        return type;
    }

    /**
     * Translate the arrow of the type specified
     *
     * @return String like '<--' or '<|--' or '<..' etc.
     */
    public String getArrow() {
        return arrow;
    }
}
