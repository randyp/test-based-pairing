package pairings;

import no.uib.cipr.matrix.DenseMatrix;

import java.util.List;

public class PairingCostMatrix {

    private DenseMatrix costMatrix;

    public PairingCostMatrix(Questions questions, List<Student> students, PairingCostMatrixArgs args) {
        this.costMatrix = new DenseMatrix(students.size(), students.size());
        for (int i = 0; i < students.size() - 1; i++) {
            Student student1 = students.get(i);
            for (int j = i + 1; j < students.size(); j++) {
                Student student2 = students.get(j);
                double cost = cost(questions, student1, student2, args);
                costMatrix.set(i, j, cost);
                costMatrix.set(j, i, cost);
            }
        }
    }

    protected static double cost(Questions questions, Student student1, Student student2, PairingCostMatrixArgs args) {
        double benefit = 0;

        double totalScoreDifference = Math.abs(student1.getOverallScore() - student2.getOverallScore());
        benefit+= args.aTotalScoreDifference * totalScoreDifference;

        for (Integer category : questions.getCategories()) {
            double categoryScoreDifference =
                    Math.abs(student1.getCategoryScore(category) - student2.getCategoryScore(category));
            double aCategoryScoreDifference = args.getACategoryScoreDifference(category);
            benefit += aCategoryScoreDifference * categoryScoreDifference;
        }

        int countCorrectXor = student1.countCorrectXorAnswers(questions, student2);
        double percentCorrentXOR = ((double) countCorrectXor) / questions.size();
        benefit+= args.aPercentCorrectXOR * percentCorrentXOR;

        int countDifferent = student1.countDifferentAnswers(questions, student2);
        double percentDifferent = ((double) countDifferent) / questions.size();
        benefit += args.aPercentDifferent * percentDifferent;

        return -benefit;
    }
}
