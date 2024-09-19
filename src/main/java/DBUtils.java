import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class                                                                                                                                                                                                                                                                             DBUtils {
    // Первый вариант получения переменных для соединения
    private static String dbURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'"; // путь к папке resources
    private static String dbUsername = "sa";
    private static String dbPassword = "";
    // Второй вариант - забрать переменные из конфигурационного файла
    // Удобнее работать с конфигурационным файлом и менять значения в нем, а не в коде
    // Но для простоты понимания будем использовать 1 вариант - с статическими переменными



    public static Connection getConnection() {
        // Второй вариант получения переменных для соединения
//        String dbURL = null;
//        String dbUsername = "sa";
//        String dbPassword = "";
//
//        FileInputStream fis;
//        Properties properties = new Properties();
//
//        try {
//            fis = new FileInputStream("src/main/resources/config.properties"); // указали путь к файлу относительно нашего проекта
//            properties.load(fis);
//
//            dbURL = properties.getProperty("db.host"); // забираем из файла по указанному ключу
//            // dbUsername = properties.getProperty(); // должны были бы забрать по другому ключу, если бы они были
//            // dbPassword = properties.getProperty();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return connection;
    }
}
