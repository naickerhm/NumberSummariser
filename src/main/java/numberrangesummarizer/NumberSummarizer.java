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
    public Collection<Integer> collect(String input) {

        Collection<Integer> collectionOfInts = null;
        try{
            collectionOfInts = Stream.of(input.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e){
            throw new NumberFormatException("Invalid input \""+ input +"\". Integers required.");

        } catch (NullPointerException e){
            throw new NumberFormatException("Invalid input \"" + input + "\". Integers required.");

        } catch (Exception e){
            throw e;
        }

        return collectionOfInts;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        ArrayList<Integer> sortedInput = new ArrayList<>(input);
        Collections.sort(sortedInput);

        List<List<Integer>> arraysCollected = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(sortedInput.get(0));

        for (int i = 0; i < sortedInput.size() - 1; i++) {
            if (sortedInput.get(i + 1) != sortedInput.get(i) + 1) {
                arraysCollected.add(temp);
                temp = new ArrayList<>();
            }
            temp.add(sortedInput.get(i + 1));
        }

        arraysCollected.add(temp);

        List<String> summarised =new ArrayList<>();

        for(List item:arraysCollected) {
            if(item.size()>1) {
                Integer firstElement = (Integer) item.get(0);
                Integer lastElement = (Integer) item.get(item.size()-1);
                String result = firstElement + "-" + lastElement;
                summarised.add(result);

            }else {
                summarised.add(item.get(0).toString());
            }
            }


        return String.join(",", summarised);
    }
}
