package pairings;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionsTest {

    @Test
    public void getQuestionsForCategory() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1,q2,q3,q4));

        List<Question> category1 = questions.getQuestionsForCategory(1);
        assertEquals(2, category1.size());
        assertTrue(category1.contains(q1));
        assertTrue(category1.contains(q3));

        List<Question> category2 = questions.getQuestionsForCategory(2);
        assertEquals(2, category2.size());
        assertTrue(category2.contains(q2));
        assertTrue(category2.contains(q4));
    }

    @Test
    public void overallScoreNoAnswers() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1,q2,q3,q4));

        Map<Integer, String> answers = new HashMap<>();
        assertEquals(0.0, questions.calculateOverallScore(answers), 0.0);
    }

    @Test
    public void overallScoreSomeCorrectSomeWrongAnswers() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1,q2,q3,q4));

        Map<Integer, String> answers = new HashMap<>();
        answers.put(1, "a");
        answers.put(2, "a");
        answers.put(3, "b");
        assertEquals(0.5, questions.calculateOverallScore(answers), 0.0);
    }

    @Test
    public void categoryScoreNoAnswers() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1,q2,q3,q4));

        Map<Integer, String> answers = new HashMap<>();
        assertEquals(0.0, questions.calculateCategoryScore(1, answers), 0.0);
    }

    @Test
    public void categoryScoreSomeCorrectSomeWrongAnswers() throws Exception {
        Question q1 = new Question(1, 1, "a");
        Question q2 = new Question(2, 2, "a");
        Question q3 = new Question(3, 1, "a");
        Question q4 = new Question(4, 2, "a");
        Questions questions = new Questions(Arrays.asList(q1,q2,q3,q4));

        Map<Integer, String> answers = new HashMap<>();
        answers.put(1, "a");
        answers.put(2, "a");
        answers.put(3, "b");
        assertEquals(0.5, questions.calculateCategoryScore(1, answers), 0.0);
    }

}
