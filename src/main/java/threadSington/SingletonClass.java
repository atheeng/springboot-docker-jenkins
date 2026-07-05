package threadSington;

public class SingletonClass {
    private static SingletonClass instance;
    private SingletonClass(){}
    public static SingletonClass getInstance(){
        if(instance==null){
            instance =new SingletonClass();
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonClass instance = SingletonClass.getInstance();
        SingletonClass instance1 = SingletonClass.getInstance();
        System.out.println(instance==instance1);
    }
}
