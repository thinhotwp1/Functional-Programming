import entity.Person;
import entity.Student;
import entity.Teacher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

public class FuntionalProgramming {
    /**
     * <p>Những hàm như Consumer, BiConsumer nhận 1 hoặc 2 tham số và sử dụng những tham số đó</p>
     * <p>Hàm Supplier<T> không nhận tham số và trả về giá trị T qua method get()</p>
     * <p>Hàm Function<T,R> và BiFunction<T, U, R> nhận tham số đầu vào là T,U và trả về object R</p>
     */
    public static void main(String[] args) {
        useConsumer();

        useBiConsumer();

        useSupplier();

        useFunction();

        useBiFunction();

        functionConstructor();
    }

    private static void useConsumer() {
        // Use Consumer
        Consumer<Person> consumer = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                // Do something with person input
                System.out.println(person.getName());
            }
        };
        consumer.accept(new Person("personName"));
        System.out.println("------------Consumer End---------");
    }

    private static void useBiConsumer() {
        BiConsumer<String, Person> biConsumer = new BiConsumer<String, Person>() {
            @Override
            public void accept(String s, Person p) {
                p.setName(s);
                System.out.println(p.getName());
            }
        };
        biConsumer.accept("personName", new Person());
        System.out.println("------------BiConsumer End---------");
    }

    private static void useSupplier() {
        // Supplier không nhận tham số đầu vào, chỉ khởi tạo và trả về một object,
        // có dạng Supllier<T>, cung cấp method get() để lấy giá trị <T>
        Supplier<Person> personSupplier = () -> createPerson("name");
        System.out.println(personSupplier.get());
        System.out.println("------------Supplier End---------");
    }

    private static void useFunction() {
        // Function nhận 2 param và trả về object tương ứng,
        // ở đây trả về Object Person dựa vào hàm createPerson và 2 params truyền vào là 2 string 
        Function<String, Person> personFunction = FuntionalProgramming::createPerson;
        Person obj = personFunction.apply("name");
        System.out.println(obj.getName());
        System.out.println("------------Function End---------");
    }

    private static void useBiFunction() {
        // BiFunction nhận 2 param và trả về object tương ứng,
        // ở đây trả về Object Person dựa vào hàm createPerson và 2 params truyền vào là 2 string
        BiFunction<String, String, Person> personBiFunction = FuntionalProgramming::createPerson;
        Person obj = personBiFunction.apply("param1", "param2");
        System.out.println(obj.getName());
        System.out.println("------------BiFunction End---------");
    }

    private static Person createPerson(String s) {
        return new Person(s);
    }

    private static Person createPerson(String s, String s1) {
        return new Person(s, s1);
    }

    private static void functionConstructor() {
        // Sử dụng constructor method reference để tạo mới một đối tượng,
        // phù hợp khi cần tạo nhiều đối tượng từ danh sách
        Map<String, String> listData = new HashMap<>();
        listData.put("Adam", "Student");
        listData.put("John Wick", "Teacher");
        listData.put("Eva", "Engineer");

        // Sử dụng lambda sẽ truyền được nhiều hơn 2 tham số, dùng Consumber hay BiComsumer 
        // chỉ truyền được 1-2 tham số, recomment sử dụng lambda tốt nhất 
        // Use lambda
        listData.forEach((s1, s2) -> {
            // Gọi Function để tạo contructor, Function chỉ nhận một param duy nhất, 
            // nếu muốn nhận 2 param thì sử dụng BiFunction
            Function<String, Person> constructor;
            // Tạo object
            Person obj;
            // Ép kiểu object
            switch (s2.toLowerCase()) {
                case "student" -> constructor = Student::new;
                case "teacher" -> constructor = Teacher::new;
                default -> constructor = Person::new;
            }
            // Gán dữ liệu cho object
            obj = constructor.apply(s1);
            // Do something with object
            System.out.println("My name is " + obj.getName() + ", Im an " + obj.getClass().getSimpleName());
        });

        System.out.println("------------FunctionConstructor End---------");
    }


}
