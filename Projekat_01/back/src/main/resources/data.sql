INSERT INTO `user` (id, username, password, role)
              VALUES (1,'miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','ADMIN');
INSERT INTO `user` (id, username, password, role)
              VALUES (2,'tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','KORISNIK');
INSERT INTO `user` (id, username, password, role)
              VALUES (3,'petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','KORISNIK');
              
INSERT INTO kategorija (id, naziv) VALUES (1, "KUHINJA");  
INSERT INTO kategorija (id, naziv) VALUES (2, "GEJMING");  
INSERT INTO kategorija (id, naziv) VALUES (3, "UREDJAJI");  


INSERT INTO proizvod (id, cena, naziv, stanje, kategorija_id) VALUES (1, 1500, "MIS", 10, 2);
INSERT INTO proizvod (id, cena, naziv, stanje, kategorija_id) VALUES (2, 2500, "SPORET", 10, 1);
INSERT INTO proizvod (id, cena, naziv, stanje, kategorija_id) VALUES (3, 1500, "USISIVAC", 10, 3);
INSERT INTO proizvod (id, cena, naziv, stanje, kategorija_id) VALUES (4, 150, "SOLJA", 12, 1);
INSERT INTO proizvod (id, cena, naziv, stanje, kategorija_id) VALUES (5, 1400, "FEN", 2, 3);


INSERT INTO porudzbina (id, kolicina, proizvod_id) VALUES (1, 5, 3);    
INSERT INTO porudzbina (id, kolicina, proizvod_id) VALUES (2, 2, 2);            
INSERT INTO porudzbina (id, kolicina, proizvod_id) VALUES (3, 6, 1);                    