USE freedb_HPBlog_db;

-- popolamento tabelle Ham_user
insert into Ham_user(userName, email, passwd, competenze, ruolo) values

                                                                     ('Nicola_Nappi','n@n.it','$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==','so fare i prosciutti affumicati!', 'content_writer'),
                                                                     ('Catello_Vitiello','c@v.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'so fare gli sformati una favola!', 'content_writer'),
                                                                     ('Vincenzo_Danese','v@d.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'So fare un sacco di cose di prosciuttifici!', 'content_writer'),
                                                                     ('Giovanni_Russo','g@r.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'so fare le soppressate!', 'content_writer'),


                                                                     ('Pippo', 'pippo@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),
                                                                     ('Pluto', 'pluto@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),
                                                                     ('Paperino', 'papero@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),
                                                                     ('Topolino', 'topo@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),


                                                                     ('Vegetto', 'sbocciario@hamplanet.blog.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'supervisore'),
                                                                     ('Rosso', 'm.rossi@live.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'Sono un esperto nella produzione di insaccati', 'content_writer');



-- POPOLAMENTO DELLA TABELLA POST
insert into Post(nomepost, testo, idcontent_writer) values
                                                        ('Post_1', 'I prosciutti sono troppo bellissimi', 1),
                                                        ('Post_2', 'Evviva la bresaola', 2),
                                                        ('Post_3', 'Il capocollo è troppo saporito', 3),
                                                        ('Post_4', 'Spider-man mangiava tanto salame napoletano', 4);

-- POPOLAMENTO DELLA TABELLA COMMENTO
insert into Commento(idpost, iduser, contenutocommento) values
                                                            (4,5,'No, io sapevo mangiasse solo bresaola...'),
                                                            (4,6,'Nemmeno io lo sapevo, assurdo!!!');

insert into Commento(idpost, iduser, contenutocommento) values (4,4,'Eh EH ragazzi, dovete ancora imparare molto, sull\'amichevole Spider-man di quartiere');

-- POPOLAMENTO DELLA TABELLA Like_
insert into Like_(id_user, id_post)
values(5,1),
      (6,2),
      (7,3),
      (8,4);


-- POPOLAMENTO DELLA TABELLA TOKEN SENZA ASSEGNARLI
INSERT INTO Tokens(token) VALUES
                              (generateRandomStringFunction()),
                              (generateRandomStringFunction()),
                              (generateRandomStringFunction());

-- ASSEGNO IL TOKEN AD UN SEPERVISORE
UPDATE Tokens
SET overseer = 1
WHERE overseer IS NULL
LIMIT 1;