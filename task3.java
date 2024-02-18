import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    String question;
    ArrayList<String> options;
    int correctAnswerIndex;

    public QuizQuestion(String question, ArrayList<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizGame {
    static int score = 0;
    static int questionIndex = 0;
    static ArrayList<QuizQuestion> questions = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupQuestions();
        startQuiz();
    }

    public static void setupQuestions() {
        // Add quiz questions with options and correct answers
        ArrayList<String> options1 = new ArrayList<>();
        options1.add("A) Option 1");
        options1.add("B) Option 2");
        options1.add("C) Option 3");
        options1.add("D) Option 4");
        QuizQuestion question1 = new QuizQuestion("What is 2 + 2?", options1, 0);

        ArrayList<String> options2 = new ArrayList<>();
        options2.add("A) Option A");
        options2.add("B) Option B");
        options2.add("C) Option C");
        options2.add("D) Option D");
        QuizQuestion question2 = new QuizQuestion("What is the capital of France?", options2, 2);

        // Add more questions if needed
        questions.add(question1);
        questions.add(question2);
    }

    public static void startQuiz() {
        score = 0;
        questionIndex = 0;

        System.out.println("Welcome to the Quiz Game!");

        // Start asking questions
        askNextQuestion();
    }

    public static void askNextQuestion() {
        if (questionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(questionIndex);
            System.out.println("\nQuestion: " + currentQuestion.question);
            for (String option : currentQuestion.options) {
                System.out.println(option);
            }
            System.out.print("Enter your answer (A, B, C, or D): ");

            // Timer for answering the question
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    questionIndex++;
                    askNextQuestion();
                }
            }, 15000); // 15 seconds timer

            // Get user's answer
            String answer = scanner.next().toUpperCase();
            timer.cancel();

            // Check if the answer is correct
            if (answer.equals(currentQuestion.options.get(currentQuestion.correctAnswerIndex).substring(0, 1))) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }

            questionIndex++;
            askNextQuestion();
        } else {
            showResult();
        }
    }

    public static void showResult() {
        System.out.println("\nQuiz finished!");
        System.out.println("Your score: " + score + "/" + questions.size());

        // Display correct and incorrect answers
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            System.out.print("\nQuestion " + (i + 1) + ": " + question.question);
            if (question.correctAnswerIndex == i) {
                System.out.println(" - Correct");
            } else {
                System.out.println(" - Incorrect");
            }
        }
    }
}