import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Food> foods = new ArrayList<>();

        int choice, m1;
        String fName = "food.dat";
        FoodList.readFile(foods, fName);

        do{
            choice = FoodList.Menu();
            switch (choice){
                case 1:
                    FoodList.addFood(foods);
                    do{
                        m1 = FoodList.Menu1();
                        switch (m1){
                            case 1 :
                                FoodList.addFood(foods);
                                break;
                            case 2 :
                                System.err.println("Add Foods SuccessFul");
                                break;
                        }
                    }while (m1>0 && m1<2);
                    break;
                case 2:
                    FoodList.displayAll(foods);
                    FoodList.searchFood(foods);
                    do{
                        m1 = FoodList.Menu2();
                        switch (m1){
                            case 1 :
                                FoodList.displayAll(foods);
                                FoodList.searchFood(foods);
                                break;
                            case 2 :
                                break;
                        }
                    }while (m1>0 && m1<2);
                    break;
                case 3:
                    FoodList.displayAll(foods);
                    FoodList.removeFoodByID(foods);
                    do{
                        m1 = FoodList.Menu3();
                        switch (m1){
                            case 1 :
                                FoodList.displayAll(foods);
                                FoodList.removeFoodByID(foods);
                                break;
                            case 2 :
                                break;
                        }
                    }while (m1>0 && m1<2);
                    break;
                case 4 :
                    FoodList.sortByDate(foods);
                    FoodList.displayAll(foods);
                    break;
                case 5 :
                    FoodList.writeFile(foods, fName);
                    break;
            }
        }while (choice < 5 && choice >0);
    }
}
