package com.example.nitrite;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.FindOptions.limit;

public final class DatabaseConnection {

    private static final DatabaseConnection DB_MEMORY_CONN = new DatabaseConnection();
    private static final DatabaseConnection DB_FILE_CONN = new DatabaseConnection("test.db");

    private Nitrite dbMemory;
    private Nitrite dbFile;
    private ObjectRepository<Employee> repository;

    private DatabaseConnection(){
        dbMemory = Nitrite.builder().openOrCreate();
        repository = dbMemory.getRepository(Employee.class);
    }

    private  DatabaseConnection(String filePath){
        dbFile = Nitrite.builder().filePath(filePath).openOrCreate();
        repository = dbFile.getRepository(Employee.class);
    }

    public static DatabaseConnection getMemoryInstance() {
        return DB_MEMORY_CONN;
    }

    public static DatabaseConnection getFileInstance() {
        return DB_FILE_CONN;
    }


    /**
     * Inserting into inmemory
     * @param employee
     * @return
     */
    public List<Employee> insertIntoMemory(Employee employee) {
        return insert(employee);
    }


    /**
     * Inserting into file
     * @param employee
     * @return
     */
    public List<Employee> insertIntoFile(Employee employee) {
        return insert(employee);
    }

    /**
     * Find from memory
     * @param offset
     * @param size
     * @return
     */
    public List<Employee> findFromMemory(int offset, int size){
        return find(offset, size);
    }

    /**
     *
     * @param offset
     * @param size
     * @return
     */
    public List<Employee> findFromFile(int offset, int size){
        return find(offset, size);
    }


    private List<Employee> insert(Employee employee){
        List<Employee> employees = new ArrayList<Employee>();
        WriteResult wr = null;
        try {
            wr = repository.insert(employee);
            System.out.println("\n Affected Rows : " + wr.getAffectedCount());
            return repository.find().toList();

        } catch (Exception e) {
            System.out.println("Unable to insert transaction. ");
            e.printStackTrace();

        }
        return employees;
    }


    private List<Employee> find(int offset, int size) {

        List<Employee> employees = new ArrayList<Employee>();
        try {
            employees = repository.find(limit(offset, size)).toList();
        } catch (Exception e) {
            System.out.println("Unable to find transactions. ");
            e.printStackTrace();
        }
        return employees;
    }

}
