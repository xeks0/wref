CREATE TABLE accounts (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  about_me TEXT DEFAULT NULL,
  account_id int(11) DEFAULT NULL,
  display_name varchar(255) DEFAULT NULL,
  down_votes int(11) DEFAULT NULL,
  last_access_date datetime DEFAULT NULL,
  location varchar(255) DEFAULT NULL,
  up_votes int(11) DEFAULT NULL,
  views int(11) DEFAULT NULL,
  website_url varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
