package pairings;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PairingCostMatrixTest {

    @Test
    public void noMultiplier() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers1 = new HashMap<>();
        answers1.put(1, "d");
        answers1.put(2, "a");
        answers1.put(3, "c");

        Student student1 = new Student(1, answers1);
        student1.calculateScore(questions);


        Map<Integer, String> answers2 = new HashMap<>();
        answers2.put(1, "a");
        answers2.put(2, "a");
        answers2.put(3, "b");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);


        Map<Integer, Double> aCategoryScoreDifference = new HashMap<>();
        aCategoryScoreDifference.put(1, 0.0);
        aCategoryScoreDifference.put(2, 0.0);
        PairingCostMatrixArgs args = new PairingCostMatrixArgs(0, aCategoryScoreDifference, 0.0, 0.0);

        assertEquals(0.0, PairingCostMatrix.cost(questions, student1, student2, args), 0.0);
    }

    @Test
    public void totalScoreDifferenceOnly() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers1 = new HashMap<>();
        answers1.put(1, "d");
        answers1.put(2, "a");
        answers1.put(3, "c");

        Student student1 = new Student(1, answers1);
        student1.calculateScore(questions);


        Map<Integer, String> answers2 = new HashMap<>();
        answers2.put(1, "a");
        answers2.put(2, "a");
        answers2.put(3, "b");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);

        Map<Integer, Double> aCategoryScoreDifference = new HashMap<>();
        aCategoryScoreDifference.put(1, 0.0);
        aCategoryScoreDifference.put(2, 0.0);
        PairingCostMatrixArgs args = new PairingCostMatrixArgs(10000, aCategoryScoreDifference, 0.0, 0.0);

        assertEquals(-2500.0, PairingCostMatrix.cost(questions, student1, student2, args), 0.0);
    }

    @Test
    public void percentCorrectXorOnly() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers1 = new HashMap<>();
        answers1.put(1, "d");
        answers1.put(2, "a");
        answers1.put(3, "c");

        Student student1 = new Student(1, answers1);
        student1.calculateScore(questions);


        Map<Integer, String> answers2 = new HashMap<>();
        answers2.put(1, "a");
        answers2.put(2, "a");
        answers2.put(3, "b");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);

        Map<Integer, Double> aCategoryScoreDifference = new HashMap<>();
        aCategoryScoreDifference.put(1, 0.0);
        aCategoryScoreDifference.put(2, 0.0);
        PairingCostMatrixArgs args = new PairingCostMatrixArgs(0, aCategoryScoreDifference, 1000.0, 0.0);

        assertEquals(-250.0, PairingCostMatrix.cost(questions, student1, student2, args), 0.0);
    }

    @Test
    public void percentDifferentOnly() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers1 = new HashMap<>();
        answers1.put(1, "d");
        answers1.put(2, "a");
        answers1.put(3, "c");

        Student student1 = new Student(1, answers1);
        student1.calculateScore(questions);


        Map<Integer, String> answers2 = new HashMap<>();
        answers2.put(1, "a");
        answers2.put(2, "a");
        answers2.put(3, "b");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);

        Map<Integer, Double> aCategoryScoreDifference = new HashMap<>();
        aCategoryScoreDifference.put(1, 0.0);
        aCategoryScoreDifference.put(2, 0.0);
        PairingCostMatrixArgs args = new PairingCostMatrixArgs(0, aCategoryScoreDifference, 0.0, 100);

        assertEquals(-50.0, PairingCostMatrix.cost(questions, student1, student2, args), 0.0);
    }

    @Test
    public void totalScoreDifferentCategoryOnly() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers1 = new HashMap<>();
        answers1.put(1, "d");
        answers1.put(2, "a");
        answers1.put(3, "c");

        Student student1 = new Student(1, answers1);
        student1.calculateScore(questions);


        Map<Integer, String> answers2 = new HashMap<>();
        answers2.put(1, "a");
        answers2.put(2, "a");
        answers2.put(3, "b");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);

        Map<Integer, Double> aCategoryScoreDifference = new HashMap<>();
        aCategoryScoreDifference.put(1, 10.0);
        aCategoryScoreDifference.put(2, 0.0);
        PairingCostMatrixArgs args = new PairingCostMatrixArgs(0, aCategoryScoreDifference, 0.0, 0.0);

        assertEquals(-5.0, PairingCostMatrix.cost(questions, student1, student2, args), 0.0);
    }
}
