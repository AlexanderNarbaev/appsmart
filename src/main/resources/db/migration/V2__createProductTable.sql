DROP TABLE PRODUCT IF EXISTS ;

CREATE TABLE PRODUCT (
  ID UUID NOT NULL,
  CUSTOMER_ID UUID NOT NULL,
  TITLE VARCHAR(255),
  DESCRIPTION VARCHAR(1024),
  PRICE DECIMAL (10,2)  NOT NULL  DEFAULT 0.0,
  IS_DELETED BOOLEAN  NOT NULL  DEFAULT FALSE,
  CREATED_AT TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MODIFIED_AT TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  CONSTRAINT PK_PRODUCT PRIMARY KEY (ID)
);

CREATE INDEX IDX_PRODUCT_ID_CUSTOMER_ID_IS_DELETED ON PRODUCT(ID, CUSTOMER_ID, IS_DELETED);