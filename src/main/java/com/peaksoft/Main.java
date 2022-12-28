package com.peaksoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args){

//creatTable();
        getCount();
        addMer(1,"Jack");
        addMer(2,"Aida");
        addMer(3,"Bili");
        addMer(4,"Dili");
        addMer(5,"Tom");
        addCity(1,"Bishkek");
        addCity(2,"Moscow");
        addCity(3,"London");
        addCity(4,"Paris");
        addCity(5,"Berlin");
        addCountry(1,"Kyrgyzstan");
        addCountry(2,"Russian");
        addCountry(3,"Korea");
        addCountry(4,"URL");
        addCountry(5,"Japan");
        getAllCities();
        getAllCountry();
        getAllMer();
        getCountryById(1);
        getCityById(2);
        getMerById(3);

    }
    public static void creatTable() {
        String SQL1 = "CREATE TABLE mers(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(128) NOT NULL);";

        String SQL2 = "CREATE TABLE   cities(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(128) NOT NULL), " +
                "mers_id INT REFERENCES mers(id);";

        String SQL3 = "CREATE TABLE county(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(128) NOT NULL)," +
                "city_id INT REFERENCES cities(id); ";

        try (Connection connection = DB.connection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL1);
            statement.executeUpdate(SQL2);
            statement.executeUpdate(SQL3);
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static int getCount() {
        String SQL1 = "SELECT count(*) FROM ";
        int count1 = 0;
        try (Connection conn = DB.connection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL1)) {
            resultSet.next();
            count1 = resultSet.getInt(1);
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return count1;
    }

    public static void addCity(int id, String name) {
        String SQL = "INSERT INTO cities(id,name) VALUES(?,?);";
        try (Connection conn = DB.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCountry(int id, String name) {
        String SQL = "INSERT INTO country(id,name) VALUES(?,?);";
        try (Connection conn = DB.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addMer(int id, String name) {
        String SQL = "INSERT INTO country(id,name) VALUES(?,?);";
        try (Connection conn = DB.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Country> getAllCountry(){
        String SQL = "SELECT * FROM country";
        List<Country> list = new ArrayList<>();
        try(Connection conn = DB.connection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()){
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                country.setCity((City) resultSet.getObject(country.getCity().getId()));
                list.add(country);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public static List<City> getAllCities(){
        String SQL = "SELECT * FROM city";
        List<City> list = new ArrayList<>();
        try(Connection conn = DB.connection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()){
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));

                list.add(city);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public static List<Mer> getAllMer(){
        String SQL = "SELECT * FROM mers";
        List<Mer> list = new ArrayList<>();
        try(Connection conn = DB.connection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()){
                Mer mer = new Mer();
                mer.setId(resultSet.getInt("id"));
                mer.setName(resultSet.getString("name"));

                list.add(mer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public static City getCityById(int id){
        String SQL = "SELECT * FROM cities WHERE id = ?";
        City city = new City();
        try(Connection conn = DB.connection();
        PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1,id);
            ResultSet set= statement.executeQuery();
            if(set.next()){
                city.setName(set.getString("name"));
                city.setId(set.getInt("id"));
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        return city;
    }
    public static Country getCountryById(int id){
        String SQL = "SELECT * FROM country WHERE id = ?";
        Country country = new Country();
        try(Connection conn = DB.connection();
        PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1,id);
            ResultSet set= statement.executeQuery();
            if(set.next()){
                country.setName(set.getString("name"));
                country.setId(set.getInt("id"));
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        return country;
    }
    public static Mer getMerById(int id){
        String SQL = "SELECT * FROM mers WHERE id = ?";
        Mer mer = new Mer();
        try(Connection conn = DB.connection();
        PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1,id);
            ResultSet set= statement.executeQuery();
            if(set.next()){
                mer.setName(set.getString("name"));
                mer.setId(set.getInt("id"));
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        return mer;
    }
}
