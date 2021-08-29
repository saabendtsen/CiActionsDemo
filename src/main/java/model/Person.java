package model;

public class Person {
    private int id;
    private String name;
    private String pw;
    private String phone;
    private String address;
    private int age;

    public Person(int id, String fname, String lname, String pw, String phone, String address) {
        this.id = id;
        this.name = fname + " " + lname;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
