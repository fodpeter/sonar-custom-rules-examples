import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class MyClass {

    @Test
    public void test() {
        Assert.assertEquals("", "");
        Assert.assertEquals(ObjectJoiner.joinString("str", "ing"), "string"); // Noncompliant
        Assert.assertEquals(Math.abs(-1), 1); // Noncompliant
        Assert.assertEquals(1, Math.abs(-1));
    }

    @Test
    public void testAssertEqualsWithTwoPamaters() {
        String string = null;
        Assert.assertEquals("expected", string);
        Assert.assertEquals("expected", "actual");
        Assert.assertEquals(string, "expected"); // Noncompliant

        assertOtherMethodName(string, "expected");

        int i = 5;
        Assert.assertEquals(1, i);
        Assert.assertEquals(1, 2);
        Assert.assertEquals(i, 2); // Noncompliant
        assertEquals(1, 2);
        assertEquals(1, i);
        assertEquals(i, 2); // Noncompliant
    }

    @Test
    public void testAssertEqualsWithNonTwoPamaters() {
        assertEquals();
        assertEquals("");
        assertEquals("a", "b", "c");

        String string = null;
        assertEquals(string, "b", "c");
    }

    private void assertOtherMethod(String a, String b) {
    }

    private void assertEquals() {
    }

    private void assertEquals(String a) {
    }

    private void assertEquals(String a, String b, String c) {
    }
}