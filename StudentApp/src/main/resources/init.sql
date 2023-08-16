USE student;

DROP TABLE IF EXISTS users_role;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS student;



-- Create tables
CREATE TABLE user (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(68)
);

CREATE TABLE users_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    role VARCHAR(50),
    FOREIGN KEY (username) REFERENCES user(username)
);

CREATE TABLE student (
	id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(70),
    email VARCHAR(100)
);



-- Initialize the student table.
INSERT INTO student (first_name, last_name, email)
VALUES
	('Alice', 'Johnson', 'alice@example.com'),
    ('Bob', 'Smith', 'bob@example.com'),
    ('Charlie', 'Williams', 'charlie@example.com'),
    ('David', 'Brown', 'david@example.com'),
    ('Emma', 'Jones', 'emma@example.com'),
    ('Frank', 'Davis', 'frank@example.com'),
    ('Grace', 'Miller', 'grace@example.com'),
    ('Henry', 'Wilson', 'henry@example.com'),
    ('Isabella', 'Moore', 'isabella@example.com'),
    ('Jack', 'Taylor', 'jack@example.com'),
    ('Katherine', 'Anderson', 'katherine@example.com'),
    ('Liam', 'Thomas', 'liam@example.com'),
    ('Mia', 'Jackson', 'mia@example.com'),
    ('Noah', 'White', 'noah@example.com'),
    ('Olivia', 'Harris', 'olivia@example.com'),
    ('Owen', 'Martin', 'owen@example.com'),
    ('Sophia', 'Lee', 'sophia@example.com'),
    ('William', 'Walker', 'william@example.com'),
    ('Zoe', 'Green', 'zoe@example.com'),
    ('Ethan', 'Hall', 'ethan@example.com');