package com.example.login.Model.entité;

import javafx.beans.property.*;



public class Element {
    private final LongProperty idMatiere = new SimpleLongProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final DoubleProperty currentCoefficient = new SimpleDoubleProperty();
    private final ObjectProperty<Module> module = new SimpleObjectProperty<>();

    public Element(ElementBuilder builder) {
        this.idMatiere.set(builder.idMatiere);
        this.nom.set(builder.nom);
        this.code.set(builder.code);
        this.currentCoefficient.set(builder.currentCoefficient);
        this.module.set(builder.module);
    }

    public Long getIdMatiere() {
        return idMatiere.get();
    }

    public void setIdMatiere(Long idMatiere) {
        this.idMatiere.set(idMatiere);
    }

    public LongProperty idMatiereProperty() {
        return idMatiere;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public StringProperty codeProperty() {
        return code;
    }

    public double getCurrentCoefficient() {
        return currentCoefficient.get();
    }

    public void setCurrentCoefficient(double currentCoefficient) {
        this.currentCoefficient.set(currentCoefficient);
    }

    public DoubleProperty currentCoefficientProperty() {
        return currentCoefficient;
    }

    public Module getModule() {
        return module.get();
    }

    public void setModule(Module module) {
        this.module.set(module);
    }

    public ObjectProperty<Module> moduleProperty() {
        return module;
    }

    public static class ElementBuilder {
        private Long idMatiere;
        private String nom;
        private String code;
        private double currentCoefficient;
        private Module module;

        public ElementBuilder withIdMatiere(Long idMatiere) {
            this.idMatiere = idMatiere;
            return this;
        }

        public ElementBuilder withNom(String nom) {
            this.nom = nom;
            return this;
        }

        public ElementBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public ElementBuilder withCurrentCoefficient(double currentCoefficient) {
            this.currentCoefficient = currentCoefficient;
            return this;
        }

        public ElementBuilder withModule(Module module) {
            this.module = module;
            return this;
        }

        public Element build() {
            return new Element(this);
        }
    }
}
