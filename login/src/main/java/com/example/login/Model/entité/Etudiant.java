package com.example.login.Model.entité;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Objects;

public class Etudiant {

    private final IntegerProperty idEtudiant = new SimpleIntegerProperty();
    private final StringProperty cneEtudiant = new SimpleStringProperty();
    private final StringProperty cinEtudiant = new SimpleStringProperty();
    private final StringProperty nomEtudiant = new SimpleStringProperty();
    private final StringProperty prenomEtudiant = new SimpleStringProperty();
    private final IntegerProperty niveauId = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> dateDeNaissance = new SimpleObjectProperty<>();
    private final IntegerProperty inscriptionAnnuelleId = new SimpleIntegerProperty();



    public Etudiant(EtudiantBuilder EtudiantBuilder) {
        this.idEtudiant.set(EtudiantBuilder.idEtudiant);
        this.cneEtudiant.set(EtudiantBuilder.cneEtudiant);
        this.cinEtudiant.set(EtudiantBuilder.cinEtudiant);
        this.nomEtudiant.set(EtudiantBuilder.nomEtudiant);
        this.prenomEtudiant.set(EtudiantBuilder.prenomEtudiant);
        this.niveauId.set(EtudiantBuilder.niveauId);
        this.dateDeNaissance.set(EtudiantBuilder.dateDeNaissance);
        this.inscriptionAnnuelleId.set(EtudiantBuilder.inscriptionAnnuelleId);
    }

    public IntegerProperty dEtudiantProperty() {
        return this.idEtudiant;
    }

    public StringProperty cneEtudiantProperty() {
        return this.cneEtudiant;
    }

    public StringProperty cinEtudiantProperty() {
        return this.cinEtudiant;
    }

    public StringProperty nomEtudiantProperty() {
        return this.nomEtudiant;
    }

    public StringProperty prenomEtudiantProperty() {
        return this.prenomEtudiant;
    }

    public IntegerProperty niveauIdProperty() {
        return this.niveauId;
    }


    public int getIdEtudiant() {
        return this.idEtudiant.get();
    }

    public String getCneEtudiant() {
        return (String)this.cneEtudiant.get();
    }

    public String getCinEtudiant() {
        return (String)this.cinEtudiant.get();
    }

    public String getNomEtudiant() {
        return (String)this.nomEtudiant.get();
    }

    public String getPrenomEtudiant() {
        return (String)this.prenomEtudiant.get();
    }

    public int getniveauId() {
        return this.niveauId.get();
    }

    public void setCneEtudiant(String cneEtudiant) {
        this.cneEtudiant.set(cneEtudiant);
    }

    public void setCinEtudiant(String cinEtudiant) {
        this.cinEtudiant.set(cinEtudiant);
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant.set(nomEtudiant);
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant.set(prenomEtudiant);
    }

    public void setniveauId(int niveauId) {
        this.niveauId.set(niveauId);
    }
    public ObjectProperty<LocalDate> dateDeNaissanceProperty() {
        return this.dateDeNaissance;
    }

    public IntegerProperty inscriptionAnnuelleIdProperty() {
        return this.inscriptionAnnuelleId;
    }

    public LocalDate getDateDeNaissance() {
        return this.dateDeNaissance.get();
    }

    public int getInscriptionAnnuelleId() {
        return this.inscriptionAnnuelleId.get();
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance.set(dateDeNaissance);
    }

    public String toString() {
        return (String)this.nomEtudiant.get() + " " + (String)this.prenomEtudiant.get();
    }

    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idEtudiant.get();
        hash = 61 * hash + Objects.hashCode(this.cinEtudiant.get());
        hash = 61 * hash + Objects.hashCode(this.cneEtudiant.get());
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
            Etudiant other = (Etudiant) obj;
            if (this.idEtudiant.get() != other.idEtudiant.get()) {
                return false;
            } else if (!Objects.equals(this.cinEtudiant.get(), other.cinEtudiant.get())) {
                return false;
            } else {
                return Objects.equals(this.cneEtudiant.get(), other.cneEtudiant.get());
            }
        }
    }

    public static class EtudiantBuilder {
        private int idEtudiant;
        private String cneEtudiant;
        private String cinEtudiant;
        private String nomEtudiant;
        private String prenomEtudiant;
        private int niveauId;
        private LocalDate dateDeNaissance;
        private int inscriptionAnnuelleId;

        public EtudiantBuilder() {
        }

        public EtudiantBuilder setIdEtudiant(int idEtudiant) {
            this.idEtudiant = idEtudiant;
            return this;
        }

        public EtudiantBuilder setCneEtudiant(String cneEtudiant) {
            this.cneEtudiant = cneEtudiant;
            return this;
        }

        public EtudiantBuilder setCinEtudiant(String cinEtudiant) {
            this.cinEtudiant = cinEtudiant;
            return this;
        }

        public EtudiantBuilder setNomEtudiant(String nomEtudiant) {
            this.nomEtudiant = nomEtudiant;
            return this;
        }

        public EtudiantBuilder setPrenomEtudiant(String prenomEtudiant) {
            this.prenomEtudiant = prenomEtudiant;
            return this;
        }

        public EtudiantBuilder setniveauId(int niveauId) {
            this.niveauId = niveauId;
            return this;
        }
        public EtudiantBuilder setDateDeNaissance(LocalDate dateDeNaissance) {
            this.dateDeNaissance = dateDeNaissance;
            return this;
        }

        public EtudiantBuilder setInscriptionAnnuelleId(int inscriptionAnnuelleId) {
            this.inscriptionAnnuelleId = inscriptionAnnuelleId;
            return this;
        }

        public Etudiant build() {
            return new Etudiant(this);
        }
    }

}
