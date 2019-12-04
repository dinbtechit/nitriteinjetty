package com.example.nitrite;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.FindOptions.limit;

public final class DatabaseConnection {

    private static Nitrite DB;
    private static DatabaseConnection DB_CONN;

    private static ObjectRepository<Employee> REPOSITORY;
    private static String DATABASE_PATH;

    public static DatabaseConnection getInstance() {
        if (DB_CONN == null) {
            synchronized (DatabaseConnection.class) {
                DB_CONN = new DatabaseConnection();
            }
        }
        if (DATABASE_PATH == null) {
            DATABASE_PATH = "/tmp/test.DB";
        }

        return DB_CONN;
    }

    private void openInMemoryConnection() {
       DB = Nitrite.builder().openOrCreate();
       REPOSITORY = DB.getRepository(Employee.class);
    }

    private void openFileConnection() {
        DB = Nitrite.builder().filePath(DATABASE_PATH).openOrCreate();
        REPOSITORY = DB.getRepository(Employee.class);
    }

    private void closeConnection() {
        DB.close();
    }


    /**
     * Inserting into inmemory
     * @param employee
     * @return
     */
    public List<Employee> insertIntoMemory(Employee employee) {
        openInMemoryConnection();
        return insert(employee);
    }


    /**
     * Inserting into file
     * @param employee
     * @return
     */
    public List<Employee> insertIntoFile(Employee employee) {
        openFileConnection();
        return insert(employee);
    }

    /**
     * Find from memory
     * @param offset
     * @param size
     * @return
     */
    public List<Employee> findFromMemory(int offset, int size){
        openInMemoryConnection();
        return find(offset, size);
    }

    /**
     *
     * @param offset
     * @param size
     * @return
     */
    public List<Employee> findFromFile(int offset, int size){
        openFileConnection();
        return find(offset, size);
    }


    private List<Employee> insert(Employee employee){
        List<Employee> employees = new ArrayList<Employee>();
        WriteResult wr = null;
        try {
            wr = REPOSITORY.insert(employee);
            System.out.println("\n Affected Rows : " + wr.getAffectedCount());
            return REPOSITORY.find().toList();

        } catch (Exception e) {
            System.out.println("Unable to insert transaction. ");
            e.printStackTrace();

        } finally {
            closeConnection();
        }
        return employees;
    }


    private List<Employee> find(int offset, int size) {

        List<Employee> employees = new ArrayList<Employee>();
        try {
            employees = REPOSITORY.find(limit(offset, size)).toList();
        } catch (Exception e) {
            System.out.println("Unable to find transactions. ");
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return employees;
    }

}
