package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgTest {
    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "root";
    private final String SAVE_USER = "insert into addresses values (default, ?) returning id";

    public static void main(String[] args) throws SQLException {
        PgTest pgTest = new PgTest();

        pgTest.save(new User("devil", "truda", "3434345"));

//        System.out.println(pgTest.getAll());
//        System.out.println(pgTest.getById(5));
//        pgTest.save(new User("Alfred"));

//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
//        Statement statement = connection.createStatement();
//        String query = "insert into users values(default, 'Denis', 3, 3)";
//        String query1 = "delete from users where id=11";
//        int i = statement.executeUpdate(query1);
//        System.out.println(i);


//        ResultSet sle = statement.executeQuery("select * from users");
//        List<User> userList = new ArrayList<>();
//        while (sle.next()) {
//            int anInt = sle.getInt(1);
//            String string = sle.getString(2);
//            userList.add(new User(anInt, string));
//        }
//        System.out.println(userList);
    }

    public User getById(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
        preparedStatement.setInt(1, id);
        ResultSet sle = preparedStatement.executeQuery();
//        while (sle.next()){
//            if (sle.getInt(1) == id){
//                return new User(sle.getInt(1), sle.getString(2));
//            }
//        }
        return null;
    }

    public List<User> getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet sle = statement.executeQuery("select * from users");
        List<User> userList = new ArrayList<>();
        while (sle.next()) {
            int anInt = sle.getInt(1);
            String string = sle.getString(2);
//            userList.add(new User(anInt, string));
        }
        return userList;
    }

    public boolean save(User user) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        connection.setAutoCommit(false);
//        connection.setTransactionIsolation(0);

        PreparedStatement address = connection.prepareStatement(SAVE_USER);
        address.setString(1, user.getAddress());
        ResultSet resultSet = address.executeQuery();
        resultSet.next();
        int addressId = resultSet.getInt(1);

        PreparedStatement telephone = connection.prepareStatement("insert into telephones values(default, ?) returning id");
        telephone.setString(1, user.getTelephone());
        ResultSet resultSet1 = telephone.executeQuery();
        resultSet1.next();
        int telephoneId = resultSet1.getInt(1);


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values(default, ?, ?, ?)");
            if (user.getName().equals("devil")) throw new SQLException();
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, addressId);
            preparedStatement.setInt(3, telephoneId);
            return preparedStatement.execute();
        } catch (SQLException e) {
            connection.rollback();
        }
        connection.commit();
        return false;
    }

    public static class User {
        private int id;
        private String name;
        private String address;
        private String telephone;

        public User(int id, String name, String address, String telephone) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.telephone = telephone;
        }

        public User(String name, String address, String telephone) {
            this.name = name;
            this.address = address;
            this.telephone = telephone;
        }

        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
