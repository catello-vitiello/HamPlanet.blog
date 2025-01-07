USE hamplanet;

-- popolamento tabelle Ham_user
insert into Ham_user(userName, email, passwd, competenze, ruolo) values

                                                                     ('Nicola Nappi','n@n.it','$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==','so fare i prosciutti affumicati!', 'content_writer'),
                                                                     ('Catello Vitiello','c@v.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'so fare gli sformati una favola!', 'content_writer'),
                                                                     ('Vincenzo Danese','v@d.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'So fare un sacco di cose di prosciuttifici!', 'content_writer'),
                                                                     ('Giovanni Russo','g@r.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'so fare le soppressate!', 'content_writer'),


                                                                     ('Pippo', 'pippo@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),
                                                                     ('Pluto', 'pluto@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),
                                                                     ('Paperino', 'papero@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),
                                                                     ('Topolino', 'topo@p.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'utente'),


                                                                     ('Vegetto', 'sbocciario@hamplanet.blog.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', NULL, 'supervisore'),
                                                                     ('Rosso', 'm.rossi@live.it', '$argon2id$v=19$m=65536,t=44,p=2$E+d2SNy8oie30LBSpv5vH32fB5ANDo0fmojUZuyV8cI=$sPeJnIiOcC6yMFr0/S5M80kwZi2IZGzSV3gsSa54xTaDWfbbEuzsE2JEXB9sd0waZQx06/IWAaqtIPn8ryIPX1yO4MZ/0vFEqtnrX6kayXVmqnO48ueH3ejxeWKOEVgwnFXnm6op0LOS7aPa1lPurGHTH16CRI/xx09m1wevN0A2PIgHyv2ezSmafky0TMNIXtcIRFIVbfwhQkXCPnRC40W6xXfFvKDSD2YauCKq24wUp4KE1LA/V4K5Pq/pD0qLlUOccqtsRNpYs6EhLxpHioY4reaD6l62gjnrt0iokhuQLBPgDqfZZjTfqHU61aST4q6KiQMJdoYCadq3kX7V0A==', 'Sono un esperto nella produzione di insaccati', 'content_writer');



-- POPOLAMENTO DELLA TABELLA POST
insert into Post(nomepost, testo, idcontent_writer) values
                                                        ('Tipi di prosciutti', 'Quasi altrettanto noto, in Italia, è il prosciutto San Daniele. Questo, secondo le norme del marchio DOP, può essere prodotto solo nel comune di San Daniele del Friuli: le quantità commercializzate sono quindi molto minori rispetto a quelle del prosciutto di Parma, realizzato in tutta l’omonima provincia. La particolarità di San Daniele è di trovarsi all’incrocio fra i venti provenienti dalle Alpi Carniche e le brezze del Mar Adriatico: il prosciutto, leggermente pressato per assorbire più uniformemente il sale marino, acquista così le note pungenti dell’aria di montagna e quelle più dolci e salmastre del mare. Nella preparazione del prosciutto San Daniele si conserva inoltre lo zampino, per mantenere il taglio integro e più facilmente conservabile.
                                                                    prosciutto venetoAnche il prosciutto Veneto Berico-Euganeo DOP, preparato nelle province di Vicenza, Padova e Verona, è leggermente pressato: è però privo di piedino, e la penetrazione del sale avviene anche grazie a una particolare tecnica di massaggio delle cosce di maiale. La stagionatura può essere di soli 10 mesi: il risultato è un prosciutto sodo ma morbido e dal gusto particolarmente delicato.
                                                                    prosciutto toscanoIl prosciutto toscano, prodotto DOP che può essere lavorato su tutto il territorio della regione, è invece ben tondeggiante e soprattutto molto saporito: si deve d’altronde accompagnare al tipico pane sciocco, cioè senza sale. Oltre al sale, per insaporire il crudo toscano si utilizzano alloro, rosmarino, bacche di ginepro e altre erbe aromatiche; alla fine della stagionatura, la cotenna è asportata a formare un taglio a V e il prosciutto ricoperto di grani di pepe.', 1),
                                                        ('Viva la bresaola', 'Il processo di produzione della bresaola inizia con una rigorosa selezione e rifilatura della carne, generalmente la punta d''anca di manzo, per eliminare il grasso in eccesso. La carne viene poi massaggiata con una miscela di sale e spezie, tra cui bacche di ginepro, cannella e noce moscata, e lasciata a riposare per alcuni giorni. Successivamente, segue un periodo di asciugatura che può durare da uno a tre mesi, durante il quale la carne perde fino al 40% del suo peso originale.
                                                                    Valori Nutrizionali
                                                                    La bresaola è apprezzata per il suo basso contenuto di grassi, circa il 3%, e l''elevato apporto proteico, con oltre 30 grammi di proteine per 100 grammi di prodotto, rendendola una scelta ideale per sportivi e persone che seguono diete ipocaloriche.
                                                                    Consumo e Abbinamenti
                                                                    Tradizionalmente servita come antipasto, la bresaola viene affettata sottilmente e condita con olio extravergine di oliva e succo di limone, spesso accompagnata da rucola e scaglie di Parmigiano Reggiano. Questo piatto semplice esalta il sapore delicato della bresaola ed è una scelta popolare nei mesi estivi per la sua leggerezza e freschezza.
                                                                    Varianti
                                                                    Oltre alla versione classica di manzo, esistono varianti di bresaola prodotte con carni diverse, come cavallo, cervo e maiale, ciascuna con caratteristiche organolettiche specifiche. Ad esempio, la bresaola di cavallo tende ad avere un sapore più deciso rispetto a quella di manzo. ', 2),
                                                        ('Capocollo, come cucinarlo', 'La storia del capocollo di maiale risale all’antica Roma. I Romani erano infatti grandi consumatori di salumi, e il capocollo era uno dei loro preferiti.
                                                                    Nel Medioevo, il capocollo si diffuse in tutta Italia, e divenne un alimento tipico della cucina contadina.
                                                                    Oggi, il capocollo è ancora un alimento molto popolare in Italia, e viene prodotto in molte regioni del paese.
                                                                    ', 3),
                                                        ('Come viene prodotto il salame napoletano', 'Il salame Napoli è simile ad altri salami tipici campani, ma si caratterizza per alcuni particolari. Viene preparato a partire da tagli suini pregiati come coppa, spalla e lombata ripuliti dal grasso di copertura, mentre il grasso utilizzato per il salame (non più del 25% dell’impasto) proviene dalla pancetta suina.

L’impasto ottenuto macinando carne e grasso viene infilato in budelli naturali, di suino o di vitello, per poi passare alla fase di asciugatura, durante la quale il salame viene affumicato. Per ultimo, una stagionatura di almeno 30 giorni, durante i quali l’insaccato matura le proprie caratteristiche in locali areati a temperatura e umidità costanti.', 4);

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