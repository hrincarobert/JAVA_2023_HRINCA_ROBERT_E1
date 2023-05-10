CREATE TABLE artists (id NUMBER PRIMARY KEY, name VARCHAR(255) NOT NULL);

insert into artists(id, name) values (1, 'Beyoncé');
insert into artists(id, name) values (2, 'Adele');

CREATE TABLE genres (
id number PRIMARY KEY,
name VARCHAR(255) not null
);

insert into genres(id, name) values (1, 'Pop');
insert into genres(id, name) values (2, 'R.B');
insert into genres(id, name) values (3, 'Hip-Hop');
insert into genres(id, name) values (4, 'Rock');
insert into genres(id, name) values (5, 'Soul');

CREATE TABLE albums (
id number PRIMARY KEY,
release_year number not null,
title VARCHAR(255) not null,
artist_id number not null,
foreign key (artist_id) references artists(id)
);

insert into albums(id, release_year, title, artist_id) values (1, 2016, 'Lemonade', 1);
insert into albums(id, release_year, title, artist_id) values (2, 2022, 'Renaissance ', 1);
insert into albums(id, release_year, title, artist_id) values (3, 2015, '25 ', 2);

DELETE FROM albums wHERE id = 1;

CREATE TABLE album_genres (
album_id number,
genre_id number,
FOREIGN KEY (album_id) REFERENCES albums (id),
FOREIGN KEY (genre_id) REFERENCES genres (id)
);

insert into album_genres(album_id, genre_id) values (1, 1);
insert into album_genres(album_id, genre_id) values (1, 2);
insert into album_genres(album_id, genre_id) values (2, 1);
insert into album_genres(album_id, genre_id) values (2, 2);
insert into album_genres(album_id, genre_id) values (2, 4);
insert into album_genres(album_id, genre_id) values (3, 5);


select * from albums;
select * from genres;
select * from artists;
select * from album_genres;