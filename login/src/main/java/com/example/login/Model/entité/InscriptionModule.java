package com.example.login.Model.entité;

import javafx.beans.property.*;

public class InscriptionModule {

        private final LongProperty idInscriptionModule = new SimpleLongProperty();
        private final DoubleProperty noteSN = new SimpleDoubleProperty();
        private final DoubleProperty noteSR = new SimpleDoubleProperty();
        private final DoubleProperty noteFinale = new SimpleDoubleProperty();
        private final StringProperty validation = new SimpleStringProperty();
        private final StringProperty plusInfos = new SimpleStringProperty();
        private final ObjectProperty<Module> module = new SimpleObjectProperty<>();
        private final ObjectProperty<InscriptionAnnuelle> inscriptionAnnuelle = new SimpleObjectProperty<>();

    private InscriptionModule(InscriptionModuleBuilder builder) {
        this.idInscriptionModule.set(builder.idInscriptionModule);
        this.noteSN.set(builder.noteSN);
        this.noteSR.set(builder.noteSR);
        this.noteFinale.set(builder.noteFinale);
        this.validation.set(builder.validation);
        this.plusInfos.set(builder.plusInfos);
        this.module.set(builder.module);
        this.inscriptionAnnuelle.set(builder.inscriptionAnnuelle);
    }

        public Long getIdInscriptionModule() {
        return idInscriptionModule.get();
    }

        public void setIdInscriptionModule(Long idInscriptionModule) {
        this.idInscriptionModule.set(idInscriptionModule);
    }

        public LongProperty idInscriptionModuleProperty() {
        return idInscriptionModule;
    }

        public double getNoteSN() {
        return noteSN.get();
    }

        public void setNoteSN(double noteSN) {
        this.noteSN.set(noteSN);
    }

        public DoubleProperty noteSNProperty() {
        return noteSN;
    }

        public double getNoteSR() {
        return noteSR.get();
    }

        public void setNoteSR(double noteSR) {
        this.noteSR.set(noteSR);
    }

        public DoubleProperty noteSRProperty() {
        return noteSR;
    }

        public double getNoteFinale() {
        return noteFinale.get();
    }

        public void setNoteFinale(double noteFinale) {
        this.noteFinale.set(noteFinale);
    }

        public DoubleProperty noteFinaleProperty() {
        return noteFinale;
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

        public String getPlusInfos() {
        return plusInfos.get();
    }

        public void setPlusInfos(String plusInfos) {
        this.plusInfos.set(plusInfos);
    }

        public StringProperty plusInfosProperty() {
        return plusInfos;
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

        public InscriptionAnnuelle getInscriptionAnnuelle() {
        return inscriptionAnnuelle.get();
    }

        public void setInscriptionAnnuelle(InscriptionAnnuelle inscriptionAnnuelle) {
        this.inscriptionAnnuelle.set(inscriptionAnnuelle);
    }

        public ObjectProperty<InscriptionAnnuelle> inscriptionAnnuelleProperty() {
        return inscriptionAnnuelle;
    }

        public static class InscriptionModuleBuilder {
            private Long idInscriptionModule;
            private double noteSN;
            private double noteSR;
            private double noteFinale;
            private String validation;
            private String plusInfos;
            private Module module;
            private InscriptionAnnuelle inscriptionAnnuelle;

            public InscriptionModuleBuilder withIdInscriptionModule(Long idInscriptionModule) {
                this.idInscriptionModule = idInscriptionModule;
                return this;
            }

            public InscriptionModuleBuilder withNoteSN(double noteSN) {
                this.noteSN = noteSN;
                return this;
            }

            public InscriptionModuleBuilder withNoteSR(double noteSR) {
                this.noteSR = noteSR;
                return this;
            }

            public InscriptionModuleBuilder withNoteFinale(double noteFinale) {
                this.noteFinale = noteFinale;
                return this;
            }

            public InscriptionModuleBuilder withValidation(String validation) {
                this.validation = validation;
                return this;
            }

            public InscriptionModuleBuilder withPlusInfos(String plusInfos) {
                this.plusInfos = plusInfos;
                return this;
            }

            public InscriptionModuleBuilder withModule(Module module) {
                this.module = module;
                return this;
            }

            public InscriptionModuleBuilder withInscriptionAnnuelle(InscriptionAnnuelle inscriptionAnnuelle) {
                this.inscriptionAnnuelle = inscriptionAnnuelle;
                return this;
            }

            public InscriptionModule build() {
                return new InscriptionModule(this);
            }
        }
}
