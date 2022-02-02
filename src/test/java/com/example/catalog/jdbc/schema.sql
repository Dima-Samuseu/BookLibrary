CREATE TABLE book
(
    id bigint NOT NULL PRIMARY KEY,
    isbn varchar(255),
    name varchar(255),
    author varchar(255),
    number_of_pages int,
    weight int,
    price decimal
);