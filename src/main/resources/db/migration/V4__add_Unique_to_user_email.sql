USE db_example2;

-- -----------------------------------------------------
-- Make email unique cause it is login
-- -----------------------------------------------------

ALTER TABLE user
ADD UNIQUE INDEX email_UNIQUE (email ASC);