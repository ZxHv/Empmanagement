CREATE TABLE `tb_emp` (
  `eid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ename` VARCHAR(50) NOT NULL,
  `dept_id` INT(11) DEFAULT NULL,
  `age` INT(11) NOT NULL,
  `gender` VARCHAR(2) NOT NULL,
  `workDate` DATE NOT NULL,
  `did` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`eid`),
  KEY `fk_tb_dept1` (`did`),
  CONSTRAINT `fk_tb_dept1` FOREIGN KEY (`did`) REFERENCES `tb_dept` (`did`)
) ENGINE=INNODB DEFAULT CHARSET=utf8
