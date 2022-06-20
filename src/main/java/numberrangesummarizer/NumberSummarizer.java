package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class NumberSummarizer implements NumberRangeSummarizer{
    private String input;


    public NumberSummarizer(String input){
        this.input=input;
    }

    public NumberSummarizer(){
    }

    @Override
    public Collection<Integer> collect(String input) throws NumberFormatException, NullPointerException {
        return Stream.of(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<List<Integer>> arraysCollected = new ArrayList<>();
        ArrayList<Integer> sortedInput = new ArrayList<>(input);
        Collections.sort(sortedInput);

        // placeholder list of current consecutive values
        List<Integer> temp = new ArrayList<>();
        temp.add(sortedInput.get(0));

        for (int i = 0; i < sortedInput.size() - 1; i++) {
            int currentValue = sortedInput.get(i);
            int nextValue = sortedInput.get(i + 1);

            //check for consecutive values
            if (nextValue != currentValue + 1) {
                arraysCollected.add(temp);
                temp = new ArrayList<>();
            }
            temp.add(sortedInput.get(i + 1));
        }

        // add last list
        arraysCollected.add(temp);

        List<String> summarised = new ArrayList<>();

        for(List<Integer> item : arraysCollected) {
            if(item.size() > 1) {
                Integer firstElement = item.get(0);
                Integer lastElement = item.get(item.size() - 1);
                summarised.add(firstElement + "-" + lastElement);
            } else {
                summarised.add(item.get(0).toString());
            }
        }

        return String.join(", ", summarised);
    }
}
