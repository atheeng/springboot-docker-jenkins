package threadSington;

import com.arun.mongodbcrud.model.Employee;

import java.util.List;

public class ThreadDemo extends Thread {
    private List<Employee> employees;

    public ThreadDemo(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void run() {
            for(Employee employee:employees){
                System.out.println(employee.getName()+":"+employee.getSalary());
            }
    }
    public static void main(String[] args) {
      List<Employee> employeeList= List.of(
              new Employee("1","Arun","Tokyo",50000.0),
              new Employee("2","Ram","Osaka",60000.0),
              new Employee("3","Hari","Kyoto",70000.0)
      );
        ThreadDemo threadDemo = new ThreadDemo(employeeList);
        threadDemo.start();
    }


}
