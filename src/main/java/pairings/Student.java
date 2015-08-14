package pairings;

import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Student {




    private final long id;
    private final Map<Integer, String> answers;
    private double overallScore;
    private final Map<Integer, Double> categoryScores = new HashMap<>();

    public Student(int id, Map<Integer, String> answers) {
        this.id = id;
        this.answers = Collections.unmodifiableMap(answers);
    }

    public long getId() {
        return id;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public String getAnswer(int questionId) {
        return answers.get(questionId);
    }

    public void calculateScore(Questions questions) {
        overallScore = questions.calculateOverallScore(answers);
        categoryScores.clear();
        for (Integer category : questions.getCategories()) {
            double scoreForCategory = questions.calculateCategoryScore(category, answers);
            categoryScores.put(category, scoreForCategory);
        }
    }

    public double getCategoryScore(Integer category) {
        return categoryScores.get(category);
    }

    public int countCorrectXorAnswers(Questions questions, Student other) {
        int countCorrectXor = 0;
        for (Question question : questions) {
            int questionId = question.getId();
            boolean correct = question.getCorrectAnswer().equals(getAnswer(questionId));
            boolean otherCorrect = question.getCorrectAnswer().equals(other.getAnswer(questionId));
            if (correct ^ otherCorrect) {
                countCorrectXor++;
            }
        }
        return countCorrectXor;
    }

    public int countDifferentAnswers(Questions questions, Student other) {
        int countCorrectXor = 0;
        for (Question question : questions) {
            int questionId = question.getId();
            String answer = getAnswer(questionId);
            String otherAnswer = other.getAnswer(questionId);
            boolean answerBlank = StringUtils.isEmpty(answer);
            boolean otherAnswerBlank = StringUtils.isEmpty(otherAnswer);
            boolean sameAnswer = (answerBlank && otherAnswerBlank) || (!answerBlank && answer.equals(otherAnswer));
            if (!sameAnswer) {
                countCorrectXor++;
            }
        }
        return countCorrectXor;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", overallScore=" + overallScore + ", categoryScores=" + categoryScores + "}";
    }
}
