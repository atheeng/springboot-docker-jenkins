package threadSington;

import com.arun.mongodbcrud.model.Employee;

import java.util.List;

public class RunnableDemo implements Runnable {

    private List<Employee> employeeList;

    public RunnableDemo(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public void run() {
        for (Employee employee : employeeList) {
            System.out.println(employee.getName() + ":" + employee.getSalary());
        }
    }
    public static void main(String[] args) {
        List<Employee> employeeList1 = List.of(
                new Employee("1","Arun","Tokyo",50000.0),
                new Employee("2","Ram","Osaka",60000.0),
                new Employee("3","Hari","Kyoto",70000.0)
        );
        RunnableDemo runnableDemo = new RunnableDemo(employeeList1);
        Thread thread = new Thread(runnableDemo);
        thread.start();
    }
}
