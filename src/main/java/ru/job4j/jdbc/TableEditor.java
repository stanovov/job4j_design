package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD %s %s;",
                tableName,
                columnName,
                type
        );
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName
        );
        execute(sql);
    }

    private void execute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Properties prop = new Properties();
        try (FileInputStream fio = new FileInputStream("app.properties")) {
            prop.load(fio);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tableName = "table_test";
        try (TableEditor editor = new TableEditor(prop)) {
            System.out.printf("Создание таблицы \"%s\"%n", tableName);
            editor.createTable(tableName);
            System.out.println(getTableScheme(editor.connection, tableName));

            System.out.printf("Добавление столбца \"%s\" в таблицу \"%s\"%n", "id", tableName);
            editor.addColumn(tableName, "id", "serial primary key");
            System.out.println(getTableScheme(editor.connection, tableName));

            System.out.printf("Добавление столбца \"%s\" в таблицу \"%s\"%n", "name", tableName);
            editor.addColumn(tableName, "name", "varchar(255)");
            System.out.println(getTableScheme(editor.connection, tableName));

            System.out.printf("Переименовывание столбца \"%s\" на \"%s\" в таблице \"%s\"%n",
                    "name", "new_name", tableName
            );
            editor.renameColumn(tableName, "name", "new_name");
            System.out.println(getTableScheme(editor.connection, tableName));

            System.out.printf("Удаление столбца \"%s\" в таблице \"%s\"%n", "new_name", tableName);
            editor.dropColumn(tableName, "new_name");
            System.out.println(getTableScheme(editor.connection, tableName));

            System.out.printf("Удаление таблицы \"%s\"%n", tableName);
            editor.dropTable(tableName);
        }
    }
}
