package de.martenl.interview.datastructures;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class LinkedListTest extends TestCase {



    @Test
    public void testLengthWithEmptyList() throws Exception {
        LinkedList<Integer> objectUnderTest = new LinkedList<Integer>();
        final int expResult = 0;
        final int result = objectUnderTest.length();
        assertEquals(expResult,result);
    }

    @Test
    public void testLength() throws Exception {
        LinkedList<Integer> objectUnderTest = new LinkedList<Integer>();
        objectUnderTest.add(1);
        final int expResult = 1;
        final int result = objectUnderTest.length();
        assertEquals(expResult,result);
    }

    //@Test
    public void testCreateList() throws Exception {

    }

    //@Test
    public void testEquals() throws Exception {

    }

    //@Test
    public void testHashCode() throws Exception {

    }

    //@Test
    public void testHasNext() throws Exception {

    }

    //@Test
    public void testNext() throws Exception {

    }

    @Test
    public void testWhere() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList(1,2,3,4);
        LinkedList<Integer> expResult = LinkedList.createList(0,2);
        LinkedList<Integer> result = objectUnderTest.where(x -> x % 2 == 1);
        assertEquals(expResult, result);

    }

    @Test
    public void testReverseWithEmptyList() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList();
        LinkedList<Integer> result = objectUnderTest.reverse();
        assertEquals(objectUnderTest,result);
    }

    @Test
    public void testReverseWithSingleElementList() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList(1);
        LinkedList<Integer> result = objectUnderTest.reverse();
        assertEquals(objectUnderTest,result);
    }

    @Test
    public void testReverse() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList(1,2,3);
        LinkedList<Integer> result = LinkedList.createList(3, 2, 1);
        objectUnderTest.reverse();
        assertEquals(objectUnderTest, result);
    }

    @Test
    public void testClear() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList(1,2,3);
        objectUnderTest.clear();
        assertEquals(0,objectUnderTest.length());
    }

    @Test
    public void testMap() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList(1,2,3);
        LinkedList<Integer> expResult = LinkedList.createList(1,4,9);
        LinkedList<Integer> result = objectUnderTest.map(x -> x * x);
        assertEquals(expResult,result);
    }

    @Test
    public void testReduce() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList(1,2,3);
        final int expResult = 6;
        final int result = objectUnderTest.reduce((x, y) -> x + y, 0);
        assertEquals(expResult,result);
    }

    @Test
    public void testPartitionBy() throws Exception {
        LinkedList<Integer> objectUnderTest = LinkedList.createList(1,2,3);
        LinkedList<Integer>[] expResult = new LinkedList[]{LinkedList.createList(1,3),LinkedList.createList(2)};
        LinkedList<Integer>[] result = objectUnderTest.partitionBy(x -> x % 2 == 1);
        assertArrayEquals(expResult, result);
    }

    public void testForEach() throws Exception{
    }
}