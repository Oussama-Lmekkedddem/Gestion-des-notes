package com.example.login.Model.entité;

import javafx.beans.property.*;
import com.example.login.Model.entité.Element;
import com.example.login.Model.entité.InscriptionAnnuelle;


public class InscriptionMatiere {

    private final LongProperty idInscriptionMatiere = new SimpleLongProperty();
    private final DoubleProperty noteSN = new SimpleDoubleProperty();
    private final DoubleProperty noteSR = new SimpleDoubleProperty();
    private final DoubleProperty noteFinale = new SimpleDoubleProperty();
    private final StringProperty validation = new SimpleStringProperty();
    private final StringProperty plusInfos = new SimpleStringProperty();
    private final DoubleProperty coefficient = new SimpleDoubleProperty();
    private final ObjectProperty<Element> matiere = new SimpleObjectProperty<>();
    private final ObjectProperty<InscriptionAnnuelle> inscriptionAnnuelle = new SimpleObjectProperty<>();

    private InscriptionMatiere(InscriptionMatiereBuilder builder) {
        this.idInscriptionMatiere.set(builder.idInscriptionMatiere);
        this.noteSN.set(builder.noteSN);
        this.noteSR.set(builder.noteSR);
        this.noteFinale.set(builder.noteFinale);
        this.validation.set(builder.validation);
        this.plusInfos.set(builder.plusInfos);
        this.coefficient.set(builder.coefficient);
        this.matiere.set(builder.matiere);
        this.inscriptionAnnuelle.set(builder.inscriptionAnnuelle);
    }

    public long getIdInscriptionMatiere() {
        return idInscriptionMatiere.get();
    }

    public LongProperty idInscriptionMatiereProperty() {
        return idInscriptionMatiere;
    }

    public double getNoteSN() {
        return noteSN.get();
    }

    public DoubleProperty noteSNProperty() {
        return noteSN;
    }

    public double getNoteSR() {
        return noteSR.get();
    }

    public DoubleProperty noteSRProperty() {
        return noteSR;
    }

    public double getNoteFinale() {
        return noteFinale.get();
    }

    public DoubleProperty noteFinaleProperty() {
        return noteFinale;
    }

    public String getValidation() {
        return validation.get();
    }

    public StringProperty validationProperty() {
        return validation;
    }

    public String getPlusInfos() {
        return plusInfos.get();
    }

    public StringProperty plusInfosProperty() {
        return plusInfos;
    }

    public double getCoefficient() {
        return coefficient.get();
    }

    public DoubleProperty coefficientProperty() {
        return coefficient;
    }

    public Element getMatiere() {
        return matiere.get();
    }

    public ObjectProperty<Element> matiereProperty() {
        return matiere;
    }

    public InscriptionAnnuelle getInscriptionAnnuelle() {
        return inscriptionAnnuelle.get();
    }

    public ObjectProperty<InscriptionAnnuelle> inscriptionAnnuelleProperty() {
        return inscriptionAnnuelle;
    }

    public static class InscriptionMatiereBuilder {
        private long idInscriptionMatiere;
        private double noteSN;
        private double noteSR;
        private double noteFinale;
        private String validation;
        private String plusInfos;
        private double coefficient;
        private Element matiere;
        private InscriptionAnnuelle inscriptionAnnuelle;

        public InscriptionMatiereBuilder withIdInscriptionMatiere(long idInscriptionMatiere) {
            this.idInscriptionMatiere = idInscriptionMatiere;
            return this;
        }

        public InscriptionMatiereBuilder withNoteSN(double noteSN) {
            this.noteSN = noteSN;
            return this;
        }

        public InscriptionMatiereBuilder withNoteSR(double noteSR) {
            this.noteSR = noteSR;
            return this;
        }

        public InscriptionMatiereBuilder withNoteFinale(double noteFinale) {
            this.noteFinale = noteFinale;
            return this;
        }

        public InscriptionMatiereBuilder withValidation(String validation) {
            this.validation = validation;
            return this;
        }

        public InscriptionMatiereBuilder withPlusInfos(String plusInfos) {
            this.plusInfos = plusInfos;
            return this;
        }

        public InscriptionMatiereBuilder withCoefficient(double coefficient) {
            this.coefficient = coefficient;
            return this;
        }

        public InscriptionMatiereBuilder withMatiere(Element matiere) {
            this.matiere = matiere;
            return this;
        }

        public InscriptionMatiereBuilder withInscriptionAnnuelle(InscriptionAnnuelle inscriptionAnnuelle) {
            this.inscriptionAnnuelle = inscriptionAnnuelle;
            return this;
        }

        public InscriptionMatiere build() {
            return new InscriptionMatiere(this);
        }
    }
}
