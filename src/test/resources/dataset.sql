insert into account values (1, 'pepito@email.com', '123');
insert into account values (2, 'pepa@email.com', '1234');
insert into account values (3, 'pedro@email.com', '12345');
insert into account values (4, 'Juan@email.com', '12');
insert into account values (5, 'Jose@email.com', '12358');
insert into account values (6, 'luis@email.com', '1425');
insert into account values (7, 'haker@email.com', 'root');
insert into account values (8, 'anonimus@email.com', 'root1');

insert into admin values (7);
insert into admin values (8);

insert into patient values (ARMENIA,123,'pepito','1234556','url_picture','no allergies','2001-10-01',
                            AB_NEGATIVE, COOMEVA, ASSET,1);
insert into patient values (BOGOTA,1234,'pepa','325145','url_picture','no allergies','1998-07-01',
                            AB_POSITIVE,SURA, ASSET,2);
insert into patient values (CALI,1235,'pedro','4316123','url_picture','no allergies','2001-08-01',
                            A_NEGATIVE,SANITAS, ASSET,3);

insert into doctor values ("ARMENIA", 11223, 'Juan', '14353415','url_picture',"AVAILABLE", "CARDIOLOGIST",4);
insert into doctor values ("BOGOTA", 2341, 'Jose', '234511','url_picture',"AVAILABLE", "OPHTHALMOLOGY",5);
insert into doctor values ("CALI", 3241, 'luis', '23511432','url_picture',"AVAILABLE", "SURGERY",6);

insert into schedule values (1, 'monday', '12:34:56.123456','8:34:56.123456',4);
insert into schedule values (2, 'thursday', '12:34:56.123456','8:34:56.123456',5);
insert into schedule values (3, 'monday', '15:34:56.123456','8:34:56.123456',6);

insert into free_day values (1,'2023-12-31', 4 );
insert into free_day values (2,'2023-12-31', 5 );
insert into free_day values (3,'2023-12-31', 6 );

insert into appointment values (1,'2023-12-01 13:34:56.123456', "PENDING", '2023-10-19 09:34:56.123456',
                                'I have something', 4, 1);
insert into appointment values (2,'2023-11-01 14:34:56.123456', "PENDING", '2023-10-18 13:34:56.123456',
                                'I have something', 5, 2);
insert into appointment values (3,'2023-11-01 12:34:56.123456', "PENDING", '2023-10-17 05:34:56.123456',
                                'I have something', 6, 3);

insert into attention values (1, 'cancer', 'nothing', 'acetaminofen', 1 );
insert into attention values (2, 'cancer', 'nothing', 'caldo de pescado', 2 );
insert into attention values (3, 'cancer', 'nothing', 'acetaminofen x2', 3 );

insert into petition values (1,'2023-12-01 16:34:56.123456', "PENDING", 'XD', "CLAIMS", 1 );
insert into petition values (2,'2023-11-01 13:34:56.123456', "PENDING", 'the doctor is a  fool', "CLAIMS", 2 );
insert into petition values (3,'2023-12-01 15:34:56.123456', "PENDING", 'what???', "CLAIMS", 3 );

insert into message values (1, '2023-10-21 10:34:56.123456','Hi',1,2,1);
insert into message values (2, '2023-10-21 11:34:56.123456','Hi, how are u',8,3,1);
insert into message values (3, '2023-10-21 12:34:56.123456','fine thank u',1,4,1);
insert into message values (4, '2023-10-21 12:34:56.123456','ok',8,4,1);

