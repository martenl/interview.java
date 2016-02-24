package de.martenl.interview.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {

    private Stack<Integer> objectUnderTest;
    private LinkedList<Integer> internalData;

    @Before
    public void setUp() throws Exception {
        internalData = new LinkedList<>();
        objectUnderTest = new Stack<>(internalData);
    }

    @Test
    public void testPush() throws Exception {
        final int expectedSize = internalData.length()+1;
        objectUnderTest.push(1);
        assertEquals(expectedSize,internalData.length());
    }

    @Test
    public void testPopWithEmptyStack() throws Exception {
        final int initialSize = internalData.length();
        objectUnderTest.pop();
        assertEquals(initialSize,internalData.length());
    }

    @Test
    public void testPop() throws Exception {
        internalData.add(1,0);
        final int initialSize = internalData.length();
        objectUnderTest.pop();
        assertEquals(initialSize-1,internalData.length());
    }
}