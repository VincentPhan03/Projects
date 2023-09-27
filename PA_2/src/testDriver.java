import java.util.Random;

public class testDriver {

  public static void main(String[] args) {
    Random rand = new Random();

    Integer[] mergeSortImprovedTest = new Integer[10];


    /*
     * for (int j = 0; j < 5; j++) { System.out.println("Before Sort: "); for (int i = 0; i < 10;
     * i++) { mergeSortImprovedTest[i] = rand.nextInt(1000);
     * System.out.println(mergeSortImprovedTest[i]); }
     * 
     * System.out.println("After Sort: ");
     * IntrospectiveSort.introspectiveSort(mergeSortImprovedTest); for (Integer i :
     * mergeSortImprovedTest) { System.out.println(i.toString()); } }
     */
    
    Integer[] mergeSortImprovedTest1 = { 9, 8, 7, 6, 5, 4,3 ,2 ,1};
    MergeSortImproved.mergeSortAdaptive(mergeSortImprovedTest1);
    
    for (Integer i : mergeSortImprovedTest1) {
      System.out.println(i.toString());
    }
  }
}
