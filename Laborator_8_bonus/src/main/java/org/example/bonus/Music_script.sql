--Number	Year	Album	                            Artist	                    Genre	
--1	        1967	Sgt. Pepper's Lonely Hearts         The Beatles	                Rock, Rock & Roll, Psychedelic Rock
--2	        1943	The Sun Sessions	                Elvis Presley	            Rock, Rock & Roll
--3	        1959	Kind of Blue	                    Miles Davis	                Jazz, Modal
--4	        1967	The Velvet Underground & Nico	    The Velvet Underground	    Art Rock, Garage Rock, Experimental
--5	        1975	Blood on the Tracks	                Bob Dylan	                Folk Rock, Acoustic
--6	        1972	Superfly	                        Curtis Mayfield	Ballad	    Grunge
--7	        1975	The Velvet Underground & Nico	    Van Morrison	            Pop,	Zydeco
--8	        1983	Graceland	                        The Beatles	                African,	Afrobeat
--9	        1975	Goodbye Yellow Brick Road	        Elton John	                Soul,	

insert into artists(id, name) values (4, 'The Beatles');
insert into artists(id, name) values (5, 'Elvis Presley');
insert into artists(id, name) values (6, 'Miles Davis');
insert into artists(id, name) values (7, 'The Velvet Underground');
insert into artists(id, name) values (8, 'Bob Dylan');
insert into artists(id, name) values (9, 'Curtis Mayfield	Ballad');
insert into artists(id, name) values (10, 'Van Morrison');
insert into artists(id, name) values (11, 'Elton John');

insert into albums(id, release_year, title, artist_id) values (4, 1967, 'Sgt. Peppers Lonely Hearts', 4);
insert into albums(id, release_year, title, artist_id) values (5, 1943, 'The Sun Sessions ', 5);
insert into albums(id, release_year, title, artist_id) values (6, 1959, 'Kind of Blue ', 6);
insert into albums(id, release_year, title, artist_id) values (7, 1967, 'The Velvet Underground + Nico ', 7);
insert into albums(id, release_year, title, artist_id) values (8, 1975, 'Blood on the Tracks ', 8);
insert into albums(id, release_year, title, artist_id) values (9, 1972, 'Superfly ', 9);
insert into albums(id, release_year, title, artist_id) values (10, 1975, 'The Velvet Underground + Nico ', 10);
insert into albums(id, release_year, title, artist_id) values (11, 1943, 'Graceland ', 4);
insert into albums(id, release_year, title, artist_id) values (12, 1975, 'Goodbye Yellow Brick Road ', 11);

delete from albums where id=11;

insert into album_genres(album_id, genre_id) values (4, 4);
insert into album_genres(album_id, genre_id) values (4, 6);
insert into album_genres(album_id, genre_id) values (4, 7);
insert into album_genres(album_id, genre_id) values (5, 4);
insert into album_genres(album_id, genre_id) values (5, 6);
insert into album_genres(album_id, genre_id) values (6, 87);
insert into album_genres(album_id, genre_id) values (6, 17);
insert into album_genres(album_id, genre_id) values (7, 19);
insert into album_genres(album_id, genre_id) values (7, 18);
insert into album_genres(album_id, genre_id) values (7, 16);
insert into album_genres(album_id, genre_id) values (8, 9);
insert into album_genres(album_id, genre_id) values (8, 20);
insert into album_genres(album_id, genre_id) values (9, 23);
insert into album_genres(album_id, genre_id) values (10, 1);
insert into album_genres(album_id, genre_id) values (10, 65);
insert into album_genres(album_id, genre_id) values (11, 63);
insert into album_genres(album_id, genre_id) values (11, 64);
insert into album_genres(album_id, genre_id) values (12, 5);

delete from album_genres where album_id=11;

commit;

select * from albums;
select * from genres;
select * from artists;
select * from album_genres;