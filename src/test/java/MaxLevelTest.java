import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.mremne.model.identification.FidUtils.extractMaxLevel;

/**
 * autor:maksim
 * date: 14.01.15
 * time: 22:28.
 */
@RunWith(Parameterized.class)
public class MaxLevelTest {
    private String testString;
    private int expectedLevel;
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"{n={value=34}, r={level=1}, c={value=50}}, {n={value=34}, ",1},
                {"r={level=4}, c={value=50}}, {n={value=34},",4},
                {"r={level=6}, c={value=50}}, {n={value=34},",6}
        });
    }
    public MaxLevelTest(String testString, int expectedLevel){
        this.testString=testString;
        this.expectedLevel=expectedLevel;
    }
    @Test
    public void shouldGetMaxLevelFromString(){
        assertThat(expectedLevel,is(extractMaxLevel(testString)));

    }

}
