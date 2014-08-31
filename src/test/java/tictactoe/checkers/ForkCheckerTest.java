package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.Board;
import tictactoe.Result;
import tictactoe.builders.BoardBuilder;
import tictactoe.Seed;

public class ForkCheckerTest {

    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};


    @Test
    public void shouldNotReturnResultWhenNoForkIsPossible() {

        Checker winningChecker = mockery.mock(Checker.class);

        Board emptyBoard = new BoardBuilder()
                .build();

        mockery.checking(new Expectations(){
            {
                allowing(winningChecker).check(with(any(Board.class)), with(Seed.COMPUTER));
                will(returnValue(Result.indeterminateResult()));
            }
        });

        Result forkResult = new ForkChecker(winningChecker).check(emptyBoard, Seed.COMPUTER);

        Assert.assertThat(forkResult.hasBeenDetermined(), Matchers.is(false));
    }

    @Test
    public void shouldReturnResultWhen() {

    }
}