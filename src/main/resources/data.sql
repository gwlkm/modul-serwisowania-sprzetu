DROP TABLE IF EXISTS DEVICE_CATEGORY;

CREATE TABLE DEVICE_CATEGORY(
  NAME VARCHAR(120) NOT NULL PRIMARY KEY
);

DROP TABLE IF EXISTS DEVICE_COMMENT;

CREATE TABLE DEVICE_COMMENT(
  ID NUMBER NOT NULL PRIMARY KEY,
  CONTENT VARCHAR(500) NOT NULL,
  CREATION_DATE TIMESTAMP NOT NULL,
  MODIFICATION_DATE TIMESTAMP NOT NULL,
  DEVICE_ID NUMBER NOT NULL,
  FOREIGN KEY (DEVICE_ID) REFERENCES DEVICE(ID)
);

DROP TABLE IF EXISTS DEVICE_PARAMETER;

CREATE TABLE DEVICE_PARAMETER(
  ID NUMBER NOT NULL PRIMARY KEY,
  NAME VARCHAR(120) NOT NULL,
  VALUE VARCHAR(240) NOT NULL,
  DEVICE_ID NUMBER NOT NULL,
  FOREIGN KEY (DEVICE_ID) REFERENCES DEVICE(ID)
);

DROP TABLE IF EXISTS DEVICE;

CREATE TABLE DEVICE(
  ID NUMBER NOT NULL PRIMARY KEY,
  NAME VARCHAR(120) NOT NULL,
  IS_BROKEN NUMBER(1) NOT NULL,
  CATEGORY_NAME VARCHAR(120) NOT NULL,
  FOREIGN KEY (CATEGORY_NAME) REFERENCES DEVICE_CATEGORY(NAME)
);

INSERT INTO DEVICE_CATEGORY VALUES 'Telewizor';

INSERT INTO DEVICE VALUES (1, 'Super telewizor', 0, 'Telewizor');

INSERT INTO DEVICE_PARAMETER VALUES(1,'waga','2.8kg',1);
INSERT INTO DEVICE_PARAMETER VALUES(2,'cena','900zl',1);

INSERT INTO DEVICE_COMMENT VALUES(1,'pierwszy',{ts '2019-09-17 18:47:52.69'},{ts '2019-09-17 18:47:52.69'},1);
INSERT INTO DEVICE_COMMENT VALUES(2,'pierwszy',{ts '2019-09-17 18:49:52.69'},{ts '2019-09-17 18:55:52.69'},1);