package db;

import org.jkutkut.db.SQLQuery;
import org.jkutkut.hr_app.db.HRDB;

import java.util.ArrayList;

public class hrdbTest {
    public static void main(String[] args) {
        HRDB db = HRDB.createInstance();

        for (Object[] row : selectTest(db)) {
            for (Object col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<Object[]> selectTest(HRDB db) {
        return SQLQuery.get(db, 11, "SELECT * FROM EMPLOYEE ;");
    }
}
