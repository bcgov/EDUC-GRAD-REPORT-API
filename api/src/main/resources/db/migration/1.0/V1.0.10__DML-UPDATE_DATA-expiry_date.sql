UPDATE CERTIFICATE_TYPE_CODE SET EXPIRY_DATE = NULL WHERE to_char(EXPIRY_DATE, 'yyyy-mm-dd') = '2099-12-31';
UPDATE DOCUMENT_STATUS_CODE SET EXPIRY_DATE = NULL WHERE to_char(EXPIRY_DATE, 'yyyy-mm-dd') = '2099-12-31';
UPDATE REPORT_TYPE_CODE SET EXPIRY_DATE = NULL WHERE to_char(EXPIRY_DATE, 'yyyy-mm-dd') = '2099-12-31';
