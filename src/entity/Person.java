package entity;

// Class đối tượng
public class Person {
    private String name;

    public Person(String name) {
        setName(name);
    }

    public Person() {
    }

    public Person(int i) {
    }

    public Person(String s, String s1) {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
