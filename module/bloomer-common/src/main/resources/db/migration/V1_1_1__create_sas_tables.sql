CREATE TABLE classroom (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  uuid BINARY(16) NOT NULL COMMENT 'Uuid for adding class',
  name SMALLINT NOT NULL,
  year SMALLINT NOT NULL,
  display_name VARCHAR(255) NOT NULL,
  remark VARCHAR(255) NOT NULL,
  section SMALLINT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (uuid),
  UNIQUE KEY (name, year, section)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;