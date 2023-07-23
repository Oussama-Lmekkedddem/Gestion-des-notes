package com.example.login.Model.entité;

import com.example.login.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {
    private Connection connection;

    public EtudiantDAO() {
        // Obtention de la connexion à la base de données
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        String query = "INSERT INTO etudiant (idEtudiant, cneEtudiant, cinEtudiant, nomEtudiant, prenomEtudiant, niveauId, dateDeNaissance, inscriptionAnnuelleId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, etudiant.getIdEtudiant());
            statement.setString(2, etudiant.getCneEtudiant());
            statement.setString(3, etudiant.getCinEtudiant());
            statement.setString(4, etudiant.getNomEtudiant());
            statement.setString(5, etudiant.getPrenomEtudiant());
            statement.setInt(6, etudiant.getniveauId());
            statement.setObject(7, etudiant.getDateDeNaissance());
            statement.setInt(8, etudiant.getInscriptionAnnuelleId());

            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }



    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiant";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Etudiant etudiant = buildEtudiantFromResultSet(resultSet);
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiants;
    }

    private Etudiant buildEtudiantFromResultSet(ResultSet resultSet) throws SQLException {
        int idEtudiant = resultSet.getInt("idEtudiant");
        String cneEtudiant = resultSet.getString("cneEtudiant");
        String cinEtudiant = resultSet.getString("cinEtudiant");
        String nomEtudiant = resultSet.getString("nomEtudiant");
        String prenomEtudiant = resultSet.getString("prenomEtudiant");
        int niveauId = resultSet.getInt("niveauId");
        LocalDate dateDeNaissance = resultSet.getObject("dateDeNaissance", LocalDate.class);
        int inscriptionAnnuelleId = resultSet.getInt("inscriptionAnnuelleId");

        Etudiant etudiant = new Etudiant.EtudiantBuilder()
                .setIdEtudiant(idEtudiant)
                .setCneEtudiant(cneEtudiant)
                .setCinEtudiant(cinEtudiant)
                .setNomEtudiant(nomEtudiant)
                .setPrenomEtudiant(prenomEtudiant)
                .setniveauId(niveauId)
                .setDateDeNaissance(dateDeNaissance)
                .setInscriptionAnnuelleId(inscriptionAnnuelleId)
                .build();

        return etudiant;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void supprimerEtudiant(Etudiant etudiant) {
        String query = "DELETE FROM etudiant WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, etudiant.getIdEtudiant());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

