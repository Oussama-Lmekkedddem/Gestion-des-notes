package com.example.login.Model.entité;

public enum EtudiantData {
    idEtudiant("idEtudiant"), cneEtudiant("cneEtudiant"), cinEtudiant("cinEtudiant"), nomEtudiant("nomEtudiant"),
    prenomEtudiant("prenomEtudiant"), classeId("niveauId");

    private String value;

    private EtudiantData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
