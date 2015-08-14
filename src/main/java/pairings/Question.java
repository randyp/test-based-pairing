package pairings;

public class Question {

    private final int id;
    private final int categoryId;
    private final String correctAnswer;

    public Question(int id, int categoryId, String correctAnswer) {
        this.id = id;
        this.categoryId = categoryId;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
