package dao;

import model.Animal;
import model.Cage;
import model.enums.Sex;
import model.enums.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CageDAO {

    private final String CREATE_TABLE = "create table cage (id bigint auto_increment, number varchar (100), " +
            "capacity int, CONSTRAINT unique_number UNIQUE(number))";
    private final String CAGE_WITH_ANIMAL = "select cage.*, animal.* from cage join animal on cage.id = animal.cage_id where cage.id=?";


    public boolean createCageTable() throws SQLException {
        try (Connection con = DataSource.getConnection();
             Statement pst = con.createStatement()) {
            return pst.execute(CREATE_TABLE);
        }
    }

    public Cage getById(Long id) throws SQLException {
        Cage cage = new Cage();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CAGE_WITH_ANIMAL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setLong(1, id);
            List<Animal> animals = new ArrayList<>();
            while (resultSet.next()) {

                Animal animal = new Animal();
                animal.setName(resultSet.getString("animal.name"));
//                animal.setSex(Sex.valueOf(resultSet.getString("gender")));
//                animal.setType(Type.valueOf(resultSet.getString("type")));
//                animal.setBirthDate(resultSet.getDate("date_birth").toLocalDate());
//                animal.setId(resultSet.getLong("id"));
                animals.add(animal);

                cage.setCapacity(resultSet.getInt("cage.capacity"));

            }
            cage.setAnimals(animals);
        }
        return cage;
    }
}
