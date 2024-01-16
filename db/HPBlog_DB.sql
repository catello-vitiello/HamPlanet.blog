DROP DATABASE IF EXISTS HPBlog_DB;
CREATE DATABASE HPBlog_DB;

DROP USER IF EXISTS 'HpB_user'@'%';
CREATE USER 'HpB_user'@'%' IDENTIFIED BY 'Prosciutto_69';
GRANT ALL PRIVILEGES ON HPBlog_DB.* TO 'HpB_user'@'%';

USE HPBlog_DB;

DROP TABLE IF EXISTS Content_Writer;
DROP TABLE IF EXISTS Utente;
DROP TABLE IF EXISTS Post;
DROP TABLE IF EXISTS Commento;

CREATE TABLE Content_Writer
(
    id INT AUTO_INCREMENT,
    userName VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    passwd VARCHAR(600) NOT NULL,
    competenze TEXT,
    PRIMARY KEY(id)
);
CREATE TABLE Utente
(
    id INT AUTO_INCREMENT,
    userName VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    passwd VARCHAR(60) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE Post
(
    id INT AUTO_INCREMENT,
    nomePost VARCHAR(200) NOT NULL,
    testo TEXT NOT NULL,
    img MEDIUMBLOB,
    numLike INT UNSIGNED DEFAULT 0,
    idContent_Writer int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(idContent_Writer) REFERENCES Content_Writer(id) ON DELETE CASCADE
);

CREATE TABLE Commento
(
    id INT AUTO_INCREMENT,
    idPost int NOT NULL,
    idUtente int DEFAULT NULL, -- chiave opzionale
    idContent_Writer int DEFAULT NULL, -- chiave opzionale
    contenutoCommento TEXT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (idPost) REFERENCES Post(id) ON DELETE CASCADE,
    FOREIGN KEY (idUtente) REFERENCES Utente(id) ON DELETE CASCADE,
    FOREIGN KEY(idContent_Writer) REFERENCES Content_Writer(id) ON DELETE CASCADE,
    CHECK(
            (idContent_Writer IS NOT NULL AND idUtente IS NULL) OR
            (idContent_Writer IS NULL AND idUtente IS NOT NULL)
        )
);