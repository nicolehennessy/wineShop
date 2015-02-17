DROP TABLE IF EXISTS `wines`;
CREATE TABLE  `wines` (
  `wineID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `yearMade` int(50) NOT NULL,
  `type` varchar(60) NOT NULL,
  `tempurature` double (2,2) NOT NULL,
  `description` varchar(250) NOT NULL,
  PRIMARY KEY (`wineID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

