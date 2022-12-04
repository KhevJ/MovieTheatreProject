DROP DATABASE IF EXISTS MOVIE_THEATRE;
CREATE DATABASE MOVIE_THEATRE; 

USE MOVIE_THEATRE;

DROP TABLE IF EXISTS MOVIES;
CREATE TABLE MOVIES (
	MovieID		int not null AUTO_INCREMENT,
	MovieName			varchar(25),
	MovieTime    DATETIME ,
    Amount              int not null,  
	primary key (MovieID)
);

INSERT INTO MOVIES (MovieName, MovieTime, Amount)
VALUES
('Batman', '2023-01-11 11:30:30' , 10),
('Batman', '2023-01-12 12:00:00', 10),
('Superman','2023-01-12 12:00:00', 10);


DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
	UserID			int not null AUTO_INCREMENT,
	Name			varchar(50) UNIQUE,
	email       	varchar(50),
    password        varchar(50),
    creditCard       varchar(50),
    DateRegistered  DATETIME,
	primary key (UserID)
    
);


INSERT INTO USERS (Name, email,password, creditCard, DateRegistered)
VALUES
('Khevin', 'khevinjug@gmail.com' ,'khevin','1111111111111111', '2022-11-11 11:30:30'),
('Abdullah', 'abdullahkhubaib@gmail.com','abdullah','1111111111111112' ,'2022-11-11 11:30:30'),
('Scott','jsdelsing@icloud.com','scott','1111111111111113' ,'2022-11-11 11:30:30');




DROP TABLE IF EXISTS SEATS;
CREATE TABLE SEATS (
	SeatID			int not null AUTO_INCREMENT,
    FMovieID        int not null,
    Seats            char(9) DEFAULT   '111111111',     
	primary key (SeatID),
    
    FOREIGN KEY(FMovieID) REFERENCES MOVIES(MovieID)
	ON DELETE CASCADE ON UPDATE CASCADE 

);


DROP TABLE IF EXISTS TICKETS;
CREATE TABLE TICKETS (
	TicketID			int not null AUTO_INCREMENT,
    FMovieID        int not null,
    email            varchar(50),
    SeatNo            int not null,
    Amount              int not null,     
    DateBought         DATETIME,  
	primary key (TicketID),
  
    FOREIGN KEY(FMovieID) REFERENCES MOVIES(MovieID)
    ON DELETE CASCADE ON UPDATE CASCADE

);

DROP TABLE IF EXISTS CREDIT;
CREATE TABLE CREDIT (
	creditID			int not null AUTO_INCREMENT,
    email               varchar(25) UNIQUE,
    Amount              int not null,  
    dateCredit             DATETIME,
	primary key (creditID)

);

DELIMITER $$

CREATE TRIGGER after_movies_insert
AFTER INSERT
ON MOVIES FOR EACH ROW
INSERT INTO SEATS(FMovieID, Seats) VALUES (NEW.MovieID, '111111111')

DELIMITER ;

INSERT INTO SEATS (FMovieID , Seats)
VALUES
(1, '111101111' ),
(2, '111101111'),
(3,'110111111');


INSERT INTO CREDIT(email, Amount, dateCredit)
VALUES
('hajin@gmail.com', 850 ,'2022-11-11 11:30:30');

INSERT INTO TICKETS(FMovieID, email, SeatNo, Amount, DateBought)
VALUES
(3, 'khevinjug@gmail.com' , 3, 1000, '2022-12-5 11:30:30');