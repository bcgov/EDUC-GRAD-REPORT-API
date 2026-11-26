-- EDO-244: Update the ministry title printed on adult graduation certificates.
-- Leave MOAEF unchanged pending a confirmed French translation.
UPDATE SIGNATURE_BLOCK_TYPE
SET LABEL = 'Minister of Post-Secondary Education' || chr(10) || 'and Labour',
    DESCRIPTION = 'Minister of Post-Secondary Education and Labour',
    UPDATE_USER = 'GRAD_REPORT_API',
    UPDATE_DATE = SYSDATE
WHERE SIGNATURE_BLOCK_TYPE = 'MOAE';

UPDATE SIGNATURE_BLOCK_TYPE
SET LABEL = 'Deputy Minister of Education and Child Care',
    DESCRIPTION = 'Deputy Minister of Education and Child Care',
    UPDATE_USER = 'GRAD_REPORT_API',
    UPDATE_DATE = SYSDATE
WHERE SIGNATURE_BLOCK_TYPE = 'MOE_DM';
