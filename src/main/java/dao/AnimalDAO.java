package dao;

import model.Animal;
import model.enums.Sex;
import model.enums.Type;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalDAO {

    private final String GET_ALL_ANIMAL = "select * from animal";
    private final String CREATE_TABLE = "create table animal (id bigint auto_increment, name varchar (100), type varchar(50), gender varchar(20)," +
            "date_birth date, cage_id bigint, FOREIGN KEY (cage_id) REFERENCES cage(id), CONSTRAINT unique_animal UNIQUE(name, date_birth))";
    private String CREATE_ANIMAL = "insert into animal (name, type, gender, date_birth) values (?, ?, ?, ?)";
    private String DELETE_ANIMAL = "delete from animal where id = ?";
    private String UPDATE_ANIMAL = "update animal set name = ?, type = ?, gender = ?, date_birth = ? where id = ?";

    public static void main(String[] args) {
        try {
            Animal animal = new Animal();
            animal.setBirthDate(LocalDate.now());
            //animal.setName("First animal");
            animal.setName("Second_ name");
            animal.setSex(Sex.FEMALE);
            animal.setType(Type.MAMMAL);
            new AnimalDAO().createAnimalTable();
            //System.out.println(new AnimalDAO().add(animal));
            //System.out.println(new AnimalDAO().all());
            //System.out.println("Delete " + new AnimalDAO().delete(3L));
            //animal = new AnimalDAO().all().get(0);
            animal.setName("Other name");
            //new AnimalDAO().update(animal);
            new CageDAO().getById(1L);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createAnimalTable() throws SQLException {
        try (Connection con = DataSource.getConnection();
             Statement pst = con.createStatement()) {
            return pst.execute(CREATE_TABLE);
        }
    }

    public Animal add(Animal animal) throws SQLException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(CREATE_ANIMAL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getType().toString());
            preparedStatement.setString(3, animal.getSex().toString());
            preparedStatement.setObject(4, animal.getBirthDate());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                animal.setId(rs.getInt(1));
            }
            return animal;
        }
    }

    public List<Animal> all() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ANIMAL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Animal animal = new Animal();
                animal.setName(resultSet.getString("name"));
                animal.setSex(Sex.valueOf(resultSet.getString("gender")));
                animal.setType(Type.valueOf(resultSet.getString("type")));
                animal.setBirthDate(resultSet.getDate("date_birth").toLocalDate());
                animal.setId(resultSet.getLong("id"));
                animals.add(animal);
            }
        }
        return animals;
    }

    public boolean delete(Long id) throws SQLException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_ANIMAL)) {
            pst.setLong(1, id);
            return pst.executeUpdate() != 0;
        }
    }

    public Animal update(Animal animal) throws SQLException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ANIMAL)) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getType().toString());
            preparedStatement.setString(3, animal.getSex().toString());
            preparedStatement.setObject(4, animal.getBirthDate());
            preparedStatement.setLong(5, animal.getId());
            preparedStatement.executeUpdate();
            return animal;
        }
    }

    public Optional<Animal> getById(Long id){
        return Optional.empty();
    }
}
