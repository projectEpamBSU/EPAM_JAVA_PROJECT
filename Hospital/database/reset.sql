DROP TABLE IF EXISTS TREATMENT;
DROP TABLE IF EXISTS APPOINTMENT;
DROP TABLE IF EXISTS NURSE;
DROP TABLE IF EXISTS DOCTOR;
DROP TABLE IF EXISTS PATIENT;


CREATE TABLE PATIENT 
(
  ID_PATIENT INT NOT NULL 
, FIRST_NAME VARCHAR(20) NOT NULL 
, SECOND_NAME VARCHAR(20) NOT NULL 
, AGE INT NOT NULL 
, LOGIN VARCHAR(20) NOT NULL 
, PASSWORD VARCHAR(20) NOT NULL 
, RECOVERED BOOLEAN NOT NULL 
, CONSTRAINT PATIENT_PK PRIMARY KEY 
  (
    ID_PATIENT 
  )
);

INSERT INTO PATIENT
VALUES (1, 'Anatoliy', 'Tsoy', 55,'tsoyzhiv','kukushka', true);

INSERT INTO PATIENT
VALUES (2, 'Sergey', 'Trofimov', 44,'konyachok','shashlichok', false);


CREATE TABLE NURSE 
(
  ID_NURSE INT NOT NULL 
, FIRST_NAME VARCHAR(20) NOT NULL 
, SECOND_NAME VARCHAR(20) NOT NULL
, AGE INT NOT NULL
, LOGIN VARCHAR(20) NOT NULL 
, PASSWORD VARCHAR(20) NOT NULL 
, CONSTRAINT NURSE_PK PRIMARY KEY 
  (
    ID_NURSE 
  )
);

INSERT into Nurse
values(1,'Mariya', 'Sharapova', 21, 'sugarpova','byumysweets');

INSERT into Nurse
values(2,'Lyubov', 'Sharipova', 40, 'lushar','qqqq');

CREATE TABLE DOCTOR 
(
  ID_DOCTOR INT NOT NULL 
, FIRST_NAME VARCHAR(20) NOT NULL 
, SECOND_NAME VARCHAR(20) NOT NULL
, AGE INT NOT NULL
, LOGIN VARCHAR(20) NOT NULL 
, PASSWORD VARCHAR(20) NOT NULL 
, DEPARTMENT VARCHAR(20) NOT NULL
, IS_HEAD_OF_DEPARTMENT BOOLEAN NOT NULL
, CONSTRAINT DOCTOR_PK PRIMARY KEY 
  (
    ID_DOCTOR 
  )
);

Insert into DOCTOR
values(1,'Boris', 'Levin', 22, 'borlev','12345', 'traumatology', false);
Insert into DOCTOR
values(2,'Semen', 'Lobanov', 31, 'semlob', '54321', 'traumatology', false);


CREATE TABLE TREATMENT (
  ID_TREATMENT INT NOT NULL ,
  T_PROCEDURE VARCHAR(20)  NOT NULL ,
  MEDICINE VARCHAR(20)  NOT NULL ,
  OPERATION VARCHAR(20)  NOT NULL ,
  DIAGNOSE VARCHAR(20)  NOT NULL ,
  ID_APPOINTMENT INT  NOT NULL 
, CONSTRAINT TREATMENT_PK PRIMARY KEY 
  (
    ID_TREATMENT 
  )
);
insert into treatment
values(1, 'Computer Games', 'DOTA', 'Destroy opponents', 'Ospa', 1);

CREATE TABLE APPOINTMENT 
(
  ID_APPOINTMENT INT NOT NULL 
, ID_DOCTOR INT NOT NULL 
, ID_PATIENT INT NOT NULL 
, APPOINTMENT_DATE DATE NOT NULL 
, CONSTRAINT APPOINTMENT_PK PRIMARY KEY 
  (
    ID_APPOINTMENT 
  )
);

insert into appointment
values(1, 1, 1, '2019-12-11');
insert into appointment
values(2, 2, 2, '2020-01-13');

ALTER TABLE APPOINTMENT ADD CONSTRAINT fk_APPOINTMENT_ID_PATIENT FOREIGN KEY(ID_PATIENT)
REFERENCES PATIENT (ID_PATIENT);

ALTER TABLE TREATMENT ADD CONSTRAINT fk_TREATMENT_ID_APPOINTMENT FOREIGN KEY(ID_APPOINTMENT)
REFERENCES APPOINTMENT (ID_APPOINTMENT);

ALTER TABLE APPOINTMENT ADD CONSTRAINT fk_APPOINTMENT_ID_DOCTOR FOREIGN KEY(ID_DOCTOR)
REFERENCES DOCTOR (ID_DOCTOR);