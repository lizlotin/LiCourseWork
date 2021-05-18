package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface DAO_Tour<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://LiCourseWork.db");
        return result;
    }

    T create(T entity);

    List<T> findAll();

    List<Tour> foundForId(int id);
    List<Tour> foundForName(String name);
}

interface DAO_Id<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://LiCourseWork.db");
        return result;
    }

    T create(T entity);

    List<SaveId> find();

    boolean delete();
}

interface DAO_Request<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://LiCourseWork.db");
        return result;
    }
    boolean update(T entity);
    T create(T entity);

    List<T> findAll();
}

interface DAO_Admin<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://LiCourseWork.db");
        return result;
    }

    List<T> findAll();
}

class CLDAO_Admin implements DAO_Admin<Admin> {
    Connection connect;
    @Override
    public List<Admin> findAll() {
        List<Admin> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM Admins";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tempLogin = resultSet.getString(1);
                String tempPassword = resultSet.getString(2);
                Admin admin = new Admin(tempLogin, tempPassword);
                result.add(admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
class CLDAO_Tour implements  DAO_Tour<Tour> {
    Connection connect;
    @Override
    public Tour create(Tour entity) {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO Tours (name,fromDate,toDate,price) VALUES ( ?,?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getFromDate());
            preparedStatement.setString(3, entity.getToDate());
            preparedStatement.setInt(4, entity.getPrice());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.setString(6,entity.getUrl());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(5);
            entity.setId(key);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<Tour> findAll() {
        List<Tour> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM Tours";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String fromDate = resultSet.getString(2);
                String toDate = resultSet.getString(3);
                int price = resultSet.getInt(4);
                int id=resultSet.getInt(5);
                String url=resultSet.getString(6);
                Tour tour=new Tour(name,fromDate,toDate,price,url);
                tour.setId(id);
                result.add(tour);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Tour> foundForId(int id) {
        List<Tour> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM Tours WHERE  id=" + String.valueOf(id);
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String fromDate = resultSet.getString(2);
                String toDate = resultSet.getString(3);
                int price = resultSet.getInt(4);
                int tempId=resultSet.getInt(5);
                String url=resultSet.getString(6);
                Tour tour=new Tour(name,fromDate,toDate,price,tempId,url);
                tour.setId(tempId);
                result.add(tour);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Tour> foundForName(String name) {
        List<Tour> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }//Login LIKE '%" + id + "%'"
            String sql = "SELECT * FROM Tours WHERE name LIKE '%" + name+"%'";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nameTour = resultSet.getString(1);
                String fromDate = resultSet.getString(2);
                String toDate = resultSet.getString(3);
                int price = resultSet.getInt(4);
                int tempId=resultSet.getInt(5);
                String url=resultSet.getString(6);
                Tour tour=new Tour(nameTour,fromDate,toDate,price,tempId,url);
                tour.setId(tempId);
                result.add(tour);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}
class CLDAO_Request implements DAO_Request<Request> {
    Connection connect;

    @Override
    public boolean update(Request entity) {
        boolean result=false;
        try {
            if(connect==null) {

                connect=getConnection();

            }
            String sql="UPDATE Requests SET status = ? WHERE requestId=?";

            PreparedStatement prepareStatement=connect.prepareStatement(sql);
            prepareStatement.setString(1,entity.getStatus());
            prepareStatement.setInt(2,entity.getRequestId());

            result=prepareStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Request create(Request entity) {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO Requests (nameTour,price,numOfAdult,numOfChildren,clientName,phoneNumber,tourId,status) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, entity.getNameTour());
            preparedStatement.setInt(2,entity.getPrice());
            preparedStatement.setInt(3, entity.getNumOfAdult());
            preparedStatement.setInt(4, entity.getNumOfChildren());
            preparedStatement.setString(5,entity.getClientName());
            preparedStatement.setInt(6,entity.getPhoneNumber());
            preparedStatement.setInt(7,entity.getTourId());
            preparedStatement.setString(8,entity.getStatus());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<Request> findAll() {
        List<Request> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM Requests";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nameTour=resultSet.getString(1);
                int price=resultSet.getInt(2);
                int numOfAdult=resultSet.getInt(3);
                int numOfChildren=resultSet.getInt(4);
                String clientName=resultSet.getString(5);
                int phoneNumber=resultSet.getInt(6);
                int requestId=resultSet.getInt(7);
                int tourId=resultSet.getInt(8);
                String status=resultSet.getString(9);
                Request request=new Request(nameTour,price,numOfAdult,numOfChildren,clientName,phoneNumber,requestId,tourId,status);
                request.setRequestId(requestId);
                result.add(request);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
class  CLDAO_Id implements DAO_Id<SaveId> {
    Connection connect;
    @Override
    public SaveId create(SaveId entity) {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO SavedID (id) VALUES (?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<SaveId> find() {
        List<SaveId> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT *FROM SavedId";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                SaveId saveId = new SaveId(id);
                saveId.setId(id);
                result.add(saveId);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete() {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "DELETE FROM SavedId";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

