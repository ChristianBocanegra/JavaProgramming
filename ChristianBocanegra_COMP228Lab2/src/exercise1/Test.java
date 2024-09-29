package exercise1;
import javax.swing.JOptionPane;
import java.util.Random;

public class Test {
    private String[] questions = {
            "What is an Object according to Java program?",
            "What is a Class according to Java program?",
            "What does the keyword void indicate in a method?",
            "What role do objects play in a typical Java program?",
            "What is the main characteristic of variables or methods declared with the access modifier private?"
    };

    private String[][] options = {
            {"a) A general term that can stand for many things.", "b) A term that refers only to physical elements.",
                    "c) A term that only applies to people.", "d) A term used only to define properties."},
            {"a) A single object in Java.", "b) A variable that describes the behavior of an object.",
                    "c) A collection of related objects.", "d) A fixed type that cannot be extended in Java."},
            {"a) The method will perform a task but will not return any information.", "b) The method will return a value to its caller.",
                    "c) The method does not require any parameters to perform its task.", "d) The method is private and cannot be accessed by other classes."},
            {"a) Objects interact by invoking methods to perform various tasks.", "b) Objects are only used to create a graphical user interface (GUI).",
                    "c) Objects do not interact with each other in a Java program.", "d) Objects are not recycled after they complete their tasks."},
            {"a) They are accessible only to methods of the class in which they’re declared.", "b) They are accessible from any class in the program.",
                    "c) They must be followed by a comma in the declaration.", "d) They can only be used in a parameter list."}
    };

    private char[] answers = {'a', 'c', 'a', 'a', 'a'};
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private Random random = new Random();

    // Method to display questions and interact with the user
    public void inputAnswer() {
        for (int i = 0; i < questions.length; i++) {
            char userAnswer = simulateQuestion(i);
            if (checkAnswer(i, userAnswer)) {
                correctAnswers++;
                JOptionPane.showMessageDialog(null, generateMessage(true));
            } else {
                incorrectAnswers++;
                JOptionPane.showMessageDialog(null, generateMessage(false) + " The correct answer was: " + answers[i]);
            }
        }
        displayResult();
    }

    // Method to display a question and get the user's answer
    public char simulateQuestion(int questionIndex) {
        String question = questions[questionIndex];
        String[] choices = options[questionIndex];
        String input = JOptionPane.showInputDialog(
                question + "\n" +
                        String.join("\n", choices)
        );
        return input != null && !input.isEmpty() ? input.toLowerCase().charAt(0) : ' ';
    }

    // Method to check if the user's answer is correct
    public boolean checkAnswer(int questionIndex, char userAnswer) {
        return userAnswer == answers[questionIndex];
    }

    // Method to generate a random response message
    public String generateMessage(boolean correct) {
        int messageIndex = random.nextInt(4);
        String message = "";

        if (correct) {
            switch (messageIndex) {
                case 0: message = "Excellent!"; break;
                case 1: message = "Good!"; break;
                case 2: message = "Keep up the good work!"; break;
                case 3: message = "Nice work!"; break;
            }
        } else {
            switch (messageIndex) {
                case 0: message = "No. Please try again."; break;
                case 1: message = "Wrong. Try once more."; break;
                case 2: message = "Don't give up!"; break;
                case 3: message = "No. Keep trying."; break;
            }
        }
        return message;
    }

    // Method to display the final results
    public void displayResult() {
        int totalQuestions = questions.length;
        double percentage = ((double) correctAnswers / totalQuestions) * 100;
        JOptionPane.showMessageDialog(
                null,
                "Test Complete!\n" +
                        "Correct Answers: " + correctAnswers + "\n" +
                        "Incorrect Answers: " + incorrectAnswers + "\n" +
                        "Percentage: " + String.format("%.2f", percentage) + "%"
        );
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.inputAnswer();
    }
}

