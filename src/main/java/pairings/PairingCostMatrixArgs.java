package pairings;

import org.apache.commons.cli.CommandLine;

import java.util.HashMap;
import java.util.Map;

public class PairingCostMatrixArgs {

    @SuppressWarnings("unused")
    public static PairingCostMatrixArgs fromCommandLine(CommandLine line) {
        double aTotalScoreDifference = parseDoubleArg(line, "atotalscoredifference");
        Map<Integer, Double> aCategoryScoreDifference = parseACategoryScoreDifference(line);
        double aPercentCorrectXOR = parseDoubleArg(line, "apercentcorrectxor");
        double aPercentDifferent = parseDoubleArg(line, "apercentdifferent");
        return new PairingCostMatrixArgs(aTotalScoreDifference, aCategoryScoreDifference,
                aPercentCorrectXOR, aPercentDifferent);
    }

    private static Map<Integer, Double> parseACategoryScoreDifference(CommandLine line) {
        String aCategoryScoreDifferenceString = line.getOptionValue("acategoryscoredifference");
        Map<Integer, Double> aCategoryScoreDifference = new HashMap<Integer, Double>();
        if (aCategoryScoreDifferenceString != null) {
            String[] categoryMultipliers = aCategoryScoreDifferenceString.split(",");
            for (String categoryMultiplier : categoryMultipliers) {
                String[] categoryMulitplierPair = categoryMultiplier.split(":");
                if (categoryMulitplierPair.length != 2) {
                    throw new RuntimeException("Could not parse category multipliers: " + categoryMultiplier);
                }
                int categoryId;
                try {
                    categoryId = Integer.parseInt(categoryMulitplierPair[0]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Could parse category id from: " + categoryMulitplierPair[0], e);
                }
                double multiplier;
                try {
                    multiplier = Double.parseDouble(categoryMulitplierPair[1]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Could not parse multiplier id from: " + categoryMulitplierPair[1], e);
                }
                aCategoryScoreDifference.put(categoryId, multiplier);
            }
        }
        return aCategoryScoreDifference;
    }

    private static double parseDoubleArg(CommandLine line, String argName) {
        String argValue = line.getOptionValue(argName);
        double aTotalScoreDifference;
        try {
            aTotalScoreDifference = argValue == null ? 1.0 : Double.parseDouble(argValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("Could not parse %s: %s", argName, argValue));
        }
        return aTotalScoreDifference;
    }

    public final double aTotalScoreDifference;
    public final Map<Integer, Double> aCategoryScoreDifference;
    public final double aPercentCorrectXOR;
    public final double aPercentDifferent;

    public PairingCostMatrixArgs(double aTotalScoreDifference, Map<Integer, Double> aCategoryScoreDifference,
                                 double aPercentCorrectXOR, double aPercentDifferent) {
        this.aTotalScoreDifference = aTotalScoreDifference;
        this.aCategoryScoreDifference = aCategoryScoreDifference;
        this.aPercentCorrectXOR = aPercentCorrectXOR;
        this.aPercentDifferent = aPercentDifferent;
    }

    public double getACategoryScoreDifference(Integer category) {
        return aCategoryScoreDifference.containsKey(category)
                ? aCategoryScoreDifference.get(category)
                : 1.0;
    }
}
