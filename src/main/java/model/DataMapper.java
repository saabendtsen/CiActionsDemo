package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataMapper {

    public static Person createPerson(Person p) {

        try {
            Connection con = DBconnector.connection();
            String SQL = "INSERT INTO Person (name, age) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            p.setId(id);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return p;

    }

    public static List<Person> getAllPersons() {
        List<Person> persons = new ArrayList();
        try {
            Connection con = DBconnector.connection();
            String SQL = "SELECT * FROM usertable";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String pw = rs.getString("pw");
                String phone = rs.getString("phone");
                String address = rs.getString("address");


                persons.add(new Person(id, fname, lname, pw, phone, address));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return persons;
    }

    public static void main(String[] args) {
        //createPerson(new Person("Hansen", 22));
        deleteTable();
       createTable();
       fillTable();
       deleteTable();

    }

    public static void createTable(){
        try{
            Connection con = DBconnector.connection();

            String SQL = "create table if not exists  startcode.usertable(\n" +
                    "id int primary key auto_increment,\n" +
                    "fname varchar(30),\n" +
                    "lname varchar(30),\n" +
                    "pw varchar(50),\n" +
                    "phone varchar(11),\n" +
                    "address varchar(50)\n" +
                    ");\n";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeUpdate();

        } catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static void fillTable(){
        try{
            Connection con = DBconnector.connection();
            String SQL = "INSERT INTO `usertable` VALUES " +
                    "(1,'Henning','Dahl','sdfw333','+4540949403','Rolighedsvej 22, 2100 Kbh Ø')," +
                    "(2,'Hannah','Dinesen','fsdkk653kk','+4540546754','Rolighedsvej 67, 2100 Kbh Ø')," +
                    "(3,'Amin','Kotchic','lkjnnn443','+4540345469','Rolighedsvej 90, 2100 Kbh Ø')," +
                    "(4,'Harun','Dupsmith','kothis55','+4540677667','Rolighedsvej 104, 2100 Kbh Ø');\n";;

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeUpdate();


        } catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static void deleteTable(){
        try{
            Connection con = DBconnector.connection();
            String SQL = "DROP TABLE IF EXISTS `usertable`;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.executeUpdate();

        } catch(SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static boolean isUnderAge(Person p) {
        if (p.getAge() < 18) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<String> ListAllByName() {
        List<Person> persons = getAllPersons();
        ArrayList<String> names = new ArrayList<>();

        for (Person p : persons) {
            names.add(p.getName());
            System.out.println(p.getName());
        }
        return names;
    }

    public static void editDetails(Person personExpected, int nextInt) {
        System.out.println(String.valueOf(nextInt));
        try {
            Connection con = DBconnector.connection();
            String SQL = "UPDATE usertable SET phone = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, String.valueOf(nextInt));
            ps.setInt(2, personExpected.getId());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

