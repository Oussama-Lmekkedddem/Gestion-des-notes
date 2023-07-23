package com.example.login.Model.entité;




import com.example.login.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElementDAO {
    private DatabaseConnection dbConnection;

    public ElementDAO() {
        dbConnection = DatabaseConnection.getInstance();
    }

    public void create(Element element) {
        String query = "INSERT INTO elements (idMatiere, nom, code, currentCoefficient, moduleId) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setLong(1, element.getIdMatiere());
            statement.setString(2, element.getNom());
            statement.setString(3, element.getCode());
            statement.setDouble(4, element.getCurrentCoefficient());
            statement.setLong(5, element.getModule().getIdModule());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Element getById(Long id) {
        String query = "SELECT * FROM elements WHERE idMatiere = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createElementFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Element> getAll() {
        List<Element> elements = new ArrayList<>();
        String query = "SELECT * FROM elements";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Element element = createElementFromResultSet(resultSet);
                elements.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elements;
    }

    public void update(Element element) {
        String query = "UPDATE elements SET nom = ?, code = ?, currentCoefficient = ?, moduleId = ? " +
                "WHERE idMatiere = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setString(1, element.getNom());
            statement.setString(2, element.getCode());
            statement.setDouble(3, element.getCurrentCoefficient());
            statement.setLong(4, element.getModule().getIdModule());
            statement.setLong(5, element.getIdMatiere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Element element) {
        String query = "DELETE FROM elements WHERE idMatiere = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setLong(1, element.getIdMatiere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Element createElementFromResultSet(ResultSet resultSet) throws SQLException {
        Long idMatiere = resultSet.getLong("idMatiere");
        String nom = resultSet.getString("nom");
        String code = resultSet.getString("code");
        double currentCoefficient = resultSet.getDouble("currentCoefficient");
        Long moduleId = resultSet.getLong("moduleId");
        // Récupérer le module correspondant depuis la base de données (vous devez avoir un ModuleDAO)
        ModuleDAO moduleDAO = new ModuleDAO();
        Module module = moduleDAO.getById(moduleId);
        // Construire et retourner l'objet Element
        return new Element.ElementBuilder()
                .withIdMatiere(idMatiere)
                .withNom(nom)
                .withCode(code)
                .withCurrentCoefficient(currentCoefficient)
                .withModule(module)
                .build();
    }
}
