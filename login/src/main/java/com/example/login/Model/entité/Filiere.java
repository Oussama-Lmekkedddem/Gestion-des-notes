package com.example.login.Model.entité;

import java.util.Objects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Filiere {

    private final IntegerProperty idFiliere;
    private final StringProperty designationFiliere;
    private final DoubleProperty noteEliminatoireFiliere;
    private final IntegerProperty departementId;
    private final IntegerProperty professeurId;

    {
        this.idFiliere = new SimpleIntegerProperty();
        this.designationFiliere = new SimpleStringProperty();
        this.noteEliminatoireFiliere = new SimpleDoubleProperty();
        this.departementId = new SimpleIntegerProperty();
        this.professeurId = new SimpleIntegerProperty();
    }

    Filiere(FiliereBuilder filiereBuilder) {
        this.idFiliere.set(filiereBuilder.idFiliere);
        this.designationFiliere.set(filiereBuilder.designationFiliere);
        this.noteEliminatoireFiliere.set(filiereBuilder.noteEliminatoireFiliere);
        this.departementId.set(filiereBuilder.departementId);
        this.professeurId.set(filiereBuilder.professeurId);
    }

    public Filiere(int idFiliere, String designationFiliere, double noteEliminatoireFiliere, int professeurId) {
    }

    public static class FiliereBuilder {

        private int idFiliere;
        private String designationFiliere;
        private double noteEliminatoireFiliere;
        private int departementId;
        private int professeurId;

        public FiliereBuilder setIdFiliere(int idFiliere) {
            this.idFiliere = idFiliere;
            return this;
        }

        public FiliereBuilder setDesignationFiliere(String designationFiliere) {
            this.designationFiliere = designationFiliere;
            return this;
        }

        public FiliereBuilder setNoteEliminatoireFiliere(double noteEliminatoireFiliere) {
            this.noteEliminatoireFiliere = noteEliminatoireFiliere;
            return this;
        }

        public FiliereBuilder setDepartementId(int departementId) {
            this.departementId = departementId;
            return this;
        }

        public FiliereBuilder setProfesseurId(int professeurId) {
            this.professeurId = professeurId;
            return this;
        }

        public Filiere build() {
            return new Filiere(this);
        }

    }

    public IntegerProperty idFiliereProperty() {
        return idFiliere;
    }

    public StringProperty designationFiliereProperty() {
        return designationFiliere;
    }

    public DoubleProperty noteEliminatoireFiliereProperty() {
        return noteEliminatoireFiliere;
    }

    public IntegerProperty departementIdProperty() {
        return departementId;
    }

    public IntegerProperty professeurIdProperty() {
        return professeurId;
    }

    public int getIdFiliere() {
        return idFiliere.get();
    }

    public String getDesignationFiliere() {
        return designationFiliere.get();
    }

    public Double getNoteEliminatoireFiliere() {
        return noteEliminatoireFiliere.get();
    }

    public int getDepartementId() {
        return departementId.get();
    }

    public int getProfesseurId() {
        return professeurId.get();
    }

    public void setDesignationFiliere(String designationFiliere) {
        this.designationFiliere.set(designationFiliere);
    }

    public void setNoteEliminatoireFiliere(double noteEliminatoireFiliere) {
        this.noteEliminatoireFiliere.set(noteEliminatoireFiliere);
    }

    public void setDepartementId(int departementId) {
        this.departementId.set(departementId);
    }

    public void setProfesseurId(int professeurId) {
        this.professeurId.set(professeurId);
    }

    @Override
    public String toString() {
        return  designationFiliere.get() ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idFiliere.get();
        hash = 61 * hash + Objects.hashCode(this.designationFiliere.get());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Filiere other = (Filiere) obj;
        if (this.idFiliere.get() != other.idFiliere.get()) {
            return false;
        }
        if (!Objects.equals(this.designationFiliere.get(), other.designationFiliere.get())) {
            return false;
        }
        return true;
    }

}