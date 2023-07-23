package com.example.login.Model.entité;


import java.util.Objects;

import javafx.beans.property.*;

public class Professeur {
    private final IntegerProperty idProfesseur = new SimpleIntegerProperty();
    private final StringProperty nomProfesseur = new SimpleStringProperty();
    private final StringProperty prenomProfesseur = new SimpleStringProperty();
    private final StringProperty emailProfesseur = new SimpleStringProperty();
    private final IntegerProperty utilisateurId = new SimpleIntegerProperty();
    private final IntegerProperty departementId = new SimpleIntegerProperty();
    private final ObjectProperty<Utilisateurs> utilisateur = new SimpleObjectProperty<>();

    private Professeur(ProfesseurBuilder professeurBuilder) {
        this.idProfesseur.set(professeurBuilder.idProfesseur);
        this.nomProfesseur.set(professeurBuilder.nomProfesseur);
        this.prenomProfesseur.set(professeurBuilder.prenomProfesseur);
        this.emailProfesseur.set(professeurBuilder.emailProfesseur);
        this.utilisateurId.set(professeurBuilder.utilisateurId);
        this.departementId.set(professeurBuilder.departementId);

    }

    public IntegerProperty idProfesseurProperty() {
        return this.idProfesseur;
    }

    public StringProperty nomProfesseurProperty() {
        return this.nomProfesseur;
    }

    public StringProperty prenomProfesseurProperty() {
        return this.prenomProfesseur;
    }

    public StringProperty emailProfesseurProperty() {
        return this.emailProfesseur;
    }

    public IntegerProperty utilisateurIdProperty() {
        return this.utilisateurId;
    }

    public IntegerProperty departementIdProperty() {
        return this.departementId;
    }

    public int getIdProfesseur() {
        return this.idProfesseur.get();
    }

    public String getNomProfesseur() {
        return (String)this.nomProfesseur.get();
    }

    public String getPrenomProfesseur() {
        return (String)this.prenomProfesseur.get();
    }

    public String getEmailProfesseur() {
        return (String)this.emailProfesseur.get();
    }

    public int getUtilisateurId() {
        return this.utilisateurId.get();
    }

    public int getDepartementId() {
        return this.departementId.get();
    }

    public void setNomProfesseur(String nomProfesseur) {
        this.nomProfesseur.set(nomProfesseur);
    }

    public void setPrenomProfesseur(String prenomProfesseur) {
        this.prenomProfesseur.set(prenomProfesseur);
    }

    public void setEmailProfesseur(String emailProfesseur) {
        this.emailProfesseur.set(emailProfesseur);
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId.set(utilisateurId);
    }

    public void setDepartementId(int departementId) {
        this.departementId.set(departementId);
    }

    public String toString() {
        return (String)this.nomProfesseur.get() + " " + (String)this.prenomProfesseur.get();
    }

    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idProfesseur.get();
        hash = 61 * hash + Objects.hashCode(this.emailProfesseur.get());
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Professeur other = (Professeur)obj;
            if (this.idProfesseur.get() != other.idProfesseur.get()) {
                return false;
            } else {
                return Objects.equals(this.emailProfesseur.get(), other.emailProfesseur.get());
            }
        }
    }

    public static class ProfesseurBuilder {
        private int idProfesseur;
        private String nomProfesseur;
        private String prenomProfesseur;
        private String emailProfesseur;
        private int utilisateurId;
        private int departementId;

        public ProfesseurBuilder() {
        }

        public ProfesseurBuilder setIdProfesseur(int idProfesseur) {
            this.idProfesseur = idProfesseur;
            return this;
        }

        public ProfesseurBuilder setNomProfesseur(String nomProfesseur) {
            this.nomProfesseur = nomProfesseur;
            return this;
        }

        public ProfesseurBuilder setPrenomProfesseur(String prenomProfesseur) {
            this.prenomProfesseur = prenomProfesseur;
            return this;
        }

        public ProfesseurBuilder setEmailProfesseur(String emailProfesseur) {
            this.emailProfesseur = emailProfesseur;
            return this;
        }

        public ProfesseurBuilder setUtilisateurId(int utilisateurId) {
            this.utilisateurId = utilisateurId;
            return this;
        }

        public ProfesseurBuilder setDepartementId(int departementId) {
            this.departementId = departementId;
            return this;
        }

        public Professeur build() {
            return new Professeur(this);
        }
    }
/*    public Utilisateurs getUtilisateur() {
        return utilisateur.get();
    }
*/

}

