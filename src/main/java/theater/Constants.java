package theater;

/**
 * Constants used in this program.
 */
public final class Constants {

    // ----- Volume credit constants -----
    public static final int BASE_VOLUME_CREDIT_THRESHOLD = 30;
    public static final int COMEDY_EXTRA_VOLUME_FACTOR = 5;

    // ----- Comedy pricing constants -----
    public static final int COMEDY_AMOUNT_PER_AUDIENCE = 300;
    public static final int COMEDY_AUDIENCE_THRESHOLD = 20;
    public static final int COMEDY_BASE_AMOUNT = 30000;
    public static final int COMEDY_OVER_BASE_CAPACITY_AMOUNT = 10000;
    public static final int COMEDY_OVER_BASE_CAPACITY_PER_PERSON = 500;

    // ----- Tragedy pricing constants -----
    public static final int TRAGEDY_AUDIENCE_THRESHOLD = 30;
    public static final int TRAGEDY_BASE_AMOUNT = 40000;
    public static final int TRAGEDY_EXTRA_AMOUNT_PER_PERSON = 1000;

    // ----- Currency formatting -----
    public static final int CENTS_PER_DOLLAR = 100;

    // ----- History constants (for future extensions) -----
    public static final int HISTORY_BASE_AMOUNT = 20000;
    public static final int HISTORY_OVER_BASE_CAPACITY_PER_PERSON = 1000;
    public static final int HISTORY_AUDIENCE_THRESHOLD = 20;
    public static final int HISTORY_VOLUME_CREDIT_THRESHOLD = 20;

    // ----- Pastoral constants (for future extensions) -----
    public static final int PASTORAL_BASE_AMOUNT = 40000;
    public static final int PASTORAL_OVER_BASE_CAPACITY_PER_PERSON = 2500;
    public static final int PASTORAL_AUDIENCE_THRESHOLD = 20;
    public static final int PASTORAL_VOLUME_CREDIT_THRESHOLD = 20;

    private Constants() {

    }
}
