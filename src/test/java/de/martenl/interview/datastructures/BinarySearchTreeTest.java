package de.martenl.interview.datastructures;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marten on 28.11.2015.
 */
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> objectUnderTest;
    @Test
    public void testMin() throws Exception {
        objectUnderTest = BinarySearchTree.create(4,8,7,1,9,2,6,3,5);
        final Optional<Integer> expResult = Optional.of(1);
        final Optional<Integer> result = objectUnderTest.min();
        assertEquals(expResult,result);
    }

    @Test
    public void testMinWithEmptyTree() throws Exception{
        objectUnderTest = new BinarySearchTree<Integer>();
        final Optional<Integer> expResult = Optional.empty();
        final Optional<Integer> result = objectUnderTest.min();
        assertEquals(expResult,result);
    }

    @Test
    public void testMax() throws Exception {
        objectUnderTest = BinarySearchTree.create(4,8,7,1,9,2,6,3,5);
        final Optional<Integer> expResult = Optional.of(9);
        final Optional<Integer> result = objectUnderTest.max();
        assertEquals(expResult,result);
    }

    @Test
    public void testMaxWithEmptyTree() throws Exception{
        objectUnderTest = new BinarySearchTree<Integer>();
        final Optional<Integer> expResult = Optional.empty();
        final Optional<Integer> result = objectUnderTest.max();
        assertEquals(expResult,result);
    }
}