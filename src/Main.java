import entity.Person;
import entity.Student;
import entity.Teacher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        // Sử dụng constructor method reference để tạo mới một đối tượng,
        // phù hợp khi cần tạo nhiều đối tượng từ danh sách
        // Ví dụ:
        functionContructor();

        // Vi du mot so loai constructor method reference:
        Supplier<String> helloWorld = () -> helloWorld("Hello World");
        System.out.println(helloWorld.get());
    }

    private static void functionContructor() {
        Map<String, String> listData = new HashMap<>();
        listData.put("Adam", "Student");
        listData.put("John Wick", "Teacher");
        listData.put("Eva", "Engineer");

        listData.forEach((s1, s2) -> {
            // Gọi Function để tạo contructor
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
    }

    {
        // tham khao mot so loai function khac
        {
            BiFunction<String, String, Person> personBiFunction = Person::new;
            Person obj = personBiFunction.apply("param1", "param2");
        }
        {
            IntFunction<Person> personIntFunction = Person::new;
            Person obj = personIntFunction.apply(5);
        }
    }

    private static String helloWorld(String world){
        return world;
    }

}
