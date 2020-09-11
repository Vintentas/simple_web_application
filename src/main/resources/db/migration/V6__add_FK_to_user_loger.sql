USE db_example2;

-- -----------------------------------------------------
-- Create foreign key to Logger
-- -----------------------------------------------------

ALTER TABLE user_logger
ADD CONSTRAINT fk_user_changer_id
  FOREIGN KEY (changer_id)
  REFERENCES user (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE user_logger
ADD CONSTRAINT fk_changed_user_id
   FOREIGN KEY (changed_user_id)
   REFERENCES user (id)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;