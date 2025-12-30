import java.util.*;

public class EmployeePayrollService {

    private List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService() {
        employeePayrollList = new ArrayList<>();
    }

    public void readEmployeePayrollData(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Employee Name: ");
        String name = sc.next();

        System.out.print("Enter Employee Salary: ");
        double salary = sc.nextDouble();

        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public void writeEmployeePayrollData() {
        System.out.println("Employee Payroll Data:");
        for (EmployeePayrollData emp : employeePayrollList) {
            System.out.println(emp);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeePayrollService service = new EmployeePayrollService();
        service.readEmployeePayrollData(sc);
        service.writeEmployeePayrollData();
    }
}
