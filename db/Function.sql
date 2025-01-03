use hamplanet;

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



