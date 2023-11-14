
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class BowlingGameTests {

    private BowlingGame game;

    @Before
    public void setUp() {
        game = new BowlingGame();
    }

    //X X X X X X X X X X X X (12 rolls: 12 strikes) = 10 frames * 30 points = 300
    @Test
    public void shouldScorePerfectGame() {
        roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertThat(game.score(), is(300));
    }

    //9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90
    @Test
    public void shouldScoreTenPairsOfNine() {
        roll(9,0, 9,0, 9,0, 9,0, 9,0, 9,0, 9,0, 9,0, 9,0, 9,0);
        assertThat(game.score(), is(90));
    }

    //5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150
    @Test
    public void shouldScoreTenPairsOfFiveAndSpareWithFinalFive() {
        roll(5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5);
        assertThat(game.score(), is(150));
    }

    @Test
    public void shouldScoreGutterGame() {
        roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertThat(game.score(), is(0));
    }

    @Test
    public void shouldScoreGameOfOnes() {
        roll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        assertThat(game.score(), is(20));
    }

    @Test
    public void shouldScoreSpareFollowedByThree() {
        roll(5,5, 3,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0);
        assertThat(game.score(), is(16));
    }

    @Test
    public void shouldScoreStrikeFollowedByThreeThenThree() {
        roll(10, 3,3 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0);
        assertThat(game.score(), is(22));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldFailIfMoreThanTwentyOneRolls() {
        roll(10, 3,3 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0, 0,0, 0,0);

    }

    private void roll(int...rolls) {
        for(int pinsDown : rolls) {
            game.roll(pinsDown);
        }
    }

}