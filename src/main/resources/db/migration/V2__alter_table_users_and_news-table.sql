ALTER TABLE users ADD COLUMN deleted BOOLEAN;
ALTER TABLE news ADD COLUMN deleted BOOLEAN;
UPDATE users SET deleted = false;
UPDATE news SET deleted = false;