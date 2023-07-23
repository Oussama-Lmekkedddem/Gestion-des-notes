package com.example.login.Model.entité;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Set;

public class InscriptionAnnuelle {
    private final Long idInscription;
    private final IntegerProperty annee;
    private final IntegerProperty etat;
    private final StringProperty type;
    private final IntegerProperty rang;
    private final StringProperty validation;
    private final StringProperty mention;
    private final StringProperty plusInfos;
    private final Set<InscriptionMatiere> inscriptionMatieres;
    private final Set<InscriptionModule> inscriptionModules;
    private Niveau niveau;
    private Etudiant etudiant;

    public InscriptionAnnuelle(InscriptionAnnuelleBuilder builder) {
        this.idInscription = builder.idInscription;
        this.annee = new SimpleIntegerProperty(builder.annee);
        this.etat = new SimpleIntegerProperty(builder.etat);
        this.type = new SimpleStringProperty(builder.type);
        this.rang = new SimpleIntegerProperty(builder.rang);
        this.validation = new SimpleStringProperty(builder.validation);
        this.mention = new SimpleStringProperty(builder.mention);
        this.plusInfos = new SimpleStringProperty(builder.plusInfos);
        this.inscriptionMatieres = builder.inscriptionMatieres;
        this.inscriptionModules = builder.inscriptionModules;
        this.niveau = builder.niveau;
        this.etudiant = builder.etudiant;
    }

    public Long getIdInscription() {
        return idInscription;
    }

    public int getAnnee() {
        return annee.get();
    }

    public void setAnnee(int annee) {
        this.annee.set(annee);
    }

    public IntegerProperty anneeProperty() {
        return annee;
    }

    public int getEtat() {
        return etat.get();
    }

    public void setEtat(int etat) {
        this.etat.set(etat);
    }

    public IntegerProperty etatProperty() {
        return etat;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public int getRang() {
        return rang.get();
    }

    public void setRang(int rang) {
        this.rang.set(rang);
    }

    public IntegerProperty rangProperty() {
        return rang;
    }

    public String getValidation() {
        return validation.get();
    }

    public void setValidation(String validation) {
        this.validation.set(validation);
    }

    public StringProperty validationProperty() {
        return validation;
    }

    public String getMention() {
        return mention.get();
    }

    public void setMention(String mention) {
        this.mention.set(mention);
    }

    public StringProperty mentionProperty() {
        return mention;
    }

    public String getPlusInfos() {
        return plusInfos.get();
    }

    public void setPlusInfos(String plusInfos) {
        this.plusInfos.set(plusInfos);
    }

    public StringProperty plusInfosProperty() {
        return plusInfos;
    }

    public Set<InscriptionMatiere> getInscriptionMatieres() {
        return inscriptionMatieres;
    }

    public Set<InscriptionModule> getInscriptionModules() {
        return inscriptionModules;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public static class InscriptionAnnuelleBuilder {
        private final Long idInscription;
        private int annee;
        private int etat;
        private String type;
        private int rang;
        private String validation;
        private String mention;
        private String plusInfos;
        private Set<InscriptionMatiere> inscriptionMatieres;
        private Set<InscriptionModule> inscriptionModules;
        private Niveau niveau;
        private Etudiant etudiant;

        public InscriptionAnnuelleBuilder(Long idInscription) {
            this.idInscription = idInscription;
        }

        public InscriptionAnnuelleBuilder annee(int annee) {
            this.annee = annee;
            return this;
        }

        public InscriptionAnnuelleBuilder etat(int etat) {
            this.etat = etat;
            return this;
        }

        public InscriptionAnnuelleBuilder type(String type) {
            this.type = type;
            return this;
        }

        public InscriptionAnnuelleBuilder rang(int rang) {
            this.rang = rang;
            return this;
        }

        public InscriptionAnnuelleBuilder validation(String validation) {
            this.validation = validation;
            return this;
        }

        public InscriptionAnnuelleBuilder mention(String mention) {
            this.mention = mention;
            return this;
        }

        public InscriptionAnnuelleBuilder plusInfos(String plusInfos) {
            this.plusInfos = plusInfos;
            return this;
        }

        public InscriptionAnnuelleBuilder inscriptionMatieres(Set<InscriptionMatiere> inscriptionMatieres) {
            this.inscriptionMatieres = inscriptionMatieres;
            return this;
        }

        public InscriptionAnnuelleBuilder inscriptionModules(Set<InscriptionModule> inscriptionModules) {
            this.inscriptionModules = inscriptionModules;
            return this;
        }

        public InscriptionAnnuelleBuilder niveau(Niveau niveau) {
            this.niveau = niveau;
            return this;
        }

        public InscriptionAnnuelleBuilder etudiant(Etudiant etudiant) {
            this.etudiant = etudiant;
            return this;
        }

        public InscriptionAnnuelle build() {
            return new InscriptionAnnuelle(this);
        }
    }
}

