package vanvtt.learn.junit.ExampleTestcase;

import junit.framework.TestCase;

public class MyTestCase extends TestCase {

    protected ListTest lstTest = new ListTest();

    protected void setUp() {
        lstTest.add("Apple");
        lstTest.add("Orange");
        lstTest.add("Grapes");
    }

    public void testSize() {
        assertEquals("Checking size of List", 3, lstTest.size());
    }

    public void testAdd() {
        lstTest.add("Banana");
        assertEquals("Adding 1 more fruit to list", 4, lstTest.size());
    }

    public void testRemove() {
        lstTest.remove("Orange");
        assertEquals("Removing 1 fruit from list", 2, lstTest.size());
    }

    protected void tearDown() {
        lstTest.removeAll();
    }
}