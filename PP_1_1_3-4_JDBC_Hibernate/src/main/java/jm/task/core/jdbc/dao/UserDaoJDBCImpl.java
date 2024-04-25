package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Util util = new Util();
    private Connection connection = util.getConnection();

    private final String creatUserTable =
            "CREATE TABLE mydbtese" + "(ID INT AUTO_INCREMENT, " +
                    "name VARCHAR(45), " +
                    "lastName VARCHAR(45), " +
                    "age INT," +
                    "PRIMARY KEY (ID))";


    public void createUsersTable() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(creatUserTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> dropUsersTable() {
        try {
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "DROP TABLE IF EXISTS %s";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO mydbtest(name, lastName, age) VALUES(?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        public void removeUserById ( long id){
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            PreparedStatement preparedStatement = null;
            String sql = "DELETE FROM mydbtes WHERE_ID=?";


            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }


        public List<User> getAllUsers () {
            List<User> userList = new ArrayList<>();
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String sql = "SELECT ID, NAME, LASTNAME, AGE FROM mydbtest";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("ID"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return userList;
        }


        public void cleanUsersTable () {
            String sql = "TRUNCATE TABLE mydbtest";

            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
