import entity.Person;
import entity.Student;
import entity.Teacher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class BuiltInFunctional {
    /**
     * <p>Những hàm như Consumer, BiConsumer nhận 1 hoặc 2 tham số và sử dụng những tham số đó</p>
     * <p>Hàm Supplier<T> không nhận tham số và trả về giá trị T qua method get()</p>
     * <p>Hàm Function<T,R> và BiFunction<T, U, R> nhận tham số đầu vào là T,U và trả về object R</p>
     */
    public static void main(String[] args) {
//        useConsumer();
//
//        useBiConsumer();
//
//        useSupplier();
//
//        useFunction();
//
//        useBiFunction();
//
//        useLambda();
//
//        useUnaryOperator();
//
//        useBinaryOperator();
//
//        usePredicate();
//
//        useBiPredicate();
//
//        checkingFunctionalInterfaces();

        useParallelStream();


    }

    /**
     * ParallelStream dùng để tạo stream chạy tiến trình song song
     */
    private static void useParallelStream() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        int tich = list.parallelStream().reduce(1, (integer, integer2) -> integer * integer2);
        System.out.println("Tích các số: " + tich);
    }

    /**
     * BiPredicate dùng để tạo hàm so sánh với 2 tham số và trả về một boolean
     */
    private static void useBiPredicate() {
        BiPredicate<Integer, Person> biPredicate = new BiPredicate<Integer, Person>() {
            @Override
            public boolean test(Integer integer, Person person) {
                return integer > person.getAge();
            }
        };
    }

    /**
     * Predicate dùng để tạo hàm so sánh với 1 tham số và trả về một boolean
     */
    private static void usePredicate() {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 10;
            }
        };

        System.out.println("Predicate with 10: " + predicate.test(20));
    }

    private static void checkingFunctionalInterfaces() {
        // Kiểm tra xem interface Predicate có phải là functional interface hay không
        boolean isFunctional = isFunctionalInterface(Predicate.class);
        System.out.println("Is Predicate a functional interface? " + isFunctional);
    }

    public static boolean isFunctionalInterface(Class<?> clazz) {
        // Kiểm tra xem interface có chú thích @FunctionalInterface hay không
        if (clazz.isAnnotationPresent(FunctionalInterface.class)) {
            // Kiểm tra xem interface có chỉ có một phương thức trừu tượng không
            return clazz.getDeclaredMethods().length == 1;
        }
        return false;
    }

    private static void useBinaryOperator() {
        BinaryOperator<Integer> sum = (x, y) -> x + y;

        int result = sum.apply(5, 3); // Kết quả là 5 + 3 = 8
        System.out.println("Sum of 5 and 3: " + result);
    }

    /**
     * <p>
     * UnaryOperator là một functional interface đại diện cho một phép toán được thực hiện trên một đối tượng và trả về kết quả cùng kiểu dữ liệu với đối tượng đầu vào.
     * </P>
     */
    private static void useUnaryOperator() {
        UnaryOperator<Integer> unaryOperator = new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer i) {
                return i * i;
            }
        };
        System.out.println("UnaryOperator: " + unaryOperator.apply(5));
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
        Function<String, Person> personFunction = BuiltInFunctional::createPerson;
        Person obj = personFunction.apply("name");
        System.out.println(obj.getName());
        System.out.println("------------Function End---------");
    }

    private static void useBiFunction() {
        // BiFunction nhận 2 param và trả về object tương ứng,
        // ở đây trả về Object Person dựa vào hàm createPerson và 2 params truyền vào là 2 string
        BiFunction<String, String, Person> personBiFunction = BuiltInFunctional::createPerson;
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

    private static void useLambda() {
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
