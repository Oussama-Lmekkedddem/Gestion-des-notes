package com.example.login.Model.entité;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Niveau {
    private final IntegerProperty filiereId = new SimpleIntegerProperty();
    private final IntegerProperty idNiveau = new SimpleIntegerProperty();
    private final StringProperty alias = new SimpleStringProperty();
    private final StringProperty titre = new SimpleStringProperty();
    private final ListProperty<Module> modules = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<InscriptionAnnuelle> inscriptions = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ObjectProperty<Filiere> filiere = new SimpleObjectProperty<>();

    private Niveau(NiveauBuilder builder) {
        this.filiereId.set(builder.filiereId);
        this.idNiveau.set(builder.idNiveau);
        this.alias.set(builder.alias);
        this.titre.set(builder.titre);

        // Check if the modules list is null and initialize it with an empty list
        if (builder.modules != null) {
            this.modules.addAll(builder.modules);
        } else {
            this.modules.set(FXCollections.observableArrayList());
        }

        // Similarly, check and initialize the inscriptions list
        if (builder.inscriptions != null) {
            this.inscriptions.addAll(builder.inscriptions);
        } else {
            this.inscriptions.set(FXCollections.observableArrayList());
        }

        this.filiere.set(builder.filiere);
        this.filiereId.set(builder.filiereId);
    }

    public NiveauBuilder toBuilder() {
        return new NiveauBuilder()
                .withIdNiveau(this.idNiveau.get())
                .withFiliereId(this.filiereId.get())
                .withAlias(this.alias.get())
                .withTitre(this.titre.get())
                .withModules(this.modules)
                .withInscriptions(this.inscriptions)
                .withFiliere(this.filiere.get());
    }

    public int getIdNiveau() {
        return idNiveau.get();
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau.set(idNiveau);
    }

    public IntegerProperty idNiveauProperty() {
        return idNiveau;
    }

    public int getFiliereId() {
        return filiereId.get();
    }

    public void setFiliereId(int filiereId) {
        this.filiereId.set(filiereId);
    }

    public IntegerProperty filiereIdProperty() {
        return filiereId;
    }

    public String getAlias() {
        return alias.get();
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public StringProperty aliasProperty() {
        return alias;
    }

    public String getTitre() {
        return titre.get();
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public StringProperty titreProperty() {
        return titre;
    }

    public ObservableList<Module> getModules() {
        return modules.get();
    }

    public void setModules(List<Module> modules) {
        this.modules.setAll(modules);
    }

    public ListProperty<Module> modulesProperty() {
        return modules;
    }

    public ObservableList<InscriptionAnnuelle> getInscriptions() {
        return inscriptions.get();
    }

    public void setInscriptions(List<InscriptionAnnuelle> inscriptions) {
        this.inscriptions.setAll(inscriptions);
    }

    public ListProperty<InscriptionAnnuelle> inscriptionsProperty() {
        return inscriptions;
    }

    public Filiere getFiliere() {
        return filiere.get();
    }

    public void setFiliere(Filiere filiere) {
        this.filiere.set(filiere);
    }

    public ObjectProperty<Filiere> filiereProperty() {
        return filiere;
    }

    public static class NiveauBuilder {
        private int idNiveau;
        private String alias;
        private String titre;
        private List<Module> modules;
        private List<InscriptionAnnuelle> inscriptions;
        private Filiere filiere;
        private int filiereId;

        public NiveauBuilder withIdNiveau(int idNiveau) {
            this.idNiveau = idNiveau;
            return this;
        }

        public NiveauBuilder withFiliereId(int filiereId) {
            this.filiereId = filiereId;
            return this;
        }

        public NiveauBuilder withAlias(String alias) {
            this.alias = alias;
            return this;
        }

        public NiveauBuilder withTitre(String titre) {
            this.titre = titre;
            return this;
        }

        public NiveauBuilder withModules(List<Module> modules) {
            this.modules = modules != null ? modules : new ArrayList<>();
            return this;
        }

        public NiveauBuilder withInscriptions(List<InscriptionAnnuelle> inscriptions) {
            this.inscriptions = inscriptions != null ? inscriptions : new ArrayList<>();
            return this;
        }

        public NiveauBuilder withFiliere(Filiere filiere) {
            this.filiere = filiere;
            return this;
        }

        public Niveau build() {
            return new Niveau(this);
        }
    }
}
