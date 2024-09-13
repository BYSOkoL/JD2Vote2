    CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.artist
(
    id bigserial,
    name character varying(128) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.artist
    OWNER to postgres;

CREATE TABLE app.genre
(
    id bigserial,
    name character varying(128) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.genre
    OWNER to postgres;

     CREATE TABLE app.vote
    (
        id bigserial,
        create_at timestamptz  NOT NULL,
        artist_id bigint  NOT NULL,
        about text NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (artist_id) REFERENCES app.artist(id)
    );

    ALTER TABLE IF EXISTS app.vote
        OWNER to postgres;


    CREATE TABLE app.cross_vote_genre
    (
      vote_id bigint,
      genre_id bigint,
      FOREIGN KEY (vote_id) REFERENCES app.vote (id),
      FOREIGN KEY (genre_id) REFERENCES app.genre (id),
      UNIQUE (vote_id, genre_id)
    );

    ALTER TABLE IF EXISTS app.cross_vote_genre
        OWNER to postgres;