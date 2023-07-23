zakariaCREATE TABLE UTILISATEUR (
  IDutilisateur INT PRIMARY KEY,
  Name VARCHAR(255),
  passwordUtilisateur VARCHAR(255),
  cleUtilisateur VARCHAR(255)
);

CREATE TABLE ADMINISTRATEUR (
  idAdmin INT PRIMARY KEY,
  utilisateurID INT,
  FOREIGN KEY (utilisateurID) REFERENCES UTILISATEUR(IDutilisateur)
);

CREATE TABLE PROFESSEUR (
  IDprof INT PRIMARY KEY,
  firstname VARCHAR(255),
  lastname VARCHAR(255),
  departementId INT
);

CREATE TABLE DEPARTEMENT (
  idDepartement INT PRIMARY KEY,
  nomDepart VARCHAR(255),
  professeurId INT
);
CREATE TABLE FILIERE (
  idfiliere INT PRIMARY KEY,
  nomfiliere VARCHAR(255),
  noteEliminatoire DECIMAL(5, 2),
  code VARCHAR(50),
  departementID INT,
  professeurID INT,
  FOREIGN KEY (departementID) REFERENCES DEPARTEMENT(idDepartement),
  FOREIGN KEY (professeurID) REFERENCES PROFESSEUR(IDprof)
);
CREATE TABLE NIVEAU (
  IDniveau INT PRIMARY KEY,
  nomniveau VARCHAR(255),
  filiereId INT,
  module ENUM('module1', 'module2', 'module3'),
  inscriptionAnnuelleId INT
);
CREATE TABLE SEMESTER (
  ID INT PRIMARY KEY,
  nom VARCHAR(255),
  niveauId INT,
  FOREIGN KEY (niveauId) REFERENCES NIVEAU(IDniveau)
);
 CREATE TABLE ELEMENT (
  idElement INT PRIMARY KEY,
  name VARCHAR(255),
  code VARCHAR(50),
  coefficient INT,
  moduleID INT,
  professeurID INT,
  FOREIGN KEY (professeurID) REFERENCES PROFESSEUR(IDprof)
);
[2:48 AM, 26/05/2023] +212 649-205989: CREATE TABLE MODULE (
  IDmodule INT PRIMARY KEY,
  code VARCHAR(50),
  semesterID INT,
  niveauID INT,
  ElementId INT,
  FOREIGN KEY (semesterID) REFERENCES SEMESTER(ID),
  FOREIGN KEY (niveauID) REFERENCES NIVEAU(IDniveau),
  FOREIGN KEY (ElementId) REFERENCES ELEMENT(idElement)
);
CREATE TABLE ETUDIANT (
  IDetudiant INT PRIMARY KEY,
  cne VARCHAR(50),
  cni VARCHAR(50),
  firstname VARCHAR(255),
  lastname VARCHAR(255),
  niveauId INT,
  datedenaissance DATE,
  InscriptionAnnuelleid INT
);

