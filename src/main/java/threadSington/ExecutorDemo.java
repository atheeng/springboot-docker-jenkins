package threadSington;

import com.arun.mongodbcrud.model.Employee;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo implements Runnable {
    Employee employee;

    public ExecutorDemo(Employee employee) {
        this.employee = employee;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(employee.getName() + ":" + employee.getSalary());
    }

    public static void main(String[] args) {
        List<Employee> employeeList = List.of(
                new Employee("1", "Arun", "Tokyo", 50000.0),
                new Employee("2", "Ram", "Osaka", 60000.0),
                new Employee("3", "Hari", "Kyoto", 70000.0)
        );
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (Employee employee : employeeList) {
            executorService.submit(new ExecutorDemo(employee));
        }
    }
}
