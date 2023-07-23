package com.example.login.Model.entité;


import com.example.login.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NiveauDAO {
    private DatabaseConnection databaseConnection;

    public NiveauDAO() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public void createNiveau(Niveau niveau) {
        String query = "INSERT INTO niveau (alias, titre) VALUES (?, ?)";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setString(1, niveau.getAlias());
            statement.setString(2, niveau.getTitre());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Niveau getNiveauById(Long id) {
        String query = "SELECT * FROM niveau WHERE idNiveau = ?";
        Niveau niveau = null;

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                niveau = buildNiveauFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return niveau;
    }

    public List<Niveau> getAllNiveaux() {
        String query = "SELECT * FROM niveau";
        List<Niveau> niveaux = new ArrayList<>();

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Niveau niveau = buildNiveauFromResultSet(resultSet);
                niveaux.add(niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return niveaux;
    }

    public void updateNiveau(Niveau niveau) {
        String query = "UPDATE niveau SET alias = ?, titre = ? WHERE idNiveau = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setString(1, niveau.getAlias());
            statement.setString(2, niveau.getTitre());
            statement.setLong(3, niveau.getIdNiveau());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNiveau(Niveau niveau) {
        String query = "DELETE FROM niveau WHERE idNiveau = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setLong(1, niveau.getIdNiveau());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Niveau buildNiveauFromResultSet(ResultSet resultSet) throws SQLException {
        int idNiveau = resultSet.getInt("idNiveau");
        String alias = resultSet.getString("alias");
        String titre = resultSet.getString("titre");

        Niveau.NiveauBuilder builder = new Niveau.NiveauBuilder()
                .withIdNiveau(idNiveau)
                .withAlias(alias)
                .withTitre(titre);

        return builder.build();
    }
}

