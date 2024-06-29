-- procedura che genera una stringa di caratteri alfanumerica casuale e la inserisce mìnel campo token della tabella Tokens
DROP PROCEDURE IF EXISTS generateRandomStringAndInsert;
DELIMITER //
CREATE PROCEDURE generateRandomStringAndInsert()
BEGIN
    DECLARE chars VARCHAR(62) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    DECLARE random_string VARCHAR(10) DEFAULT '';
    DECLARE i INT DEFAULT 0;

    -- START TRANSACTION;

    WHILE i < 10 DO
        SET random_string = CONCAT(random_string, SUBSTRING(chars, FLOOR(1 + RAND() * 62), 1));
        SET i = i + 1;
    END WHILE;

    INSERT INTO tokens(token) VALUES (random_string);

    -- COMMIT;
END 
//
DELIMITER ;

-- funzione che restituisce una stringa casuale alfanumerica di 10 caratteri
DROP FUNCTION IF EXISTS generateRandomStringFunction;
DELIMITER //
CREATE FUNCTION generateRandomStringFunction() RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
    DECLARE chars VARCHAR(62) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    DECLARE random_string VARCHAR(10) DEFAULT '';
    DECLARE i INT DEFAULT 0;

    WHILE i < 10 DO
        SET random_string = CONCAT(random_string, SUBSTRING(chars, FLOOR(1 + RAND() * 62), 1));
        SET i = i + 1;
    END WHILE;

    RETURN random_string;
END
//
DELIMITER ;

-- trigger che inserisce un nuovo token nella tabella Tokens  quando viene eliminato un suoervisore
DROP TRIGGER IF EXISTS after_delete_trigger;
DELIMITER //
CREATE TRIGGER after_delete_trigger
AFTER DELETE ON Ham_user
FOR EACH ROW
BEGIN
    IF OLD.ruolo = 'supervisore' THEN
        CALL generateRandomStringAndInsert();
    END IF;
END;
//DELIMITER ;

-- trigger che elimina il profile quando gli viene rifiutato il profilo al momento della verifica
DROP TRIGGER IF EXISTS after_invalidContentWriter_trigger;
DELIMITER //
CREATE TRIGGER after_invalidContentWriter_trigger
AFTER UPDATE ON pending
FOR EACH ROW
BEGIN
	IF NEW.verify = 'rejected' THEN
		DELETE FROM Ham_user WHERE id = OLD.idContent_writer;
	END IF;
END;
//DELIMITER ;

-- trigger che ogni volta che viene creato un nuovo profilo profile esso viene messo in waiting
DROP TRIGGER IF EXISTS addContentWriterToQueue_trigger;
DELIMITER //
CREATE TRIGGER addContentWriterToQueue_trigger
AFTER INSERT ON Ham_user
FOR EACH ROW
BEGIN
	IF NEW.ruolo = 'content_writer' THEN
		INSERT INTO pending(idContent_writer) VALUES (NEW.id);
	END IF;
END;
// DELIMITER ;

