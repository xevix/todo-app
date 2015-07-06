# TodoItems schema
 
# --- !Ups
 
CREATE TABLE todo_items (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    body varchar(255) NOT NULL
);
 
# --- !Downs
 
DROP TABLE todo_items;
