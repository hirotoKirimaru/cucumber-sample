CREATE TABLE BOOK
(
    isbn         VARCHAR(13) PRIMARY KEY,
    money        INT,
    author       VARCHAR(120),
    number       INT,
    generate_date TIMESTAMP,
    generate_user VARCHAR(120),
    update_date   TIMESTAMP,
    update_user   VARCHAR(120)
);

-- INSERT INTO BOOK VALUES ('9784798126708', 1000, 'kirimaru', 10, '2021-07-02 12:34:56', 'kirimaru', '2021-08-03 11:22:33', 'kirimaru')
