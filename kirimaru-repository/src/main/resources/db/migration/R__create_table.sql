create TABLE BOOK
(
    isbn         VARCHAR(13) PRIMARY KEY,
    money        INT,
    author       VARCHAR(120),
    generateDate TIMESTAMP,
    generateUser VARCHAR(120),
    updateDate   TIMESTAMP,
    updateUser   VARCHAR(120)
);

