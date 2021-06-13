CREATE TABLE BOOK
(
    isbn         VARCHAR(13) PRIMARY KEY,
    money        INT,
    author       VARCHAR(120),
    generate_date TIMESTAMP,
    generate_user VARCHAR(120),
    update_date   TIMESTAMP,
    update_user   VARCHAR(120)
);

