USE `recap` ;

-- Script for Release 1.3.5 starts here

INSERT INTO `recap`.`job_t` (`JOB_ID`,`JOB_NAME`,`JOB_DESC`,`LAST_EXECUTED_TIME`,`NEXT_RUN_TIME`,`CRON_EXP`,`STATUS`,`JOB_INSTANCE_ID`) VALUES (22,'IdentifyAndNotifyPendingRequest','Identify and Notify Pending Requests from Database',NULL,NULL,NULL,NULL,NULL);

DROP TABLE IF EXISTS `PENDING_REQUEST_T`;
-- -----------------------------------------------------
-- Table `PENDING_REQUEST_T`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PENDING_REQUEST_T` (
  `PENDING_ID`          INT           NOT NULL AUTO_INCREMENT,
  `ITEM_ID`             INT           NOT NULL,
  `REQUEST_ID`     INT           NOT NULL,
  `REQUEST_CREATED_DATE`        DATETIME NOT NULL,
  PRIMARY KEY (`PENDING_ID`),
  INDEX `ITEM_ID_FK_idx` (`ITEM_ID` ASC),
  INDEX `REQUEST_ID_FK_idx` (`REQUEST_ID` ASC),
  CONSTRAINT `PENDING_ITEM_ID_FK`     FOREIGN KEY (`ITEM_ID`)           REFERENCES `ITEM_T` (`ITEM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PENDING_REQUEST_ID_FK`  FOREIGN KEY (`REQUEST_ID`) REFERENCES `REQUEST_ITEM_T` (`REQUEST_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
  ENGINE = InnoDB;

-- Script for Release 1.3.5 ends here
