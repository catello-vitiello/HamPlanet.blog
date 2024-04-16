USE freedb_HPBlog_db;

-- popolamento tabelle Ham_user
insert into Ham_user(userName, email, passwd, competenze, ruolo) values
                                                                     ('Nicola_Nappi','n@n.it',sha2('nappi', 512),'so fare i prosciutti affumicati!', 'profile'),
                                                                     ('Catello_Vitiello','c@v.it',sha2('vitiello', 512), 'so fare gli sformati una favola!', 'profile'),
                                                                     ('Vincenzo_Danese','v@d.it',sha2('danese', 512), 'So fare un sacco di cose di prosciuttifici!', 'profile'),
                                                                     ('Giovanni_Russo','g@r.it',sha2('russo', 512), 'so fare le soppressate!', 'profile'),

                                                                     ('Pippo', 'pippo@p.it', sha2('p', 512), NULL, 'utente'),
                                                                     ('Pluto', 'pluto@p.it', sha2('p', 512), NULL, 'utente'),
                                                                     ('Paperino', 'papero@p.it', sha2('p', 512), NULL, 'utente'),
                                                                     ('Topolino', 'topo@p.it', sha2('p', 512), NULL, 'utente'),

                                                                     ('Vegetto', 'sbocciario@hamplanet.blog.it', sha2('Freezer@91', 512), NULL, 'supervisore'),
                                                                     ('Rosso', 'm.rossi@live.it', sha2('a', 512), 'Sono un esperto nella produzione di insaccati', 'profile');


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