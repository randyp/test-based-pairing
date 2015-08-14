package pairings;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StudentTest {

    @Test
    public void calculateScore() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers = new HashMap<>();
        answers.put(1, "a");
        answers.put(2, "b");
        answers.put(3, "a");

        Student student = new Student(1, answers);
        student.calculateScore(questions);

        assertEquals(0.5, student.getOverallScore(), 0.0);
        assertEquals(1.0, student.getCategoryScore(1), 0.0);
        assertEquals(0.0, student.getCategoryScore(2), 0.0);
    }

    @Test
    public void countXorCorrect() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers1 = new HashMap<>();
        answers1.put(1, "a");
        answers1.put(2, "b");
        answers1.put(3, "a");

        Student student1 = new Student(1, answers1);
        student1.calculateScore(questions);


        Map<Integer, String> answers2 = new HashMap<>();
        answers2.put(1, "a");
        answers2.put(2, "a");
        answers2.put(3, "b");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);

        assertEquals(2, student1.countCorrectXorAnswers(questions, student2));
        answers2.put(3, "a");
        assertEquals(1, student1.countCorrectXorAnswers(questions, student2));
    }

    @Test
    public void countDifferentAnswers() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1, q2, q3, q4));

        Map<Integer, String> answers1 = new HashMap<>();
        answers1.put(1, "d");
        answers1.put(2, "a");
        answers1.put(3, "c");
        answers1.put(4, "d");

        Student student1 = new Student(1, answers1);
        student1.calculateScore(questions);


        Map<Integer, String> answers2 = new HashMap<>();
        answers2.put(1, "a");
        answers2.put(2, "a");
        answers2.put(3, "b");
        answers2.put(4, "d");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);

        assertEquals(2, student1.countDifferentAnswers(questions, student2));
        answers2.put(3, "c");
        assertEquals(1, student1.countDifferentAnswers(questions, student2));
    }

    @Test
    public void countDifferentAnswersBlanksAreDifferentFromAnswers() throws Exception {
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
        answers2.put(4, "b");

        Student student2 = new Student(1, answers2);
        student2.calculateScore(questions);

        assertEquals(3, student1.countDifferentAnswers(questions, student2));
        answers2.put(3, "c");
        assertEquals(2, student1.countDifferentAnswers(questions, student2));
    }

    @Test
    public void countDifferentAnswersBlanksAreNotDifferentFromEachother() throws Exception {
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

        assertEquals(2, student1.countDifferentAnswers(questions, student2));
        answers2.put(3, "c");
        assertEquals(1, student1.countDifferentAnswers(questions, student2));
    }

}
