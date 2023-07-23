package com.example.login.Model.entité;

public enum ProfesseurData {

    idProfesseur("idProfesseur"), nomProfesseur("nomProfesseur"), prenomProfesseur("prenomProfesseur"),
    emailProfesseur("emailProfesseur"), utilisateurId("utilisateurId"), departementId("departementId");

    private String value;

    private ProfesseurData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
