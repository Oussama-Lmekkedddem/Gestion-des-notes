package com.example.login.Model.entité;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Module {
    private final LongProperty idModule = new SimpleLongProperty();
    private final StringProperty titre = new SimpleStringProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final ListProperty<Element> elements = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ObjectProperty<Niveau> niveau = new SimpleObjectProperty<>();
    private final LongProperty semesterID = new SimpleLongProperty();
    private final LongProperty niveauID = new SimpleLongProperty();
    private final LongProperty elementID = new SimpleLongProperty();

    public Long getIdModule() {
        return idModule.get();
    }

    public LongProperty idModuleProperty() {
        return idModule;
    }

    public void setIdModule(Long idModule) {
        this.idModule.set(idModule);
    }

    public String getTitre() {
        return titre.get();
    }

    public StringProperty titreProperty() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public ObservableList<Element> getElements() {
        return elements.get();
    }

    public ListProperty<Element> elementsProperty() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements.setAll(elements);
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }

    public Niveau getNiveau() {
        return niveau.get();
    }

    public ObjectProperty<Niveau> niveauProperty() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau.set(niveau);
    }

    public Long getSemesterID() {
        return semesterID.get();
    }

    public LongProperty semesterIDProperty() {
        return semesterID;
    }

    public void setSemesterID(Long semesterID) {
        this.semesterID.set(semesterID);
    }

    public Long getNiveauID() {
        return niveauID.get();
    }

    public LongProperty niveauIDProperty() {
        return niveauID;
    }

    public void setNiveauID(Long niveauID) {
        this.niveauID.set(niveauID);
    }

    public Long getElementID() {
        return elementID.get();
    }

    public LongProperty elementIDProperty() {
        return elementID;
    }

    public void setElementID(Long elementID) {
        this.elementID.set(elementID);
    }

    public static class Builder {
        public final Module module;

        public Builder() {
            module = new Module();
        }

        public Builder withIdModule(Long idModule) {
            module.setIdModule(idModule);
            return this;
        }

        public Builder withTitre(String titre) {
            module.setTitre(titre);
            return this;
        }

        public Builder withCode(String code) {
            module.setCode(code);
            return this;
        }

        public Builder withElements(List<Element> elements) {
            module.setElements(elements);
            return this;
        }

        public Builder withNiveau(Niveau niveau) {
            module.setNiveau(niveau);
            return this;
        }

        public Builder withSemesterID(Long semesterID) {
            module.setSemesterID(semesterID);
            return this;
        }

        public Builder withNiveauID(Long niveauID) {
            module.setNiveauID(niveauID);
            return this;
        }

        public Builder withElementID(Long elementID) {
            module.setElementID(elementID);
            return this;
        }

        public Module build() {
            return module;
        }
    }
}
