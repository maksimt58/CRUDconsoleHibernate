CREATE TABLE writers
(
id SERIAL NOT NULL,
first_name CHARACTER VARYING(255) NOT NULL ,
last_name CHARACTER VARYING(255) NOT NULL ,
PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id SERIAL NOT NULL,
    content CHARACTER VARYING(255) NOT NULL ,
    status CHARACTER VARYING(255) NOT NULL ,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    writer_id INTEGER NOT NULL CHECK ( writer_id > 0 ),
    foreign key (writer_id) references writers (id) ON DELETE CASCADE on update cascade,
    PRIMARY KEY (id)
);



CREATE TABLE labels
(
    id SERIAL NOT NULL,
    name_label CHARACTER VARYING(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE post_labels
(
    post_id INTEGER NOT NULL,
    label_id INTEGER NOT NULL,
    foreign key (post_id) references posts (id) ON DELETE CASCADE on update cascade,
    foreign key (label_id) references labels (id) ON DELETE CASCADE on update cascade,
    UNIQUE (post_id,label_id)
);

CREATE FUNCTION update_update_date_column() RETURNS trigger
    LANGUAGE plpgsql
AS $$
BEGIN
    NEW.update_date = NOW();
    RETURN NEW;
END;
$$;

CREATE TRIGGER posts_update_date_modtime BEFORE UPDATE ON posts
    FOR EACH ROW EXECUTE PROCEDURE update_update_date_column();