import java.util.List;
import java.util.Random;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.base.Array1D;
import bridges.data_src_dependent.Shakespeare;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
// a program fragment to access the Shakespeare data (plays, poems)
public class shakespeare_snippet {

    //TASK 2 LINEAR SEARCH
    public static int linearSearch(Array1D<Shakespeare> subsetArray,Shakespeare s){
        int n = subsetArray.getSize();
        for (int i = 0; i < n; i++){
            if (subsetArray.getElement(i).getValue() == s)
            {
                //figure out how to set color of array cell
                subsetArray.getElement(i).setColor("red");
                System.out.println(i);
            }
        }
        return -1;
    }

    //TASK 3 SORTING
    //bubble sort code
    public static void bubbleSort(Array1D<Shakespeare> subsetArray ){
        int n = subsetArray.getSize();
        for(int i =0; i < n-1; i++)
            for(int j =0; j < n-i-1;j++)
                if(subsetArray.getElement(j).getValue().getTitle().compareTo(subsetArray.getElement(j+1).getValue().getTitle()) > 0)
                {
                    Shakespeare temp = subsetArray.getElement(j).getValue();
                    subsetArray.getElement(j).setValue(subsetArray.getElement(j+1).getValue());
                    subsetArray.getElement(j+1).setValue(temp);
                }
    }

    //TASK 4 BINARY SEARCH  //MAKE RECURSIVE
    public static int binarySearch(Array1D<Shakespeare> subsetArray,int l, int r,Shakespeare s){
        if( r >= l) {
            int mid = l + (r - l) / 2;
            if (subsetArray.getElement(mid).getValue().getTitle().equals(s.getTitle())){
                subsetArray.getElement(mid).setColor("yellow");
                System.out.println("\n Found at " + mid);
                return mid;
            }

            if(subsetArray.getElement(mid).getValue().getTitle().compareTo(s.getTitle())>0){
                return binarySearch(subsetArray,l,mid - 1 ,s);
            }

            return binarySearch(subsetArray,mid +1,r,s);
        }
        return -1;
    }

    //TASK 5 TABLE
    //create new array with integers or strings
    //create method with new 1D Array
    //in text file i emailed

    public static void main(String[] args) throws Exception {
        //create the Bridges object
        Bridges bridges = new Bridges(1, "chill", "772935405491");

        // Get a List of Shakespeare objects from Bridges
        DataSource ds = bridges.getDataSource();
        List<Shakespeare> mylist = ds.getShakespeareData();
        //Lines () testing for original code
        // Copy the List of Shakespeare objects to an array
        //Array1D<Shakespeare> arr = new Array1D<Shakespeare> (mylist.size());
        //for (int k = 0; k < arr.getSize(); k++) {
        //arr.getElement(k).setValue(mylist.get(k));
        //arr.getElement(k).setLabel(String.valueOf(arr.getElement(k).getValue().getTitle()));
        //}

        //TASK 1
        //create new array 50 random elements from actual array
        Array1D<Shakespeare> subsetArray = new Array1D<Shakespeare>(170);       
        for (int i =0; i < subsetArray.getSize(); i++){
            Shakespeare s = mylist.get(new Random().nextInt(mylist.size()));
            subsetArray.getElement(i).setValue(s);
            subsetArray.getElement(i).setLabel(s.getTitle());
        }

        //TASK 2: call linear seatch 3 times
        Shakespeare s = mylist.get((new Random()).nextInt(mylist.size()));
        final long startTime = System.nanoTime();
        System.out.println("\n" + linearSearch(subsetArray,s));
        final long endTime = System.nanoTime();
        System.out.println(" \n The runtime is " + (endTime-startTime) + " nanoseconds");

        //testing sort Lines ()
        //System.out.println("Before sorting");
         //for(int i =0; i < subsetArray.getSize();i++){
             //System.out.print(subsetArray.getElement(i).getValue().getTitle()+ "  ");
         //}

        //Task 3 Sort
        bubbleSort(subsetArray);
        
        //after sort test
        // System.out.println(" \n After sorting");
        // for(int i =0; i < subsetArray.getSize();i++){
            // System.out.print(subsetArray.getElement(i).getValue().getTitle() + "  ");
        // }

        //TASK 4 Call Binary Search 3 times
        final long startTime2 = System.nanoTime();
        System.out.println("\n" + binarySearch(subsetArray,0,subsetArray.getSize()-1,s));
        final long endTime2 = System.nanoTime();
        System.out.println(" \n The runtime is " + (endTime2-startTime2) + " nanoseconds");

        //Lines ( ) for original code test
        // Inspect a random Shakespeare object
        //Shakespeare work1 = mylist.get((new Random()).nextInt(mylist.size()));
        //System.out.println(work1.getTitle());
        //System.out.println(work1.getType());
        //System.out.println(work1.getText().substring(0, Math.min(100, work1.getText().length())) + "...");
        //tell Bridges what datastructure to visualize
        //bridges.setDataStructure(arr);

        //attempt to get subset array displayed
        bridges.setDataStructure(subsetArray);
        // visualize the list
        bridges.visualize();

    }
}

