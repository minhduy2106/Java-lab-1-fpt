import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FoodList extends Vector<Food> {
    static Scanner sc = new Scanner(System.in);

    //Menu
    public static int Menu(){
        int choice;
        System.out.println("Welcome to food management - @ 2021 by SE150047 - Le Nguyen Minh Duy");
        System.out.println("=========Menu=========");
        System.out.println("1. Add a new Food");
        System.out.println("2. Search Food by name");
        System.out.println("3. Remove Food by ID ");
        System.out.println("4. Print the food list in the descending order of expired date");
        System.out.println("5. Out the program");
        System.out.println("======================");
        System.out.print("Enter your choice :");
        choice = Validate.checkIn(1,5);
        return choice;
    }

    //Menu 1
    public static int Menu1(){
        int choice;
        System.out.println("Do you want to add another food : ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        choice = Validate.checkIn(1,2);
        return choice;
    }

    //Menu 2
    public static int Menu2(){
        int choice;
        System.out.println("Do you want to search another food : ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        choice = Validate.checkIn(1,2);
        return choice;
    }

    //Menu 3
    public static int Menu3(){
        int choice;
        System.out.println("Do you want to remove another food : ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        choice = Validate.checkIn(1,2);
        return choice;
    }

    //Display 1 food
    public static void displayFood(Food food){
        System.out.printf("%-10s%-10s%-25s%-10s%-10s%-10s\n",
                food.getID(), food.getName(), food.getWeight(), food.getType(), food.getPlace(), food.getExpiredDate());
    }

    //Display all
    public static void displayAll(ArrayList<Food> foods){
        if(foods.size() == 0){
            System.out.println("Empty list");
            return;
        }else{
            System.out.printf("%-10s%-10s%-25s%-10s%-10s%-10s\n", "ID", "Name", "Weight", "Type", "Place", "ExpiredDay");
            System.out.println("----------------------------------------------------------------------------------");
            for(var food : foods){
                displayFood(food);
            }
        }
    }

    //Add Food
    public static void addFood(ArrayList<Food> foods){
        System.out.print("Enter ID : ");
        String code = Validate.checkString();
        if(!Validate.checkCode(foods, code)){
            System.out.println("The Food has ID : " + code + " is exist.");
        }else{
            System.out.print("Enter name : ");
            String name = Validate.checkString();
            System.out.print("Enter weight : ");
            float weight = Validate.inputFloat();
            System.out.print("Enter Type : ");
            String type = Validate.checkString();
            System.out.print("Enter Place : ");
            String place = Validate.checkString();
            System.out.print("Enter Expired Day (dd/MM/yyyy): ");
            String ex = Validate.inputDate();
            foods.add(new Food(code, name, weight, type, place, ex));
        }
    }

    //Search food
    public static void searchFood(ArrayList<Food> foods){
        System.out.print("Enter name of food : ");
        String key = Validate.checkString().toLowerCase(Locale.ROOT);
        ArrayList<Food> found = getListByName(foods,key);
        if(found.size() == 0){
            System.err.println("This food is not exist");
        } else{
            displayAll(found);
        }
    }

    //search by name
    public static ArrayList<Food> getListByName(ArrayList<Food> foods, String key){
        ArrayList<Food> foodFoundByName = new ArrayList<>();
        for(Food food:foods){
            if(food.getName().contains(key)){
                foodFoundByName.add(food);
            }
        }
        return foodFoundByName;
    }

    //get food from code
    public static Food getFoodByCode(ArrayList<Food> foods, String code){
        for (Food food:foods){
            if(food.getID().equalsIgnoreCase(code)){
                return food;
            }
        }
        return null;
    }

    //remove food by ID
    public static void removeFoodByID(ArrayList<Food> foods){
        System.out.print("Enter ID to remove : ");
        String id = Validate.checkString();
        Food food = getFoodByCode(foods,id);
        if(Validate.checkCode(foods,id)){
            System.out.println("No found that food");
            return;
        }else {
            foods.remove(food);
            System.out.println("Remove SuccessFull");
        }
    }


    //Print descending order of expired date
    public static void sortByDate(ArrayList<Food> foods) {
        Collections.sort(foods, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                try {
                    return (sdf.parse(o2.getExpiredDate())).compareTo(sdf.parse(o1.getExpiredDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    //write file
    public static void writeFile(ArrayList<Food> foods ,String fName){
        try{
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i=0; i<foods.size(); i++){
                pw.println(foods.get(i).getID() + "," + foods.get(i).getName() + "," + foods.get(i).getWeight()
                        + "," + foods.get(i).getType() + "," + foods.get(i).getPlace() + "," + foods.get(i).getExpiredDate());
            }
            fw.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Read file
    public static void readFile(ArrayList<Food> foods, String fName){
        try{
            File f = new File(fName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String detail;
            while ((detail = br.readLine())!=null){
                StringTokenizer stk = new StringTokenizer(detail, ",");
                String ID = stk.nextToken();
                String name = stk.nextToken();
                Float weight = Float.parseFloat(stk.nextToken());
                String type = stk.nextToken();
                String place = stk.nextToken();
                String expiredDay = stk.nextToken();

                Food fd = new Food(ID, name, weight, type,place,expiredDay);
                foods.add(fd);
            }
            fr.close();
            br.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
