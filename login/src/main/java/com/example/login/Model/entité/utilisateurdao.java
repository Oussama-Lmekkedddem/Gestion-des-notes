package com.example.login.Model.entité;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class utilisateurdao {
    private Connection connection;

    public  utilisateurdao(Connection connection) {
        this.connection = connection;
    }

    public void ajouterUtilisateur(Utilisateurs utilisateur) {
        String query = "INSERT INTO utilisateurs (idUtilisateur, nomUtilisateur, motDePasseUtilisateur, cinUtilisateur, email, telephone, nomArabe, prenomArabe, photo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, utilisateur.getIdUtilisateur());
            statement.setString(2, utilisateur.getNomUtilisateur());
            statement.setString(3, utilisateur.getMotDePasseUtilisateur());
            statement.setString(4, utilisateur.getCinUtilisateur());
            statement.setString(5, utilisateur.getEmail());
            statement.setString(6, utilisateur.getTelephone());
            statement.setString(7, utilisateur.getNomArabe());
            statement.setString(8, utilisateur.getPrenomArabe());
            statement.setString(9, utilisateur.getPhoto());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilisateurs getUtilisateurById(int idUtilisateur) {
        String query = "SELECT * FROM utilisateurs WHERE idUtilisateur = ?";
        Utilisateurs utilisateur = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utilisateur = extractUtilisateurFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }

    public List<Utilisateurs> getAllUtilisateurs() {
        String query = "SELECT * FROM utilisateurs";
        List<Utilisateurs> utilisateursList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Utilisateurs utilisateur = extractUtilisateurFromResultSet(resultSet);
                utilisateursList.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateursList;
    }

    public void updateUtilisateur(Utilisateurs utilisateur) {
        String query = "UPDATE utilisateurs SET nomUtilisateur = ?, motDePasseUtilisateur = ?, cinUtilisateur = ?, email = ?, " +
                "telephone = ?, nomArabe = ?, prenomArabe = ?, photo = ? WHERE idUtilisateur = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getNomUtilisateur());
            statement.setString(2, utilisateur.getMotDePasseUtilisateur());
            statement.setString(3, utilisateur.getCinUtilisateur());
            statement.setString(4, utilisateur.getEmail());
            statement.setString(5, utilisateur.getTelephone());
            statement.setString(6, utilisateur.getNomArabe());
            statement.setString(7, utilisateur.getPrenomArabe());
            statement.setString(8, utilisateur.getPhoto());
            statement.setInt(9, utilisateur.getIdUtilisateur());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUtilisateur(int idUtilisateur) {
        String query = "DELETE FROM utilisateurs WHERE idUtilisateur = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Utilisateurs extractUtilisateurFromResultSet(ResultSet resultSet) throws SQLException {
        int idUtilisateur = resultSet.getInt("idUtilisateur");
        String nomUtilisateur = resultSet.getString("nomUtilisateur");
        String motDePasseUtilisateur = resultSet.getString("motDePasseUtilisateur");
        String cinUtilisateur = resultSet.getString("cinUtilisateur");
        String email = resultSet.getString("email");
        String telephone = resultSet.getString("telephone");
        String nomArabe = resultSet.getString("nomArabe");
        String prenomArabe = resultSet.getString("prenomArabe");
        String photo = resultSet.getString("photo");

        Utilisateurs utilisateur = new Utilisateurs.UtilisateurBuilder()
                .setIdUtilisateur(idUtilisateur)
                .setNomUtilisateur(nomUtilisateur)
                .setMotDePasseUtilisateur(motDePasseUtilisateur)
                .setcinUtilisateur(cinUtilisateur)
                .setEmailBuilder(email)
                .setTelephoneBuilder(telephone)
                .setnomarabeBuilder(nomArabe)
                .setprenomarabBuilder(prenomArabe)
                .setphotoBuilder(photo)
                .build();

        return utilisateur;
    }
}
