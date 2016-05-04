package practice2;

import org.junit.*;
import static org.junit.Assert.*;



/**
 * Created by Marva on 14.02.2016.
 */
public class DigitsTest {

    @Test
    public void testProductSimple() throws Exception {
        assertEquals(1,Digits.product(111111));
        assertEquals(6,Digits.product(123));
    }

    @Test
    public void testProductZero() throws Exception {
        assertEquals(0,Digits.product(254064654));
        assertEquals(0,Digits.product(0));
        assertEquals(35,Digits.product(071));
    }

    @Test
    public void testProductMaximum() throws Exception {
        int bigNumber=999999999;
        assertEquals((int)Math.pow(9,String.valueOf(bigNumber).length()),Digits.product(bigNumber));
    }

    @Test
    public void testProductNegative() throws Exception {
        assertEquals(10,Digits.product(-25));
    }
}