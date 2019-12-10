DROP USER project2 CASCADE;

CREATE USER project2
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;
GRANT CONNECT TO project2;
GRANT RESOURCE TO project2;
GRANT CREATE SESSION TO project2;
GRANT CREATE TABLE TO project2;
GRANT CREATE VIEW TO project2;
conn project2/p4ssw0rd;


/************************************
Tables and sequences
************************************/
-- Create the user table --
CREATE SEQUENCE user_id_seq;
CREATE TABLE users (
    user_id number PRIMARY KEY,                 -- = user ID. 
    current_ip varchar2(50) NOT NULL,           -- = the users IP?
    current_ip_location varchar2(50) NOT NULL,  -- = the users location?
    display_name varchar2(50) UNIQUE,           -- = their display name (for messages or interactions)
    email varchar2(50) UNIQUE,                  -- = the email of the user
    secure_key varchar2(50),                    -- = 
    salt varchar2(50)                           -- = 
);

-- Create photo table for saving favorite photos
CREATE SEQUENCE photo_id_seq;
CREATE TABLE photo (
    id number PRIMARY KEY,
    photo_id number,
    animal_id number,
    user_id number NOT NULL                 -- = 
        REFERENCES users(user_id),
    animal_type varchar2(30),     -- = Dog, Cat, Bird, etc.
    full_url varchar2(100),
    url varchar2(150)
);

-- table for the total number of likes for all animals (cats, dogs, birds, pigs, etc)
CREATE SEQUENCE total_likes_id_seq;
CREATE TABLE total_likes (
    total_likes_id number PRIMARY KEY,
    species varchar2(15),
    likes number
);

-- table for the total number of likes for all animals (cats, dogs, birds, pigs, etc)
CREATE SEQUENCE daily_likes_id_seq;
CREATE TABLE daily_likes (
    daily_likes_id number PRIMARY KEY,
    species varchar2(15),
    likes number,
    current_date TIMESTAMP
);
    
INSERT INTO users (user_id, current_ip, current_ip_location, display_name, email, secure_key, salt)
    VALUES (525252, '2600:8806:4000:3d0:38ab:bebe:5851:cc34', '38.9841,-77.3672', 'Tim', 'tim@user.com', 'pass', 'pass');
    
-- insert users. 
--INSERT INTO users (user_id, current_ip, current_ip_location, display_name, email, secure_key, salt)
--    VALUES(user_id_seq.nextval, ?, ?, ?, ?, ?, ?);

INSERT INTO photo (id, photo_id, animal_id, user_id, animal_type, full_url, adoption_url)
    VALUES (photo_id_seq.nextval, 1575981498, 46806223, 525252, 'cat', 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46806223/1/?bust=1575981498', 'https://www.petfinder.com/cat/gizmo-46806223/mi/eaton-rapids/saved-by-zade-mi1028/?referrer_id=2279119b-62bc-4991-8fc5-ff96fb42cad6');

-- Insert a photo
--INSERT INTO photo (id, photo_id, animal_id, animal_type, full_url, adoption_url)
--    VALUES (photo_id_seq.nextval, ?, ?, ?, ?, ?);

-- SELECT * FROM photo WHERE user_id = 525252;
commit;