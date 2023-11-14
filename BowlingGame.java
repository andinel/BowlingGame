
public class BowlingGame {

    private static final int TOTAL_FRAMES = 10;
    private static final int MAX_PINS = 10;

    private int roll = 0;
    private final int[] rolls = new int[21];

    public void roll(int pinsDown) {
        rolls[roll++] = pinsDown;
    }

    public int score() {
        int score = 0;
        int index = 0;

        for (int frame = 0; frame < TOTAL_FRAMES; frame++) {

            if (isStrike(index)) {
                score += 10 + strikeBonus(index);
                index++;

            } else if (isSpare(index)) {
                score += 10 + spareBonus(index);
                index += 2;

            } else {
                score += sumOfRollsInFrame(index);
                index += 2;
            }
        }
        return score;
    }

    private boolean isSpare(int index) {
        return rolls[index] + rolls[index+1] == MAX_PINS;
    }

    private boolean isStrike(int index) {
        return rolls[index] == MAX_PINS;
    }

    private int strikeBonus(int index) {
        return rolls[index + 1] + rolls[index + 2];
    }

    private int spareBonus(int index) {
        return rolls[index + 2];
    }

    private int sumOfRollsInFrame(int index) {
        return rolls[index] + rolls[index + 1];
    }

}