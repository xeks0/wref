CREATE TABLE hibernate_sequence (
  next_val bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
INSERT INTO hibernate_sequence
(next_val)
VALUES
(1);
