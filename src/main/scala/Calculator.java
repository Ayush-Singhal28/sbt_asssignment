

import java.util.Scanner;
import java.util.Calendar;
import java.sql.*;

public class Calculator {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String USER = "root";
    static final String PASS = "qwerty";
    static final String DB_URL = "jdbc:mysql://localhost/Calc";


    public static void main(String args[]) {
        Connection conn = null;
        Statement stmt = null;
        try{

            Scanner keyboard=new Scanner(System.in);

            System.out.println("id:");
            System.out.println("1. add");
            System.out.println("2. subtract");
            System.out.println("3. mutiply");
            System.out.println("4. divide");
            System.out.println("5. modulus");
            System.out.println("6. power");
            System.out.println("7. absolute");
            System.out.println("8. maximum");
            System.out.println("9. minimum");

            System.out.println("Enter id");
            int id = keyboard.nextInt();

            System.out.println("Enter you left_operand");
            int left_operand=keyboard.nextInt();
            System.out.println("Enter your right_operand");
            int right_operand=keyboard.nextInt();


            System.out.println("Enter your operator");
            String operator=keyboard.nextLine();

            Calendar calendar = Calendar.getInstance();
            java.sql.Date date_created = new java.sql.Date(calendar.getTime().getTime());


            int result=0;

            switch(operator) {


                case "add" :
                    result=left_operand + right_operand ;
                    break;
                case "subtract" :
                    result = left_operand - right_operand;
                    break;
                case "multiply" :
                    result = left_operand * right_operand;
                    break;
                case "divide" :
                    if(right_operand!=0)
                        result = left_operand/right_operand;
                    break;
                case "modulus" :
                    if(right_operand!=0)
                        result = left_operand%right_operand;
                    break;
                case "power" :
                    result = (int) Math.pow(left_operand,right_operand);
                    break;
                case "absolute" :
                    result = Math.abs(left_operand);
                    break;
                case "maximum" :
                    result = Math.max(left_operand,right_operand);
                    break;
                case "minimum" :
                    result = Math.min(left_operand,right_operand);
                    break;
                default:
            }




            Class.forName("com.mysql.jdbc.Driver");


            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            System.out.println("Creating statement...");
            stmt = conn.createStatement();


        String query = " insert into calculator (id, date_created, left_operand, right_operand, operator, result)"
                + " values (?, ?, ?, ?, ?,?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt (1, id);
        preparedStmt.setDate (2, date_created);
        preparedStmt.setInt   (3, left_operand);
        preparedStmt.setInt(4, right_operand);
        preparedStmt.setString    (5, operator);
        preparedStmt.setInt(6, result);

        preparedStmt.execute();

        conn.close();
    }
    catch (Exception e)
    {
        System.err.println("Got an exception!");
        System.err.println(e.getMessage());
    }



    }
}
