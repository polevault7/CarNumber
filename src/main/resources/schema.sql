CREATE TABLE IF NOT EXISTS numbers
(
    id          SERIAL primary key ,
    plateNumber varchar not null unique
);
