import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class SpaceChallenge {
    public static void main (String [] args){
        Simulation sim = new Simulation();
        String phase1 = "E:\\Machine_learning_nd\\JavaPrep\\SpaceChallenge\\out\\production\\SpaceChallenge\\phase-1.txt";

        ArrayList<Item> items;
        // Phase 1
        try{
             items = sim.loadItems(phase1);
        }
        catch (FileNotFoundException e){
            System.out.println("There was some error");
            return;
        }

        ArrayList<U1> arrayU1 = sim.loadU1(items);
        ArrayList<U2> arrayU2 = sim.loadU2(items);


    }
}

class Item{
    String name;
    int weight;
    Item(String name,int weight){
        this.name = name;
        this.weight = weight;
    }
}

interface SpaceShip{
    boolean launch();
    boolean land();
    boolean canCarry(Item item);
    void carry(Item item);
}

class Rocket implements SpaceShip{
    int weight;
    int cost;
    int maxWeigth;


    public boolean launch(){
      return true;
    }
    public boolean land(){
        return true;
    }
    public final boolean canCarry(Item item){
        if(item.weight <= this.maxWeigth - this.weight) {
            return true;
        } else return false;
    }
    public final void carry(Item item){
        this.weight+= item.weight;
    }

}

class U1 extends Rocket{
    U1(){
        //super();
        this.cost = 100000000;
        this.maxWeigth = 18000; // in kgs
        this.weight = 10000; // in kgs

    }
    @Override
    public boolean launch(){
        double randNum = Math.random();
        double launchExplo = ((float) this.weight / this.maxWeigth) * (5.0 / 100);

        if(randNum > launchExplo) return true;
        else return false;
    }

    @Override
    public boolean land(){
        double randNum = Math.random();
        double landCrash = ((float) this.weight / this.maxWeigth) * (1.0 / 100);
        if(randNum > landCrash){
            //System.out.println("Safe to land rocket U1");
            return true;
        }
        else {
            //System.out.println("Unsafe to land rocket U1");
            return false;
        }
    }
}

class U2 extends Rocket{
    U2(){
        //super();
        this.cost = 120000000;
        this.maxWeigth = 29000; // in kgs
        this.weight = 18000; // in kgs

    }
    @Override
    public boolean launch(){
        double randNum = Math.random();
        double launchExplo = ((float) this.weight / this.maxWeigth) * (4.0 / 100);

        if(randNum > launchExplo) return true;
        else return false;
    }

    @Override
    public boolean land(){
        double randNum = Math.random();
        double landCrash = ((float) this.weight / this.maxWeigth) * (8.0 / 100);

        if(randNum > landCrash) return true;
        else return false;
    }
}

class Simulation {
    // This reads the file txt and fills up the items

    ArrayList<Item> loadItems(String path) throws FileNotFoundException{
        ArrayList<Item> items = new ArrayList<Item>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            String inputLine = scanner.nextLine();
            String[] splitArr = inputLine.split("=");

            items.add(new Item(splitArr[0],(int) Integer.valueOf(splitArr[1])));

        }
        /*

        for(Item itr:items){
        System.out.println("the item is : " + itr.name + " and has the weight " + itr.weight);
        }
        */
        return items;
    }

    ArrayList<U1> loadU1(ArrayList<Item> items){
        // So You need to add
        ArrayList<U1> arrayU1 = new ArrayList<U1>();
        arrayU1.add(new U1());

        U1 presentU1 = arrayU1.get(arrayU1.size() - 1);

        for(Item itr: items){

            if(!presentU1.canCarry(itr)){
                arrayU1.add(new U1());
                presentU1 = arrayU1.get(arrayU1.size() - 1);
            }
            presentU1.carry(itr);

        }

        System.out.println("Number of U1 rockets used = " + arrayU1.size());
        return arrayU1;
    }

    ArrayList<U2> loadU2(ArrayList<Item> items){
        // So You need to add
        ArrayList<U2> arrayU2 = new ArrayList<U2>();
        arrayU2.add(new U2());

        U2 presentU2 = arrayU2.get(arrayU2.size() - 1);

        for(Item itr: items){

            if(!presentU2.canCarry(itr)){
                arrayU2.add(new U2());
                presentU2 = arrayU2.get(arrayU2.size() - 1);
            }
            presentU2.carry(itr);

        }

        System.out.println("Number of U2 rockets used = " + arrayU2.size());
        return arrayU2;
    }


}