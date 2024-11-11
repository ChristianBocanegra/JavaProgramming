package exercise2;
import javax.swing.JOptionPane;
import java.util.Random;

public class Lotto {

    private int[] number;

    public Lotto() {
        number = new int[3];
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            number[i] = random.nextInt(9) + 1;
        }
    }

    public int[] getNumbers() {
        return number;
    }

    public int getSum() {
        int sum = 0;
        for (int num : number) {
            sum += num;
        }
        return sum;
    }

    public void playLottoGame() {

        int userNumber = 0;
        boolean validInput = false;

        while (!validInput) {
            String userInput = JOptionPane.showInputDialog(null, "Please enter a number between 3 and 27:");

            try {
                userNumber = Integer.parseInt(userInput);
                if (userNumber >= 3 && userNumber <= 27) {
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid number! Please enter a number between 3 and 27. \n Try again");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid entry! Please enter a number between 3 and 27. \n Try again");
            }
        }


        try {

            boolean userWin = false;


            for (int i = 0; i < 5; i++) {
                Lotto lotto = new Lotto();
                int[] numbers = lotto.getNumbers();
                int sum = lotto.getSum();

                JOptionPane.showMessageDialog(null, "Lottery numbers: " + numbers[0] + ", " + numbers[1] + ", " + numbers[2] + "\nSum: " + sum);

                if (sum == userNumber) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You won the game.");
                    userWin = true;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry, the sum does not match your number.");
                }
            }

            if (!userWin) {
                JOptionPane.showMessageDialog(null, "The computer wins! Keep trying.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid");
        }
    }

    public static void main(String[] args) {
        Lotto gameOne = new Lotto();
        gameOne.playLottoGame();
    }
}

