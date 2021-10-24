ALTER TABLE comments
ADD COLUMN is_translate int(11) NULL;

ALTER TABLE users
ADD COLUMN is_translate int(11) NULL;
