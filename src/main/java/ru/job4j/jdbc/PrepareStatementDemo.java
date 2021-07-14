package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {

    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "753951";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        boolean result = true;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        PrepareStatementDemo demo = new PrepareStatementDemo();
        City tver = demo.insert(new City(0, "Tver'", 407_000));
        City moscow = demo.insert(new City(0, "Moscow", 11_920_000));
        City stPetersburg = demo.insert(new City(0, "St. Petersburg", 4_991_000));
        demo.delete(tver.getId());
        stPetersburg.setPopulation(5_000_000);
        demo.update(stPetersburg);
        List<City> cities = demo.findAll();
        for (City city : cities) {
            System.out.println(city);
        }
    }
}
