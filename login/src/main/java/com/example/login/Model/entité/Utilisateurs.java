package com.example.login.Model.entité;


import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Utilisateurs {
    private final IntegerProperty idUtilisateur = new SimpleIntegerProperty();
    private final StringProperty nomUtilisateur = new SimpleStringProperty();
    private final StringProperty motDePasseUtilisateur = new SimpleStringProperty();
    private final StringProperty cinUtilisateur = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty telephone = new SimpleStringProperty();
    private final StringProperty nomArabe = new SimpleStringProperty();
    private final StringProperty prenomArabe = new SimpleStringProperty();
    private final StringProperty photo = new SimpleStringProperty();
    private final StringProperty typeUtilisateur = new SimpleStringProperty();


    private Utilisateurs(UtilisateurBuilder compte) {
        this.idUtilisateur.set(compte.idUtilisateur);
        this.nomUtilisateur.set(compte.nomUtilisateur);
        this.motDePasseUtilisateur.set(compte.motDePasseUtilisateur);
        this.cinUtilisateur.set(compte.cinUtilisateur);
        this.email.set(compte.email);
        this.telephone.set(compte.telephone);
        this.nomArabe.set(compte.nomArabe);
        this.prenomArabe.set(compte.prenomArabe);
        this.photo.set(compte.photo);
        this.typeUtilisateur.set(compte.typeUtilisateur);

    }

    public String getTypeUtilisateur() {
        return typeUtilisateur.get();
    }

    public StringProperty typeUtilisateurProperty() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur.set(typeUtilisateur);
    }



    public int getIdUtilisateur() {
        return idUtilisateur.get();
    }

    public IntegerProperty idUtilisateurProperty() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur.set(idUtilisateur);
    }

    public String getNomUtilisateur() {
        return nomUtilisateur.get();
    }

    public StringProperty nomUtilisateurProperty() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur.set(nomUtilisateur);
    }

    public String getMotDePasseUtilisateur() {
        return motDePasseUtilisateur.get();
    }

    public StringProperty motDePasseUtilisateurProperty() {
        return motDePasseUtilisateur;
    }

    public void setMotDePasseUtilisateur(String motDePasseUtilisateur) {
        this.motDePasseUtilisateur.set(motDePasseUtilisateur);
    }
    public String getCinUtilisateur() {
        return cinUtilisateur.get();
    }

    public StringProperty cinUtilisateurProperty() {
        return cinUtilisateur;
    }

    public void setCinUtilisateur(String cinUtilisateur) {
        this.cinUtilisateur.set(cinUtilisateur);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }


    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephoneUtilisateur(String telephone) {
        this.telephone.set(telephone);
    }
    public void setEmailUtilisateur(String email) {
        this.email.set(email);
    }


    public String getNomArabe() {
        return nomArabe.get();
    }

    public StringProperty nomArabeProperty() {
        return nomArabe;
    }

    public void setNomArabe(String nomArabe) {
        this.nomArabe.set(nomArabe);
    }

    public String getPrenomArabe() {
        return prenomArabe.get();
    }

    public StringProperty prenomArabeProperty() {
        return prenomArabe;
    }

    public void setPrenomArabe(String prenomArabe) {
        this.prenomArabe.set(prenomArabe);
    }

    public String getPhoto() {
        return photo.get();
    }

    public StringProperty photoProperty() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateurs)) return false;
        Utilisateurs that = (Utilisateurs) o;
        return getIdUtilisateur() == that.getIdUtilisateur() &&
                Objects.equals(getNomUtilisateur(), that.getNomUtilisateur()) &&
                Objects.equals(getMotDePasseUtilisateur(), that.getMotDePasseUtilisateur()) &&
                Objects.equals(getCinUtilisateur(), that.getCinUtilisateur()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getTelephone(), that.getTelephone()) &&
                Objects.equals(getNomArabe(), that.getNomArabe()) &&
                Objects.equals(getPrenomArabe(), that.getPrenomArabe()) &&
                Objects.equals(getPhoto(), that.getPhoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUtilisateur(), getNomUtilisateur(), getMotDePasseUtilisateur(), getCinUtilisateur(), getEmail(), getTelephone(), getNomArabe(), getPrenomArabe(), getPhoto());
    }

    public static class UtilisateurBuilder {
        private int idUtilisateur;
        private String nomUtilisateur;
        private String prenomUtilisateur;
        private String motDePasseUtilisateur;
        private String cinUtilisateur;
        private String email;

        private String telephone;

        private String nomArabe;

        private String prenomArabe;

        private String photo;
        private String typeUtilisateur;

        public UtilisateurBuilder() {
        }

        public UtilisateurBuilder setIdUtilisateur(int idUtilisateur) {
            this.idUtilisateur = idUtilisateur;
            return this;
        }

        public UtilisateurBuilder setNomUtilisateur(String nomUtilisateur) {
            this.nomUtilisateur = nomUtilisateur;
            return this;
        }

        public UtilisateurBuilder setMotDePasseUtilisateur(String motDePasseUtilisateur) {
            this.motDePasseUtilisateur = motDePasseUtilisateur;
            return this;
        }

        public UtilisateurBuilder setTelephoneBuilder(String telephone) {
            this.telephone = telephone;
            return this;
        }
        public UtilisateurBuilder setEmailBuilder(String email) {
            this.email = email;
            return this;
        }
        public UtilisateurBuilder setnomarabeBuilder(String nomArabe) {
            this.nomArabe = nomArabe;
            return this;
        }
        public UtilisateurBuilder setprenomarabBuilder(String prenomArabe) {
            this.prenomArabe = prenomArabe;
            return this;
        }
        public UtilisateurBuilder setphotoBuilder(String photo) {
            this.photo = photo;
            return this;
        }


        public UtilisateurBuilder setcinUtilisateur(String cinUtilisateur) {
            this.cinUtilisateur = cinUtilisateur;
            return this;}

        public Utilisateurs build() {
            return new Utilisateurs(this);
        }

    }

}
