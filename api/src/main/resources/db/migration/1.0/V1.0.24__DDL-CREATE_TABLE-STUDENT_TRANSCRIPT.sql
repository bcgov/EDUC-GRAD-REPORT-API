--------------------------------------------------------
--  DDL for Table STUDENT_TRANSCRIPT
--------------------------------------------------------

CREATE TABLE STUDENT_TRANSCRIPT
(	STUDENT_TRANSCRIPT_ID RAW(16) DEFAULT SYS_GUID(),
     TRANSCRIPT CLOB,
     TRANSCRIPT_TYPE_CODE VARCHAR2(10 BYTE) NOT NULL ENABLE,
     GRADUATION_STUDENT_RECORD_ID RAW(16) NOT NULL ENABLE,
     DOCUMENT_STATUS_CODE VARCHAR2(5 BYTE),
     DISTRIBUTION_DATE DATE,
     CREATE_USER VARCHAR2(30 BYTE) DEFAULT USER NOT NULL ENABLE,
     CREATE_DATE DATE DEFAULT SYSTIMESTAMP NOT NULL ENABLE,
     UPDATE_USER VARCHAR2(30 BYTE) DEFAULT USER NOT NULL ENABLE,
     UPDATE_DATE DATE DEFAULT SYSTIMESTAMP NOT NULL ENABLE,
     CONSTRAINT STUDENT_TRANSCRIPT_ID_PK PRIMARY KEY (STUDENT_TRANSCRIPT_ID)
         USING INDEX TABLESPACE API_GRAD_IDX  ENABLE
) SEGMENT CREATION IMMEDIATE
    NOCOMPRESS LOGGING
    TABLESPACE API_GRAD_DATA NO INMEMORY
    LOB (TRANSCRIPT) STORE AS SECUREFILE (
    TABLESPACE API_GRAD_BLOB_DATA ENABLE STORAGE IN ROW
    NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES) ;

alter table STUDENT_TRANSCRIPT
    add constraint STUD_TRAN_TRAN_TYPE_CD_FK foreign key(TRANSCRIPT_TYPE_CODE)
        references TRANSCRIPT_TYPE_CODE(TRANSCRIPT_TYPE_CODE);
