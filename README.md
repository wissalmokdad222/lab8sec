# Projet WS — Gestion des Étudiants (PHP + MySQL + Android)

---

## Partie 1 — Création de la base de données MySQL

### 1. Démarrage de XAMPP
- Lancer **Apache** et **MySQL**.  
- Accéder à **phpMyAdmin** via : [http://localhost/phpmyadmin](http://localhost/phpmyadmin)

**Capture d’écran :**  
![Capture XAMPP](URL_IMAGE_PUBLIC)

### 2. Création de la base de données

### 3. Création de la table Etudiant
```sql
CREATE TABLE Etudiant (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(50),
  prenom VARCHAR(50),
  ville VARCHAR(50),
  sexe VARCHAR(10)
);
```
#### Capture d’écran :
![](https://github.com/user-attachments/assets/500d25d5-b2a4-448b-b2b9-0eb1ef7feb84)

### 4. Ajout de données tests
#### Capture d’écran :
![](https://github.com/user-attachments/assets/f3aac50d-6323-4c28-ab35-54c24ab2a927)

## Partie 2 — Développement du Web Service PHP
### 1. Structure du projet
C:\xampp\htdocs\projet
│
├─ classes/
├─ connexion/
├─ dao/
├─ service/
├─ controller/
└─ ws/

#### Capture d’écran :

![](https://github.com/user-attachments/assets/f3aac50d-6323-4c28-ab35-54c24ab2a927)
![](https://github.com/user-attachments/assets/a514c998-8258-4f68-a62c-1f4e4430ae5c)
### 2. Test des Web Services
#### a) Ajouter un étudiant (POST)

URL : http://localhost/projet/ws/createEtudiant.php

![](https://github.com/user-attachments/assets/c078406a-98e2-49a7-898a-8180ebff887e)
![](https://github.com/user-attachments/assets/5e5ca8a1-0487-4008-8700-b25c3d540a53)

#### b) Lire tous les étudiants (GET)

```sql URL : http://localhost/projet/ws/loadEtudiant.php ``
![](https://github.com/user-attachments/assets/4fe89443-4b4d-4483-b3de-549cb4144888)

## Partie 3 — Application Android (Volley + Gson)
#### Capture d’écran :
![](https://github.com/user-attachments/assets/827c81e8-6991-4184-b2b9-4335a4920f93)


###  Vérification dans phpMyAdmin

L’enregistrement ajouté depuis Android apparaît dans la table.

Capture d’écran :

![](https://github.com/user-attachments/assets/adedae5d-4fc4-4adb-9dfa-6f795c0b36c7)

## Partie 5 — Remarques

Communication Android ↔ PHP : HTTP POST / GET

Échange des données au format JSON, parsé avec Gson

Android 9+ : trafic HTTP autorisé via network_security_config.xml

