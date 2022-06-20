package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class NumberSummarizerTest {
    String orderedList = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
    List<Integer> orderedNumbers = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
    String unorderedList = "22,23,24,31,1,3,6,7,8,12,13,14,15,21";
    List<Integer> unorderedNumbers = Arrays.asList(22,23,24,31,1,3,6,7,8,12,13,14,15,21);


    @Test
    void testCollectStringAndConvertToIntListOrderedTest() {
        NumberSummarizer summariser = new NumberSummarizer(orderedList);
        Collection<Integer> numberList = summariser.collect(orderedList);
        assertEquals(orderedNumbers,numberList);
    }

    @Test
    void testCollectStringAndConvertToListOfIntsOnUnorderedSeqTest() {
        NumberSummarizer summariser = new NumberSummarizer(unorderedList);
        Collection<Integer> numberList = summariser.collect(unorderedList);
        assertEquals(unorderedNumbers,numberList);
    }
    @Test
    void testCollectOnInvalidDataTest() {
        String input = "string";
        NumberSummarizer summariser = new NumberSummarizer(input);
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            summariser.collect(input);
        });
        assertEquals( "Invalid input \"" +input + "\". Integers required.", exception.getMessage());

    }

    @Test
    void testCollectOnNullTest() {
        String input = null;
        NumberSummarizer summariser = new NumberSummarizer(input);
                Exception exception = assertThrows(NumberFormatException.class, () -> {
            summariser.collect(input);
        });
        assertEquals( "Invalid input \"" +input + "\". Integers required.", exception.getMessage());

    }

    @Test
    void testSummariserOnOrderedListTest(){
        NumberSummarizer summariser = new NumberSummarizer();
        Collection<Integer> numberList = summariser.collect(orderedList);
        String result = summariser.summarizeCollection(numberList);
        assertEquals("1,3,6-8,12-15,21-24,31",result);
    }

    @Test
    void testSummariserOnUnorderedListTest(){
        NumberSummarizer summariser = new NumberSummarizer();
        Collection<Integer> numberList = summariser.collect(unorderedList);
        String result = summariser.summarizeCollection(numberList);
        assertEquals("1,3,6-8,12-15,21-24,31",result);
    }

}