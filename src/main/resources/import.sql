INSERT INTO db_photoalbum.photos (description, is_visible, title, url) VALUES('Description 1', 1, 'Photo 1','https://picsum.photos/200');
INSERT INTO db_photoalbum.photos (description, is_visible, title, url) VALUES('Description 2', 1, 'Photo 2','https://picsum.photos/200');
INSERT INTO db_photoalbum.photos (description, is_visible, title, url) VALUES('Description 3', 1, 'Photo 3','https://picsum.photos/200');
INSERT INTO db_photoalbum.photos (description, is_visible, title, url) VALUES('Description 4', 1, 'Photo 4','https://picsum.photos/200');
INSERT INTO db_photoalbum.photos (description, is_visible, title, url) VALUES('Description 5', 1, 'Photo 5','https://picsum.photos/200');

INSERT INTO db_photoalbum.categories (name) VALUES('Category 1');
INSERT INTO db_photoalbum.categories (name) VALUES('Category 2');
INSERT INTO db_photoalbum.categories (name) VALUES('Category 3');
INSERT INTO db_photoalbum.categories (name) VALUES('Category 4');
INSERT INTO db_photoalbum.categories (name) VALUES('Category 5');

INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(1, 1);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(1, 2);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(1, 3);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(2, 4);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(2, 3);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(2, 2);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(3, 1);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(3, 4);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(3, 3);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(4, 3);
INSERT INTO db_photoalbum.photos_categories (photo_id, categories_id) VALUES(5, 1);

INSERT INTO db_photoalbum.users (password, username) VALUES('{noop}ciao', 'enrico');
INSERT INTO db_photoalbum.users (password, username) VALUES('{noop}ciao', 'diana');
INSERT INTO db_photoalbum.roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO db_photoalbum.roles (id, name) VALUES(2, 'USER');

INSERT INTO db_photoalbum.users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO db_photoalbum.users_roles (user_id, roles_id) VALUES(2, 2);


