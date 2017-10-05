-- auto-generated definition
CREATE TABLE indexDataInf
(
  dataID              INT          NULL,
  dataName            VARCHAR(255) NULL,
  indexDataSetZIPPath VARCHAR(255) NULL,
  indexDataSetPath    VARCHAR(255) NULL,
  indexDataSetDivPath VARCHAR(255) NULL,
  indexDocTag         VARCHAR(255) NULL,
  indexIDTag          VARCHAR(255) NULL
);

-- auto-generated definition
CREATE TABLE indexInf
(
  indexID         INT          NULL,
  dataID          INT          NULL,
  indexProcessTag VARCHAR(255) NULL,
  indexResultPath VARCHAR(255) NULL,
  indexDocInfPath VARCHAR(255) NULL
);

-- auto-generated definition
CREATE TABLE queryDataInf
(
  queryID     INT          NULL,
  indexID     INT          NULL,
  queryName   VARCHAR(255) NULL,
  queryPath   VARCHAR(255) NULL,
  queryDocTag VARCHAR(255) NULL,
  queryIDTag  VARCHAR(255) NULL
);
-- auto-generated definition
CREATE TABLE queryInf
(
  retrieveResultID INT          NULL,
  indexID          INT          NULL,
  queryID          INT          NULL,
  queryProcessTag  VARCHAR(255) NULL,
  queryModel       VARCHAR(255) NULL,
  resultLength     DOUBLE       NULL,
  resultPath       VARCHAR(255) NULL
);

-- auto-generated definition
CREATE TABLE user_index
(
  id     INT NULL,
  userID INT NULL,
  dataID INT NULL
);

-- auto-generated definition
CREATE TABLE user_Login
(
  emailAddress VARCHAR(255) NOT NULL
    PRIMARY KEY,
  username     VARCHAR(255) NULL,
  password     VARCHAR(255) NULL,
  validation   VARCHAR(255) NULL
);
