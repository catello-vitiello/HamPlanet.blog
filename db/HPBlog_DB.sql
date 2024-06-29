DROP DATABASE IF EXISTS freedb_HPBlog_db;
CREATE DATABASE freedb_HPBlog_db;
USE freedb_HPBlog_db;

DROP TABLE IF EXISTS Like_;
DROP TABLE IF EXISTS Commento;
DROP TABLE IF EXISTS Post;
DROP TABLE IF EXISTS Tokens;
DROP TABLE IF EXISTS pending;
DROP TABLE IF EXISTS Ham_user;


CREATE TABLE Ham_user
(
    id INT AUTO_INCREMENT,
    username VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    passwd VARCHAR(600) NOT NULL,
    competenze TEXT,
    ruolo enum('supervisore', 'utente', 'content_writer') DEFAULT 'utente',
    PRIMARY KEY(id)
);

CREATE TABLE Post
(
    id INT AUTO_INCREMENT,
    nomePost VARCHAR(200) NOT NULL,
    testo TEXT NOT NULL,
    idcontent_writer int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(idcontent_writer) REFERENCES Ham_user(id) ON DELETE CASCADE
);

CREATE TABLE Like_(
                      id_user INT NOT NULL,
                      id_post INT NOT NULL,
                      PRIMARY KEY(id_user, id_post),
                      FOREIGN KEY(id_user) REFERENCES Ham_user(id) ON DELETE CASCADE,
                      FOREIGN KEY(id_post) REFERENCES Post(id) ON DELETE CASCADE
);

CREATE TABLE Commento
(
    id INT AUTO_INCREMENT,
    idpost int NOT NULL,
    iduser int DEFAULT NULL,
    contenutocommento TEXT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (idpost) REFERENCES Post(id) ON DELETE CASCADE,
    FOREIGN KEY (iduser) REFERENCES Ham_user(id) ON DELETE CASCADE
);

CREATE TABLE Tokens
(
    token CHAR(10) PRIMARY KEY,
    overseer int NULL,
    FOREIGN KEY (overseer) REFERENCES Ham_user(id) ON DELETE CASCADE
);

CREATE TABLE pending
(
    idContent_writer int NOT NULL,
    verify enum('verified', 'waiting', 'rejected') DEFAULT 'waiting',
    PRIMARY KEY (idContent_writer),
    FOREIGN KEY (idContent_writer) REFERENCES Ham_user(id) ON DELETE CASCADE
);