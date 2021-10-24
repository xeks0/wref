ALTER TABLE posts
ADD COLUMN slug VARCHAR(255) NOT NULL AFTER parent_id,
ADD COLUMN is_translate int(11) NULL AFTER slug;
