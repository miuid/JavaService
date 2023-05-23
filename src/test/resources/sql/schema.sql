CREATE TABLE IF NOT EXISTS record (
    id                     IDENTITY     NOT NULL    PRIMARY KEY,
    name                   VARCHAR      NOT NULL,
    message                VARCHAR      NOT NULL
);

insert into record(name, message) values('Danni', 'Hello');
insert into record(name, message) values('Mia', 'Good Kid');
