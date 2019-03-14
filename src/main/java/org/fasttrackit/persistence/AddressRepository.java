package org.fasttrackit.persistence;

import org.fasttrackit.domain.PhoneAddress;
import org.fasttrackit.transfer.AddressWithId;
import org.fasttrackit.transfer.FirstNameLastNameAddress;
import org.fasttrackit.transfer.IdAddress;
import org.fasttrackit.transfer.SaveAddressRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository {

    public void createAddress(SaveAddressRequest request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "INSERT INTO phone_address (firstname, lastname, number) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getFirstname());
            preparedStatement.setString(2, request.getLastname());
            preparedStatement.setInt(3, request.getNumber());

            preparedStatement.executeUpdate();
        }
    }

    public List<PhoneAddress> getAddress() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            String query = "SELECT * FROM phone_address ORDER BY id ASC";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<PhoneAddress> response = new ArrayList<>();

            while (resultSet.next()) {
                PhoneAddress phoneAddress = new PhoneAddress();
                phoneAddress.setId(resultSet.getLong("id"));
                phoneAddress.setFirstname(resultSet.getString("firstname"));
                phoneAddress.setLastname(resultSet.getString("lastname"));

                response.add(phoneAddress);
            }
            return response;
        }
    }

    public void updateAddress(AddressWithId request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "UPDATE phone_address SET firstname=?, lastname=?, number=? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getFirstname());
            preparedStatement.setString(2, request.getLastname());
            preparedStatement.setInt(3, request.getNumber());
            preparedStatement.setLong(4, request.getId());

            preparedStatement.executeUpdate();
            System.out.println("*Update successful.");

        } catch (Exception e) {
            System.out.println("*Invalid update");
        }
    }

    public void deleteAddress(IdAddress request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "DELETE FROM phone_address WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setLong(1, request.getId());

            preparedStatement.executeUpdate();
            System.out.println("*Delete successful.");

        } catch (Exception e) {
            System.out.println("*Invalid delete operation");
        }
    }

    public void deleteMultipleAddress(IdAddress request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "DELETE FROM phone_address ORDER BY id DESC limit ?";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setLong(1, request.getId());

            preparedStatement.executeUpdate();
            System.out.println("*Delete successful.");

        } catch (Exception e) {
            System.out.println("*Invalid delete operation");
        }
    }

    public List<PhoneAddress> findAddress(FirstNameLastNameAddress request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "SELECT * FROM phone_address WHERE firstname=? OR lastname=?";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getFirstname());
            preparedStatement.setString(2, request.getLastname());

            ResultSet resultSet = preparedStatement.executeQuery(insertSql);
            List<PhoneAddress> response = new ArrayList<>();
            while (resultSet.next()) {
                PhoneAddress phoneAddress = new PhoneAddress();
                phoneAddress.setFirstname(resultSet.getString("firstname"));
                phoneAddress.setLastname(resultSet.getString("lastname"));
                phoneAddress.setNumber(resultSet.getInt("number"));
                phoneAddress.setId(resultSet.getLong("id"));

                response.add(phoneAddress);
            }
            return response;
        }
    }



}
