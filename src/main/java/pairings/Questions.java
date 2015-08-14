package pairings;

import java.util.*;

public class Questions implements Iterable<Question> {

    private final List<Question> questions;
    private final Map<Integer, Question> idToQuestion = new HashMap<>();
    private final Map<Integer, List<Question>> categoryToQuestions = new HashMap<>();

    public Questions(List<Question> questions) {
        this.questions = questions;
        for (Question question : questions) {
            idToQuestion.put(question.getId(), question);
            List<Question> domainQuestions = categoryToQuestions.get(question.getCategoryId());
            if (domainQuestions == null) {
                domainQuestions = new ArrayList<>();
                categoryToQuestions.put(question.getCategoryId(), domainQuestions);
            }
            domainQuestions.add(question);
        }
        for (Map.Entry<Integer, List<Question>> entry : categoryToQuestions.entrySet()) {
            entry.setValue(Collections.unmodifiableList(entry.getValue()));
        }
    }

    public List<Question> getQuestionsForCategory(Integer categoryId) {
        return categoryToQuestions.get(categoryId);
    }

    public int size() {
        return questions.size();
    }

    public Collection<Integer> getCategories() {
        return categoryToQuestions.keySet();
    }

    public Question getQuestion(int questionId){
        return idToQuestion.get(questionId);
    }

    public double calculateOverallScore(Map<Integer, String> answers) {
        int countCorrect = 0;
        for (Map.Entry<Integer, String> entry : answers.entrySet()) {
            int questionId = entry.getKey();
            String givenAnswer = entry.getValue();
            Question question = this.getQuestion(questionId);
            String correctAnswer = question.getCorrectAnswer();
            if (correctAnswer.equals(givenAnswer)) {
                countCorrect++;
            }
        }
        return ((double) countCorrect) / this.size();
    }

    public double calculateCategoryScore(Integer category, Map<Integer, String> answers) {
        List<Question> questionsForCategory = getQuestionsForCategory(category);
        int countCorrect = 0;
        for (Question question : questionsForCategory) {
            String givenAnswer = answers.get(question.getId());
            String correctAnswer = question.getCorrectAnswer();
            if (correctAnswer.equals(givenAnswer)) {
                countCorrect++;
            }
        }
        return ((double) countCorrect) / questionsForCategory.size();
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }
}
