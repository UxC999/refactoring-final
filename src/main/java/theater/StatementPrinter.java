package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Generates a statement for a given invoice of performances.
 *
 * @null This class does not accept null parameters.
 */
public class StatementPrinter {

    private final Invoice invoice;
    private final Map<String, Play> plays;

    /**
     * Construct a StatementPrinter for the given invoice and plays.
     *
     * @param invoice the invoice to print (must not be null)
     * @param plays   mapping from play ID to play information (must not be null)
     */
    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Map<String, Play> getPlays() {
        return plays;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     *
     * @return the formatted statement
     * @throws RuntimeException if a play type is unknown
     */
    public String statement() {
        final StringBuilder result =
                new StringBuilder("Statement for " + invoice.getCustomer() + System.lineSeparator());
        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        int totalAmount = 0;
        int volumeCredits = 0;

        for (Performance performance : invoice.getPerformances()) {
            final int thisAmount = getAmount(performance);

            // add volume credits
            volumeCredits += Math.max(
                    performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);

            if ("comedy".equals(getPlay(performance).getType())) {
                volumeCredits += performance.getAudience()
                        / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
            }

            result.append(String.format(
                    "  %s: %s (%s seats)%n",
                    getPlay(performance).getName(),
                    frmt.format(thisAmount / (double) Constants.CENTS_PER_DOLLAR),
                    performance.getAudience()
            ));

            totalAmount += thisAmount;
        }

        result.append(String.format(
                "Amount owed is %s%n",
                frmt.format(totalAmount / (double) Constants.CENTS_PER_DOLLAR)
        ));
        result.append(String.format(
                "You earned %s credits%n", volumeCredits
        ));

        return result.toString();
    }

    /**
     * Returns the Play corresponding to this performance.
     *
     * @param performance the performance
     * @return the play associated with the performance
     */
    private Play getPlay(Performance performance) {
        return plays.get(performance.getPlayID());
    }

    /**
     * Computes the amount owed for a single performance.
     *
     * @param performance the performance
     * @return the amount owed in cents
     * @throws RuntimeException if the play type is unknown
     */
    private int getAmount(Performance performance) {
        int result;

        switch (getPlay(performance).getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_EXTRA_AMOUNT_PER_PERSON
                            * (performance.getAudience()
                            - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;

            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience()
                            - Constants.COMEDY_AUDIENCE_THRESHOLD);
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                break;

            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", getPlay(performance).getType()));
        }

        return result;
    }
}
