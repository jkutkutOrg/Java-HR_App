package db;

import org.jkutkut.db.SQLQuery;
import org.jkutkut.hr_app.db.HRDB;
import org.jkutkut.hr_app.javabean.Employee;

import java.util.ArrayList;

public class hrdbTest {
    public static void main(String[] args) {
        HRDB db = HRDB.createInstance();

        for (Employee e : db.getAllEmployees()) {
            System.out.println(e);
        }
    }

    public static ArrayList<Object[]> selectTest(HRDB db) {
        return SQLQuery.get(db, 11, "SELECT * FROM EMPLOYEE ;");
    }
}
