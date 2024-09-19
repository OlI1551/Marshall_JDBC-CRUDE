import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private static String INSERT_STUDENT = "INSERT INTO students(name, surname, course_name) VALUES (?, ?, ?);";
    private static String UPDATE_STUDENT = "UPDATE students SET course_name=? WHERE id=?;";
    private static String DELETE_STUDENT = "DELETE FROM students WHERE id = ?;";

    // 1 метод CRUD - READ
    public static List<Student> getStudentData(String query) {
        List<Student> studentsList = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
//             Statement; // нельзя добавить параметры
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//             CallableStatement; // можно получить значения из хранимых процедур
//            preparedStatement.execute(); // отправили запрос, но этот метод не возвращает никаких значений
            ResultSet rs = preparedStatement.executeQuery(); // вернет значение, которое мы запросили, в экземпляр класса ResultSet из JDBC API для хранения таких результатов

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                studentsList.add(new Student(id, name, surname, course_name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    // 2 метод CRUD - CREATE
    public static List<Student> saveStudent(Student student) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCourse_name());
            preparedStatement.executeUpdate(); // нужно передать значения, чтобы они там обновились

            // это делаем, чтобы проверить, внесли ли мы нового студента
            PreparedStatement allStudents = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudents.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                students.add(new Student(id, name, surname, course_name));
            }  // это делаем, чтобы проверить, внесли ли мы нового студента
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 3 метод CRUD - UPDATE
    public static List<Student> updateStudent(int studentId, String courseName) {
        List<Student> updateStudents = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            preparedStatement.setString(1, courseName);
            preparedStatement.setInt(2, studentId);
            preparedStatement.executeUpdate(); // нужно передать значения, чтобы они там поменялись

            // это делаем, чтобы проверить, внесли ли мы изменения
            PreparedStatement allStudents = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudents.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                updateStudents.add(new Student(id, name, surname, course_name));
            }  // это делаем, чтобы проверить, внесли ли мы изменения
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateStudents;
    }

    // 4 метод CRUD - DELETE
    public static List<Student> deleteStudent(int studentId) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate(); // нужно передать значения, чтобы они там удалились

            // это делаем, чтобы проверить, внесли ли мы изменения
            PreparedStatement allStudents = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = allStudents.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String course_name = rs.getString("course_name");

                students.add(new Student(id, name, surname, course_name));
            }  // это делаем, чтобы проверить, внесли ли мы изменения


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;

    }
}
