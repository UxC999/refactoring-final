package theater;

/**
 * Represents a play with a name and a type (e.g., tragedy, comedy).
 *
 * @null This class does not accept null parameters.
 */

public class Play {

    private final String name;
    private final String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
