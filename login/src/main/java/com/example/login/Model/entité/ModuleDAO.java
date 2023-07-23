package com.example.login.Model.entité;



import com.example.login.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO {
    private DatabaseConnection dbConnection;

    public ModuleDAO() {
        dbConnection = DatabaseConnection.getInstance();
    }

    public void create(Module module) {
        String query = "INSERT INTO modules (idModule, titre, code, niveauID, semesterID) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setLong(1, module.getIdModule());
            statement.setString(2, module.getTitre());
            statement.setString(3, module.getCode());
            statement.setLong(4, module.getNiveauID());
            statement.setLong(5, module.getSemesterID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Module getById(Long id) {
        String query = "SELECT * FROM modules WHERE idModule = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createModuleFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Module> getAll() {
        List<Module> modules = new ArrayList<>();
        String query = "SELECT * FROM modules";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Module module = createModuleFromResultSet(resultSet);
                modules.add(module);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    public void update(Module module) {
        String query = "UPDATE modules SET titre = ?, code = ?, niveauID = ?, semesterID = ? " +
                "WHERE idModule = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setString(1, module.getTitre());
            statement.setString(2, module.getCode());
            statement.setLong(3, module.getNiveauID());
            statement.setLong(4, module.getSemesterID());
            statement.setLong(5, module.getIdModule());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Module module) {
        String query = "DELETE FROM modules WHERE idModule = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setLong(1, module.getIdModule());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Module createModuleFromResultSet(ResultSet resultSet) throws SQLException {
        Long idModule = resultSet.getLong("idModule");
        String titre = resultSet.getString("titre");
        String code = resultSet.getString("code");
        Long niveauID = resultSet.getLong("niveauID");
        Long semesterID = resultSet.getLong("semesterID");
        // Récupérer le niveau et les éléments correspondants depuis la base de données (vous devez avoir des DAO correspondants)
        NiveauDAO niveauDAO = new NiveauDAO();
        Niveau niveau = niveauDAO.getNiveauById(niveauID);
        ElementDAO elementDAO = new ElementDAO();
        List<Element> elements = (List<Element>) elementDAO.getById(idModule);
        // Construire et retourner l'objet Module
        Module.Builder builder = new Module.Builder()
                .withIdModule(idModule)
                .withTitre(titre)
                .withCode(code)
                .withElements(elements)
                .withNiveau(niveau)
                .withSemesterID(semesterID)
                .withNiveauID(niveauID);
        return builder.build();
    }
}
