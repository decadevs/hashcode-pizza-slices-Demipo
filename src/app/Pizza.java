package app;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Pizza {
    ArrayList<Integer> PizzaTypesArrayList = new ArrayList<Integer>();
    ArrayList<Integer> copyPizzaTypesArrayList = new ArrayList<Integer>();
    ArrayList<Integer> pizzaIndicesArrayList = new ArrayList<Integer>();
    int numberOfPizzaOrdered, numberOfPizzaTypes, itr = 0, loop =0;
    String line;

    public void readFileToStructure(){

        try{
            FileReader fr = new FileReader("input.txt");
            BufferedReader bf = new BufferedReader(fr);
            line = bf.readLine();
            for (String value : line.split(" ")) {
                if (itr == 0) { numberOfPizzaOrdered = Integer.parseInt(value); }
                else { numberOfPizzaTypes = Integer.parseInt(value);  }
                itr++;
            }

            line = bf.readLine();

            for(String value : line.split(" ")){
                PizzaTypesArrayList.add(Integer.parseInt(value));
                copyPizzaTypesArrayList.add(Integer.parseInt(value));
            }
            fr.close();
        }catch(IOException e){
            System.out.println("Could not find file.");
        }
    }

    public void pizzaOrder(){
        int diff = numberOfPizzaOrdered;
        Collections.sort(copyPizzaTypesArrayList);

        for(int j = copyPizzaTypesArrayList.size()-1; j >= 0; j--){
            if(diff >= copyPizzaTypesArrayList.get(j)){
                diff -= copyPizzaTypesArrayList.get(j);
                for(int k = 0; k < PizzaTypesArrayList.size(); k++){
                    if(PizzaTypesArrayList.get(k) == copyPizzaTypesArrayList.get(j) )
                        pizzaIndicesArrayList.add(k);
                }
            }
        }
    }

    public void writeStructureToFile(){
        try{
            FileWriter fw = new FileWriter("output.txt");
            fw.write(String.valueOf(pizzaIndicesArrayList.size())+"\n");
            Collections.sort(pizzaIndicesArrayList);
            for(int j = 0; j < pizzaIndicesArrayList.size(); j++){
                fw.write(String.valueOf(pizzaIndicesArrayList.get(j)));
                fw.write(32); //32 is ASCII value that is equivalent to space.
            }
            fw.close();
        }catch(IOException e){
            System.out.println("Output file error");
        }
    }

    public void getResult(){
        readFileToStructure();
        pizzaOrder();
        writeStructureToFile();
    }

}
