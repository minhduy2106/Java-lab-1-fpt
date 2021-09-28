import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Validate {
    static Scanner sc = new Scanner(System.in);

    public static String checkString(){
        boolean check = false;
        String str = "";
        do{
            try{
                str = sc.nextLine().trim();
                if(str.isEmpty()) throw new Exception();
                check = false;
            }catch (Exception e){
                System.out.println("Input need to be not empty");
                System.out.println("==========================");
                System.err.print("Input try again : ");
            }
        }while (check);
        return str;
    }

    public static float inputFloat(){
        while (true){
            try{
                float value;
                Scanner scanner = new Scanner(System.in);
                value = Float.parseFloat(scanner.nextLine());
                if (value < 0) {
                    throw new Exception();
                }
                return value;
            }catch(NumberFormatException error){
                System.err.println("Input weight again : ");
            } catch (Exception e) {
                System.out.println("Weight must be >0, So input weight again : ");
            }
        }
    }

    public static boolean checkCode(ArrayList<Food> foods, String ID){
        for(Food food : foods){
            if(ID.equalsIgnoreCase(food.getID())){
                return false;
            }
        }
        return true;
    }

    public static int checkIn(int min, int max){
        while (true){
            try{
                int num = Integer.parseInt(sc.nextLine().trim());
                if(num < min || num > max) throw new Exception();
                return num;
            }catch (Exception e){
                System.out.println("Invalid input");
                System.out.println("Enter again : ");
            }
        }
    }

    public static String inputDate(){
            Scanner sc = new Scanner(System.in);
            Date currentDay = new Date();
            while (true) {
                String date = sc.nextLine();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    Date parseExpiredDay = sdf.parse(date);
                    if (parseExpiredDay.before(currentDay)) {
                        throw new IllegalArgumentException();
                    }
                    return date;
                } catch (ParseException e) {
                    System.out.println("-----------------");
                    System.out.print("Date format is incorrect!! Please enter again: ");
                } catch (IllegalArgumentException i) {
                    System.out.println("-----------------");
                    System.out.print("This product is out of date!! Please enter again: ");
                }
            }
    }


}
