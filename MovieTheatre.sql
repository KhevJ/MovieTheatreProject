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


DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
	UserID			int not null AUTO_INCREMENT,
	Name			varchar(50) UNIQUE,
	email       	varchar(50),
    DateRegistered  DATETIME,
    isRegistered    boolean DEFAULT FALSE,
	primary key (UserID)
    
);

DROP TABLE IF EXISTS SEATS;
CREATE TABLE SEATS (
	SeatID			int not null AUTO_INCREMENT,
    FMovieID        int not null,
    Seats            char(9) DEFAULT   '111111111',     
	primary key (SeatID),
    
    FOREIGN KEY(FMovieID) REFERENCES MOVIES(MovieID)
	ON DELETE CASCADE ON UPDATE CASCADE 

);


DROP TABLE IF EXISTS BOUGHTTICKETS;
CREATE TABLE BOUGHTTICKETS (
	TicketID			int not null AUTO_INCREMENT,
    FMovieID        int not null,
    SeatNo            char(1) not null,
    UserName        varchar(25),
    UserEmail          varchar(25),
    Amount              int not null,       
	primary key (TicketID),
  
    FOREIGN KEY(FMovieID) REFERENCES MOVIES(MovieID)
    ON DELETE CASCADE ON UPDATE CASCADE

);

DROP TABLE IF EXISTS CREDIT;
CREATE TABLE CREDIT (
	creditID			int not null AUTO_INCREMENT,
    FUserID           int not null,
    Amount              int not null,      
	primary key (creditID),
    
    FOREIGN KEY(FUserID) REFERENCES USERS(UserID)
    ON DELETE CASCADE ON UPDATE CASCADE

);

DELIMITER $$

CREATE TRIGGER after_movies_insert
AFTER INSERT
ON MOVIES FOR EACH ROW
INSERT INTO SEATS(FMovieID, Seats) VALUES (NEW.MovieID, '111111111')

DELIMITER ;