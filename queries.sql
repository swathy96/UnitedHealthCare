
DROP TABLE IF EXISTS address;
CREATE TABLE address (
  id int(11) NOT NULL AUTO_INCREMENT,
  street varchar(45) NOT NULL,
  city varchar(45) NOT NULL,
  state varchar(45) NOT NULL,
  country varchar(45) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO address VALUES (1,'A K Road','Bangalore','Karnataka','India'),(2,'Kalyan Lane','Bangalore','Karnataka','India'),(3,'RaviKiran Road','Bangalore','Karnataka','India'),(4,'5th Avenue','Bangalore','Karnataka','India'),(5,'12th Cross','Bangalore','Karnataka','India');


DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  description varchar(45) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO role VALUES (1,'Manager','Manager'),(2,'Receptionist','Receptionist'),(3,'Doctor','Doctor'),(4,'Patient','Patient');


DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  contact_no varchar(45) NOT NULL,
  dob date NOT NULL,
  age int(11) NOT NULL,
  blood_group varchar(45) NOT NULL,
  address_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO user VALUES (1,'Harish','UHCDHAR1','123','harish@gmail.com','9632587410','1895-02-12',35,'AB+ve',1,3),(2,'Shruti','UHCDSHR2','123','shruti@gmail.com','6598320147','1895-06-21',35,'O-ve',3,3),(3,'Aditya','UHCDADI3','123','aditya@gmail.com','9856301247','1895-12-03',35,'A-ve',2,3),(4,'Charmi','UHCDCHA4','123','charmi@gmail.com','8745960321','1895-09-09',35,'B+ve',1,3),(5,'Jitesh','UHCDJIT5','123','jitesh@gmail.com','8856431029','1895-11-16',35,'O+ve',4,3),(6,'Karthik','UHCPKAR6','111','karthik@gmail.com','7226350178','1895-11-22',35,'O+ve',1,4),(7,'Ritesh','UHCPRIT7','123','ritesh@gmail.com','7853264109','1895-06-02',35,'B+ve',3,4),(8,'Leela','UHCPLEE8','123','leela@gmail.com','8564792033','1895-12-14',35,'A-ve',2,4),(9,'Saru','UHCPSAR9','123','saru@gmail.com','7765994582','1895-02-27',35,'O-ve',1,4),(10,'Hema','UHCPHEM10','123','hema@gmail.com','6332211087','1895-09-18',35,'AB+ve',4,4),(11,'Reena','REENA11','111','reena@gmail.com','7002656369','1895-12-03',35,'O+ve',1,2),(12,'Sridevi','SRIDEVI','111','sridevi@gmail.com','9620368646','1895-12-04',35,'B+ve',2,1);


DROP TABLE IF EXISTS receptionist;
CREATE TABLE receptionist (
  id int(11) NOT NULL,
  qualification varchar(45) NOT NULL,
  experience int(11) NOT NULL,
  status varchar(45) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO receptionist VALUES (11,'B Com',3,'Working');


DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
  id int(11) NOT NULL,
  patient_id varchar(45) NOT NULL,
  emergency_contact_name varchar(45) NOT NULL,
  emergency_contact_no varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY patient_id_UNIQUE (patient_id),
  FOREIGN KEY (id) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO patient VALUES (6,'UHCPKAR6','Ravish','8622110031'),(7,'UHCPRIT7','Ashraf','6110069853'),(8,'UHCPLEE8','Rahul','7009658120'),(9,'UHCPSAR9','Rajeev','9260113385'),(10,'UHCPHEM10','Hina','9664588756');


DROP TABLE IF EXISTS medical_record;
CREATE TABLE medical_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  upload_date date NOT NULL,
  patient_id varchar(45) NOT NULL,
  document blob NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (patient_id) REFERENCES patient (patient_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS doctor;
CREATE TABLE doctor (
  id int(11) NOT NULL,
  doctor_id varchar(45) NOT NULL,
  qualification varchar(45) NOT NULL,
  specialisation varchar(45) NOT NULL,
  experience int(11) NOT NULL,
  consultation_fees double NOT NULL,
  status varchar(45) NOT NULL,
  maximum_patient_per_slot varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY doctor_id_UNIQUE (doctor_id),
  FOREIGN KEY (id) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO doctor VALUES (1,'UHCDHAR1','MBBS','General',1,100,'Working','3'),(2,'UHCDSHR2','MBBS','General',2,160,'Working','3'),(3,'UHCDADI3','MBBS','General',1,120,'Working','3'),(4,'UHCDCHA4','MBBS','General',3,250,'Working','3'),(5,'UHCDJIT5','MBBS','General',2,180,'Relieved','3');


DROP TABLE IF EXISTS consultation_slot;
CREATE TABLE consultation_slot (
  id int(11) NOT NULL AUTO_INCREMENT,
  day varchar(45) NOT NULL,
  hours varchar(45) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO consultation_slot VALUES (1,'Sunday','9:00 to 10:00'),(2,'Sunday','10:00 to 11:00'),(3,'Sunday','11:00 to 12:00'),(4,'Sunday','12:00 to 13:00'),(5,'Sunday','13:00 to 14:00'),(6,'Sunday','14:00 to 15:00'),(7,'Sunday','15:00 to 16:00'),(8,'Sunday','16:00 to 17:00'),(9,'Sunday','17:00 to 18:00'),(10,'Monday','9:00 to 10:00'),(11,'Monday','10:00 to 11:00'),(12,'Monday','11:00 to 12:00'),(13,'Monday','12:00 to 13:00'),(14,'Monday','13:00 to 14:00'),(15,'Monday','14:00 to 15:00'),(16,'Monday','15:00 to 16:00'),(17,'Monday','16:00 to 17:00'),(18,'Monday','17:00 to 18:00'),(19,'Tuesday','9:00 to 10:00'),(20,'Tuesday','10:00 to 11:00'),(21,'Tuesday','11:00 to 12:00'),(22,'Tuesday','12:00 to 13:00'),(23,'Tuesday','13:00 to 14:00'),(24,'Tuesday','14:00 to 15:00'),(25,'Tuesday','15:00 to 16:00'),(26,'Tuesday','16:00 to 17:00'),(27,'Tuesday','17:00 to 18:00'),(28,'Wednesday','9:00 to 10:00'),(29,'Wednesday','10:00 to 11:00'),(30,'Wednesday','11:00 to 12:00'),(31,'Wednesday','12:00 to 13:00'),(32,'Wednesday','13:00 to 14:00'),(33,'Wednesday','14:00 to 15:00'),(34,'Wednesday','15:00 to 16:00'),(35,'Wednesday','16:00 to 17:00'),(36,'Wednesday','17:00 to 18:00'),(37,'Thursday','9:00 to 10:00'),(38,'Thursday','10:00 to 11:00'),(39,'Thursday','11:00 to 12:00'),(40,'Thursday','12:00 to 13:00'),(41,'Thursday','13:00 to 14:00'),(42,'Thursday','14:00 to 15:00'),(43,'Thursday','15:00 to 16:00'),(44,'Thursday','16:00 to 17:00'),(45,'Thursday','17:00 to 18:00'),(46,'Friday','9:00 to 10:00'),(47,'Friday','10:00 to 11:00'),(48,'Friday','11:00 to 12:00'),(49,'Friday','12:00 to 13:00'),(50,'Friday','13:00 to 14:00'),(51,'Friday','14:00 to 15:00'),(52,'Friday','15:00 to 16:00'),(53,'Friday','16:00 to 17:00'),(54,'Friday','17:00 to 18:00'),(55,'Saturday','9:00 to 10:00'),(56,'Saturday','10:00 to 11:00'),(57,'Saturday','11:00 to 12:00'),(58,'Saturday','12:00 to 13:00'),(59,'Saturday','13:00 to 14:00'),(60,'Saturday','14:00 to 15:00'),(61,'Saturday','15:00 to 16:00'),(62,'Saturday','16:00 to 17:00'),(63,'Saturday','17:00 to 18:00');


DROP TABLE IF EXISTS doctor_consultation_slot;
CREATE TABLE doctor_consultation_slot (
  id int(11) NOT NULL AUTO_INCREMENT,
  doctor_id varchar(45) NOT NULL,
  consultation_slot_id int(11) NOT NULL,
  start_date date NOT NULL,
  end_date date NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (consultation_slot_id) REFERENCES consultation_slot (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO doctor_consultation_slot VALUES (1,'UHCDHAR1',1,'2020-01-01','2020-03-01'),(2,'UHCDHAR1',2,'2020-01-01','2020-03-01'),(3,'UHCDHAR1',3,'2020-01-01','2020-03-01'),(4,'UHCDHAR1',4,'2020-01-01','2020-03-01'),(5,'UHCDHAR1',7,'2020-01-01','2020-03-01'),(6,'UHCDHAR1',8,'2020-01-01','2020-03-01'),(7,'UHCDHAR1',9,'2020-01-01','2020-03-01'),(8,'UHCDSHR2',1,'2020-01-01','2020-03-01'),(9,'UHCDSHR2',2,'2020-01-01','2020-03-01'),(10,'UHCDSHR2',3,'2020-01-01','2020-03-01'),(11,'UHCDSHR2',4,'2020-01-01','2020-03-01'),(12,'UHCDSHR2',7,'2020-01-01','2020-03-01'),(13,'UHCDSHR2',8,'2020-01-01','2020-03-01'),(14,'UHCDSHR2',9,'2020-01-01','2020-03-01'),(15,'UHCDADI3',1,'2020-01-01','2020-03-01'),(16,'UHCDADI3',2,'2020-01-01','2020-03-01'),(17,'UHCDADI3',3,'2020-01-01','2020-03-01'),(18,'UHCDADI3',4,'2020-01-01','2020-03-01'),(19,'UHCDADI3',7,'2020-01-01','2020-03-01'),(20,'UHCDADI3',8,'2020-01-01','2020-03-01'),(21,'UHCDADI3',9,'2020-01-01','2020-03-01'),(22,'UHCDCHA4',1,'2020-01-01','2020-03-01'),(23,'UHCDCHA4',2,'2020-01-01','2020-03-01'),(24,'UHCDCHA4',3,'2020-01-01','2020-03-01'),(25,'UHCDCHA4',4,'2020-01-01','2020-03-01'),(26,'UHCDCHA4',7,'2020-01-01','2020-03-01'),(27,'UHCDCHA4',8,'2020-01-01','2020-03-01'),(28,'UHCDCHA4',9,'2020-01-01','2020-03-01'),(29,'UHCDJIT5',1,'2020-01-01','2020-03-01'),(30,'UHCDJIT5',2,'2020-01-01','2020-03-01'),(31,'UHCDJIT5',3,'2020-01-01','2020-03-01'),(32,'UHCDJIT5',4,'2020-01-01','2020-03-01'),(33,'UHCDJIT5',7,'2020-01-01','2020-03-01'),(34,'UHCDJIT5',8,'2020-01-01','2020-03-01'),(35,'UHCDJIT5',9,'2020-01-01','2020-03-01');


DROP TABLE IF EXISTS leave_detail;
CREATE TABLE leave_detail (
  id int(11) NOT NULL AUTO_INCREMENT,
  leave_date date NOT NULL,
  doctor_id varchar(45) NOT NULL,
  doctor_consultation_slot_id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (doctor_consultation_slot_id) REFERENCES doctor_consultation_slot (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO leave_detail VALUES (1,'2020-01-05','UHCDSHR2',9),(2,'2020-01-05','UHCDSHR2',10);


DROP TABLE IF EXISTS status;
CREATE TABLE status (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO status VALUES (1,'Upcoming'),(2,'Consulted'),(3,'Cancelled');


DROP TABLE IF EXISTS appointment;
CREATE TABLE appointment (
  id int(11) NOT NULL AUTO_INCREMENT,
  visiting_date date NOT NULL,
  booking_date date NOT NULL,
  user_id int(11) NOT NULL,
  patient_id varchar(45) NOT NULL,
  doctor_id varchar(45) NOT NULL,
  doctor_consultation_slot_id int(11) NOT NULL,
  status_id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (doctor_consultation_slot_id) REFERENCES doctor_consultation_slot (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (patient_id) REFERENCES patient (patient_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (status_id) REFERENCES status (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO appointment VALUES (1,'2020-01-05','2020-01-03',11,'UHCPKAR6','UHCDHAR1',1,1),(2,'2020-01-05','2020-01-03',11,'UHCPRIT7','UHCDHAR1',2,2),(3,'2020-01-05','2020-01-03',11,'UHCPLEE8','UHCDJIT5',3,1),(4,'2020-01-05','2020-01-03',11,'UHCPRIT7','UHCDJIT5',4,1),(5,'2020-02-09','2020-01-03',11,'UHCPRIT7','UHCDCHA4',1,1),(6,'2020-01-05','2020-01-03',11,'UHCPLEE8','UHCDSHR2',1,1),(7,'2020-01-05','2020-01-03',11,'UHCPRIT7','UHCDSHR2',7,1),(8,'2020-01-05','2020-01-03',11,'UHCPSAR9','UHCDSHR2',2,1),(9,'2020-01-05','2020-01-03',11,'UHCPKAR6','UHCDSHR2',4,1),(10,'2020-01-05','2020-01-03',11,'UHCPHEM10','UHCDSHR2',6,1),(11,'2020-02-09','2020-01-03',11,'UHCPRIT7','UHCDADI3',5,1),(12,'2020-01-05','2020-01-03',11,'UHCPSAR9','UHCDADI3',7,3),(13,'2020-01-05','2020-02-05',11,'UHCPKAR6','UHCDSHR2',9,3),(14,'2020-02-09','2020-02-05',11,'UHCPLEE8','UHCDSHR2',9,2),(15,'2020-02-09','2020-02-05',11,'UHCPSAR9','UHCDHAR1',9,2);


DROP TABLE IF EXISTS cancellation;
CREATE TABLE cancellation (
  id int(11) NOT NULL AUTO_INCREMENT,
  reason varchar(45) NOT NULL,
  appointment_id int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (appointment_id) REFERENCES appointment (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO cancellation VALUES (1,'Change of appointment date',12),(2,'No Show',13);


