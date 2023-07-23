package com.example.login.Model.entité;

import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Departement {
    private final IntegerProperty idDepartement = new SimpleIntegerProperty();
    private final StringProperty designationDepartement = new SimpleStringProperty();
    private final IntegerProperty professeurId = new SimpleIntegerProperty();

    private Departement(DepartementBuilder departementBuilder) {
        this.idDepartement.set(departementBuilder.idDepartement);
        this.designationDepartement.set(departementBuilder.designationDepartement);
        this.professeurId.set(departementBuilder.professeurId);
    }

    public IntegerProperty idDepartementProperty() {
        return this.idDepartement;
    }

    public StringProperty designationDepartementProperty() {
        return this.designationDepartement;
    }

    public IntegerProperty professeurIdProperty() {
        return this.professeurId;
    }

    public int getIdDepartement() {
        return this.idDepartement.get();
    }

    public String getDesignationDepartement() {
        return (String)this.designationDepartement.get();
    }

    public int getProfesseurId() {
        return this.professeurId.get();
    }

    public void setDesignationDepartement(String designationDepartement) {
        this.designationDepartement.set(designationDepartement);
    }

    public void setProfesseurId(int professeurId) {
        this.professeurId.set(professeurId);
    }

    public String toString() {
        return (String)this.designationDepartement.get();
    }

    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idDepartement.get();
        hash = 61 * hash + Objects.hashCode(this.designationDepartement.get());
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
            Departement other = (Departement)obj;
            if (this.idDepartement.get() != other.idDepartement.get()) {
                return false;
            } else {
                return Objects.equals(this.designationDepartement.get(), other.designationDepartement.get());
            }
        }
    }

    public static class DepartementBuilder {
        private int idDepartement;
        private String designationDepartement;
        private int professeurId;

        public DepartementBuilder() {
        }

        public DepartementBuilder setIdDepartement(int idDepartement) {
            this.idDepartement = idDepartement;
            return this;
        }

        public DepartementBuilder setDesignationDepartement(String designationDepartement) {
            this.designationDepartement = designationDepartement;
            return this;
        }

        public DepartementBuilder setProfesseurId(int professeurId) {
            this.professeurId = professeurId;
            return this;
        }

        public Departement build() {
            return new Departement(this);
        }
    }
}