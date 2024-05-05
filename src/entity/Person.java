package entity;

// Class đối tượng
public class Person {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name) {
        setName(name);
    }

    public Person() {
    }

    public Person(int i) {
    }

    public Person(String s, String s1) {
        setName(s);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
