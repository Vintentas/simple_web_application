-- -----------------------------------------------------
-- Create foreign key in User.Role to Role
-- -----------------------------------------------------
USE db_example2;


ALTER TABLE user
ADD INDEX role_id_idx (role_id ASC);

ALTER TABLE user
ADD CONSTRAINT fk_role_user_id
  FOREIGN KEY (role_id)
  REFERENCES role (role_id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


-- -----------------------------------------------------
-- Insert default roles
-- -----------------------------------------------------

INSERT INTO role (role_id, role_name) VALUES ('1', '1');
INSERT INTO role (role_id, role_name) VALUES ('2', '2');
INSERT INTO role (role_id, role_name) VALUES ('3', '3');