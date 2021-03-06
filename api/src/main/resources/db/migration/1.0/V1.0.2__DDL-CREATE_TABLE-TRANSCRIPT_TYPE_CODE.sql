-- API_GRAD_REPORT.TRANSCRIPT_TYPE_CODE definition

  CREATE TABLE TRANSCRIPT_TYPE_CODE 
   (	TRANSCRIPT_TYPE_CODE VARCHAR2(16 BYTE), 
	LABEL VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	DESCRIPTION VARCHAR2(255 BYTE) NOT NULL ENABLE, 
	DISPLAY_ORDER NUMBER NOT NULL ENABLE, 
	EFFECTIVE_DATE DATE NOT NULL ENABLE, 
	EXPIRY_DATE DATE, 
	CREATE_USER VARCHAR2(30 BYTE) DEFAULT USER NOT NULL ENABLE, 
	CREATE_DATE DATE DEFAULT SYSTIMESTAMP NOT NULL ENABLE, 
	UPDATE_USER VARCHAR2(30 BYTE) DEFAULT USER NOT NULL ENABLE, 
	UPDATE_DATE DATE DEFAULT SYSTIMESTAMP NOT NULL ENABLE, 
	 CONSTRAINT TRANSCRIPT_TYPE_CODE_PK PRIMARY KEY (TRANSCRIPT_TYPE_CODE)
	 USING INDEX TABLESPACE API_GRAD_IDX  ENABLE
	) SEGMENT CREATION IMMEDIATE
    NOCOMPRESS LOGGING
    TABLESPACE API_GRAD_DATA NO INMEMORY ;
