import java.io.IOException;
import java.nio.file.*;
import java.util.*;
public class EmployeePayrollService {
    private static final String FILE_NAME = "payroll-files/payroll.txt";
    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService(List<EmployeePayrollData> list) {
        this.employeePayrollList = list;
    }
    public void writeEmployeePayrollDataToFile() {
        List<String> lines = new ArrayList<>();
        for (EmployeePayrollData emp : employeePayrollList) {
            lines.add(emp.toString());
        }

        try {
            Files.write(Paths.get(FILE_NAME), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countEntries() {
        try {
            return Files.lines(Paths.get(FILE_NAME)).count();
        } catch (IOException e) {
            return 0;
        }
    }
    public void printEmployeePayrollData() {
        try {
            Files.lines(Paths.get(FILE_NAME)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<EmployeePayrollData> list = Arrays.asList(
                new EmployeePayrollData(1, "Hru", 100000),
                new EmployeePayrollData(2, "Risha", 200000),
                new EmployeePayrollData(3, "Sree", 300000)
        );

        EmployeePayrollService service = new EmployeePayrollService(list);
        service.writeEmployeePayrollDataToFile();
        System.out.println("Entries count: " + service.countEntries());
        service.printEmployeePayrollData();
        System.out.println("Entries count: " + service.countEntries());

    }
}