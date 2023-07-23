package com.example.login.Controller.Niveau;


import com.example.login.DatabaseConnection;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcelCreationProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demander les informations à l'utilisateur
        System.out.print("Entrez votre nom : ");
        String nomProfesseur = scanner.nextLine();

        System.out.print("Entrez le nom du module : ");
        String nomModule = scanner.nextLine();

        System.out.print("Entrez le semestre : ");
        String semestre = scanner.nextLine();

        System.out.print("Entrez la session (normale ou rattrapage) : ");
        String session = scanner.nextLine();

        System.out.print("Entrez l'année actuelle : ");
        int annee = scanner.nextInt();

        System.out.print("Entrez l'ID niveau : ");
        int idNiveau = scanner.nextInt();

        // Créer un nouveau classeur Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Données");

        // Créer les cellules et définir les valeurs
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);

        Cell cellA1 = row1.createCell(0);
        Cell cellA2 = row2.createCell(0);
        Cell cellB1 = row1.createCell(1);
        Cell cellB2 = row2.createCell(1);
        Cell cellC1 = row1.createCell(2);
        Cell cellC2 = row2.createCell(2);
        Cell cellD1 = row1.createCell(3);
        Cell cellD2 = row2.createCell(3);
        Cell cellE1 = row1.createCell(4);
        Cell cellE2 = row2.createCell(4);
        Cell cellF1 = row1.createCell(5);
        Cell cellF2 = row2.createCell(5);

        cellA1.setCellValue("MODULE");
        cellA2.setCellValue(nomModule);
        cellB1.setCellValue("ENSEIGNANT");
        cellB2.setCellValue(nomProfesseur);
        cellC1.setCellValue("SEMESTRE");
        cellC2.setCellValue(semestre);
        cellD1.setCellValue("SESSION");
        cellD2.setCellValue(session);
        cellE1.setCellValue("ANNÉE");
        cellE2.setCellValue("Classe");
        cellF1.setCellValue(String.valueOf(annee));
        cellF2.setCellValue(String.valueOf(idNiveau));

        Row rowHeader = sheet.createRow(3);
        Cell cellA3 = rowHeader.createCell(0);
        Cell cellB3 = rowHeader.createCell(1);
        Cell cellC3 = rowHeader.createCell(2);
        Cell cellD3 = rowHeader.createCell(3);
        Cell cellE3 = rowHeader.createCell(4);
        Cell cellF3 = rowHeader.createCell(5);
        Cell cellG3 = rowHeader.createCell(6);
        Cell cellH3 = rowHeader.createCell(7);

        cellA3.setCellValue("ID");
        cellB3.setCellValue("CNE");
        cellC3.setCellValue("NOM");
        cellD3.setCellValue("PRENOM");
        cellE3.setCellValue("ELEMENT1");
        cellF3.setCellValue("ELEMENT2");
        cellG3.setCellValue("MOYENNE");
        cellH3.setCellValue("VALIDATION");

        // Remplir les données des étudiants
        List<String[]> etudiantsList = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT IDetudiant, cne, firstname, lastname FROM ETUDIANT WHERE niveauID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idNiveau);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("IDetudiant");
                String cne = resultSet.getString("cne");
                String nom = resultSet.getString("lastname");
                String prenom = resultSet.getString("firstname");

                // Ajouter les données de l'étudiant à la liste
                etudiantsList.add(new String[]{id, cne, nom, prenom, "", ""});
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convertir la liste en tableau
        String[][] etudiants = new String[etudiantsList.size()][];
        etudiantsList.toArray(etudiants);

        for (int i = 0; i < etudiants.length; i++) {
            Row row = sheet.createRow(i + 4);
            for (int j = 0; j < etudiants[i].length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(etudiants[i][j]);
            }
            Cell cellMoyenne = row.createCell(6);
            cellMoyenne.setCellFormula("(E" + (i + 5) + "+F" + (i + 5) + ")/2");

            Cell cellValidation = row.createCell(7);
            cellValidation.setCellFormula("IF(G" + (i + 5) + ">=12, \"V\", \"R\")");
        }

        // Ajuster la largeur des colonnes
        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }

        // Enregistrer le fichier Excel
        try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\hp\\Pictures\\data S1\\BINOM\\data s2\\tppppy\\note.xlsx")) {
            workbook.write(fileOutputStream);
            System.out.println("Le fichier Excel a été créé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fermer le classeur Excel
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
