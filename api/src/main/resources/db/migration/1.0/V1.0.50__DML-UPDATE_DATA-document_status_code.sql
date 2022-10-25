UPDATE DOCUMENT_STATUS_CODE SET DESCRIPTION = 'Student has not yet met their program requirements' WHERE DOCUMENT_STATUS_CODE='IP';
UPDATE DOCUMENT_STATUS_CODE SET DESCRIPTION = 'Student has met their program requirements' WHERE DOCUMENT_STATUS_CODE='COMPL';
UPDATE DOCUMENT_STATUS_CODE SET DESCRIPTION = 'Student has completed documents under a different program than their current program' WHERE DOCUMENT_STATUS_CODE='ARCH';
