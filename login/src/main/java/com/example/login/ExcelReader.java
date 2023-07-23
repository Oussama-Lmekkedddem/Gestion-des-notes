package com.example.login;

import com.example.login.Model.entité.Etudiant;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public List<Etudiant> readExcel(File file) {
        List<Etudiant> etudiants = new ArrayList<>();

        try {
            // Obtaining an instance of the database connection
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            // Read the Excel file
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through the rows of the Excel file
            for (Row row : sheet) {
                // Retrieve the data from each cell
                Cell idEtudiantCell = row.getCell(0);
                Cell cneCell = row.getCell(1);
                Cell nomCell = row.getCell(2);
                Cell prenomCell = row.getCell(3);
                Cell idNiveauActuelCell = row.getCell(4);
                Cell typeCell = row.getCell(5);

                // Check if the file format is correct
                if (idEtudiantCell == null || cneCell == null || nomCell == null || prenomCell == null
                        || idNiveauActuelCell == null || typeCell == null) {
                    System.out.println("Incorrect file format!");
                    break;
                }

                int idEtudiant;
                try {
                    idEtudiant = (int) idEtudiantCell.getNumericCellValue();
                } catch (IllegalStateException e) {
                    System.out.println("Error: Invalid student ID!");
                    continue;
                }

                String cne;
                try {
                    cne = cneCell.getStringCellValue();
                } catch (IllegalStateException e) {
                    System.out.println("Error: Invalid student CNE!");
                    continue;
                }

                String nom;
                try {
                    nom = nomCell.getStringCellValue();
                } catch (IllegalStateException e) {
                    System.out.println("Error: Invalid student name!");
                    continue;
                }

                String prenom;
                try {
                    prenom = prenomCell.getStringCellValue();
                } catch (IllegalStateException e) {
                    System.out.println("Error: Invalid student first name!");
                    continue;
                }

                int idNiveauActuel;
                try {
                    idNiveauActuel = (int) idNiveauActuelCell.getNumericCellValue();
                } catch (IllegalStateException e) {
                    System.out.println("Error: Invalid current level ID!");
                    continue;
                }

                String type;
                try {
                    type = typeCell.getStringCellValue();
                } catch (IllegalStateException e) {
                    System.out.println("Error: Invalid registration type!");
                    continue;
                }

                // Check if the student already exists in the database
                boolean existingStudent = checkExistingStudent(connection, idEtudiant);

                if (type.equalsIgnoreCase("Reinscription")) {
                    if (existingStudent) {
                        int niveauActuelEtudiant = getNiveauActuelEtudiant(connection, idEtudiant);
                        if (idNiveauActuel != niveauActuelEtudiant) {
                            updateNiveauActuel(connection, idEtudiant, idNiveauActuel);
                            System.out.println("The student with ID " + idEtudiant + " has been updated!");
                        } else {
                            System.out.println("The student with ID " + idEtudiant + " already exists!");
                        }
                    } else {
                        if (checkNiveauExiste(connection, idNiveauActuel)) {
                            insertEtudiant(connection, idEtudiant, cne, nom, prenom, idNiveauActuel, type);
                            System.out.println("The student with ID " + idEtudiant + " has been inserted!");
                        } else {
                            System.out.println("Error: The level with ID " + idNiveauActuel + " does not exist!");
                        }
                    }
                } else if (type.equalsIgnoreCase("Inscription")) {
                    if (existingStudent) {
                        System.out.println("The student with ID " + idEtudiant + " already exists!");
                    } else {
                        if (checkNiveauExiste(connection, idNiveauActuel)) {
                            insertEtudiant(connection, idEtudiant, cne, nom, prenom, idNiveauActuel, type);
                            System.out.println("The student with ID " + idEtudiant + " has been inserted!");
                        } else {
                            System.out.println("Error: The level with ID " + idNiveauActuel + " does not exist!");
                        }
                    }
                } else {
                    System.out.println("Error: Invalid registration type!");
                }
            }

            // Close the input stream
            inputStream.close();

            System.out.println("Importing registrations completed!");

            // Close the database connection
            dbConnection.closeConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return etudiants;
    }

    private static boolean checkExistingStudent(Connection connection, int idEtudiant) throws SQLException {
        String query = "SELECT * FROM inscription wherRE IDetudiant = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idEtudiant);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static int getNiveauActuelEtudiant(Connection connection, int idEtudiant) throws SQLException {
        String query = "SELECT niveauId FROM inscription WHERE IDetudiant = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idEtudiant);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("niveauId");
                } else {
                    throw new SQLException("Student not found in the database!");
                }
            }
        }
    }

    private static void updateNiveauActuel(Connection connection, int idEtudiant, int idNiveauActuel) throws SQLException {
        String query = "UPDATE etudiant SET niveauId = ? WHERE IDetudiant = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idNiveauActuel);
            statement.setInt(2, idEtudiant);
            statement.executeUpdate();
        }
    }

    private static boolean checkNiveauExiste(Connection connection, int idNiveauActuel) throws SQLException {
        String query = "SELECT * FROM niveau WHERE idNiveau = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idNiveauActuel);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static void insertEtudiant(Connection connection, int idEtudiant, String cne, String nom,
                                       String prenom, int idNiveauActuel, String type) throws SQLException {
        String query = "INSERT INTO etudiant (IDetudiant, CNE, nom, prenom, niveauId, type) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idEtudiant);
            statement.setString(2, cne);
            statement.setString(3, nom);
            statement.setString(4, prenom);
            statement.setInt(5, idNiveauActuel);
            statement.setString(6, type);
            statement.executeUpdate();
        }
    }
}