CREATE TABLE NOTEELEMENT (
  id INT PRIMARY KEY,
  noteavR INT,
  noteapR INT,
  valideavR INT,
  valideapR INT,
  note FLOAT,
  elementID INT,
  EtudiantID INT,
  FOREIGN KEY (elementID) REFERENCES ELEMENT(idElement),
  FOREIGN KEY (EtudiantID) REFERENCES ETUDIANT(IDetudiant)
);
  CREATE TABLE NOTEMODULE (
  id INT PRIMARY KEY,
  noteavR INT,
  noteapR INT,
  valideavR INT,
  valideapR INT,
  notemodule FLOAT,
  moduleID INT,
  EtudiantID INT,
  FOREIGN KEY (moduleID) REFERENCES MODULE(IDmodule),
  FOREIGN KEY (EtudiantID) REFERENCES ETUDIANT(IDetudiant)
);
CREATE TABLE INSCRIPTION (
  IDEtudiant INT,
  CNE VARCHAR(50),
  Nom VARCHAR(255),
  Prenom VARCHAR(255),
  IDNiveauActuel INT,
  Type VARCHAR(50),
  FOREIGN KEY (IDEtudiant) REFERENCES ETUDIANT(IDetudiant),
  FOREIGN KEY (IDNiveauActuel) REFERENCES NIVEAU(IDniveau)
);
INSERT INTO INSCRIPTION (IDEtudiant, CNE, Nom, Prenom, IDNiveauActuel, Type)
VALUES
  (2, 'B987654321', 'HAI', 'YASSINE', 10, 'Reinscription'),
  (4, 'D135792468', 'Houbaoui', 'Mimoune', 5, 'Reinscription'),
  (6, 'F578932641', 'Mahroug', 'Aymane', 17, 'Reinscription'),
  (8, 'H506927381', 'El Yacoubi', 'Alae', 8, 'Reinscription'),
  (10, 'J728145903', 'Benlamkadam', 'Zakaria', 19, 'Reinscription'),
  (12, 'L102938475', 'El Haouzi', 'Ayoub', 3, 'Reinscription'),
  (14, 'N840271563', 'El Amrani', 'Karima', 9, 'Reinscription'),
  (16, 'P470162938', 'Bouziane', 'Leila', 11, 'Reinscription'),
  (22, 'V765021493', 'El Amrani', 'Malika', 13, 'Reinscription'),
  (24, 'X905718432', 'El Haddad', 'Karim', 24, 'Reinscription'),
  (26, 'Z372104984', 'Madani', 'Aissa', 2, 'Reinscription'),
  (27, 'Z372104985', 'El Khayat', 'Mustapha', 23, 'Reinscription'),
  (28, 'A183947205', 'El Kharroubi', 'Leila', 30, 'Reinscription'),
  (29, 'B820354691', 'El Mansouri', 'Hassan', 2, 'Reinscription'),
  (31, 'D518209673', 'El Harrak', 'Abdellah', 16, 'Reinscription'),
  (33, 'F254960183', 'El Kabbaj', 'Amine', 21, 'Reinscription'),
  (35, 'H932076415', 'El Mernissi', 'Karim', 28, 'Reinscription'),
  (37, 'J617239584', 'El Malki', 'Khalid', 26, 'Reinscription'),
  (39, 'L346192758', 'El Moussaoui', 'Ahmed', 12, 'Reinscription'),
  (42, 'T452607198', 'El Mansouri', 'Youssef', 10, 'Reinscription'),
  (44, 'P759248163', 'El Kabbaj', 'Younes', 5, 'Reinscription'),
  (48, 'Y829317406', 'El Fassi', 'Nada', 8, 'Reinscription'),
  (52, 'X518932064', 'El Haouzi', 'Karim', 3, 'Reinscription'),
  (56, 'G457021896', 'Bouziane', 'Imane', 11, 'Reinscription'),
  (58, 'F279504618', 'Hamidi', 'Asma', 21, 'Reinscription'),
  (60, 'M903726451', 'El Hachimi', 'Salma', 6, 'Reinscription'),
  (62, 'C596281407', 'El Amrani', 'Dounia', 13, 'Reinscription'),
  (64, 'I713042958', 'El Haddad', 'Samir', 24, 'Reinscription'),
  (66, 'Z615892374', 'El Khayat', 'Tarik', 23, 'Reinscription'),
  (68, 'O284061597', 'El Mansouri', 'Mohamed', 2, 'Reinscription'),
  (70, 'T526031498', 'El Harrak', 'Ahmed', 16, 'Reinscription'),
  (72, 'N618297534', 'El Kabbaj', 'Nabil', 21, 'Reinscription'),
  (74, 'Z670923581', 'El Mernissi', 'Yassine', 28, 'Reinscription'),
  (76, 'M829175043', 'El Malki', 'Hicham', 26, 'Reinscription'),
  (78, 'K731960482', 'El Moussaoui', 'Abdelilah', 12, 'Reinscription'),
  (82, 'V830561297', 'El Malki', 'Salim', 10, 'Reinscription'),
  (84, 'L943016782', 'El Kabbaj', 'Tarik', 5, 'Reinscription'),
  (86, 'I586240913', 'Zouhair', 'Noura', 19, 'Reinscription'),
  (87, 'C318905627', 'El Haouzi', 'Karima', 14, 'Reinscription'),
  (88, 'E709461825', 'Ait Said', 'Youssef', 27, 'Reinscription'),
  (89, 'J437825901', 'El Amrani', 'Lamia', 9, 'Reinscription'),
  (90, 'Q190583627', 'Ouazzani', 'Reda', 18, 'Reinscription');
[11:59 AM, 26/05/2023] +212 649-205989: ALTER TABLE INSCRIPTION DROP COLUMN IDEtudiant, DROP COLUMN IDNiveauActuel;

-- Ajouter les nouvelles colonnes IDEtudiant et IDNiveauActuel
ALTER TABLE INSCRIPTION ADD COLUMN IDEtudiant INT, ADD COLUMN IDNiveauActuel INT;

-- Ajouter les contraintes de clé étrangère
ALTER TABLE INSCRIPTION ADD CONSTRAINT FK_ETUDIANT_IDEtudiant FOREIGN KEY (IDEtudiant) REFERENCES ETUDIANT(IDetudiant);
ALTER TABLE INSCRIPTION ADD CONSTRAINT FK_NIVEAU_IDNiveauActuel FOREIGN KEY (IDNiveauActuel) REFERENCES NIVEAU(IDniveau);