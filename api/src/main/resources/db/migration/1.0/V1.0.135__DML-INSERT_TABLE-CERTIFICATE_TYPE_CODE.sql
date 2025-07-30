INSERT INTO CERTIFICATE_TYPE_CODE
(CERTIFICATE_TYPE_CODE, LABEL, DESCRIPTION, DISPLAY_ORDER, EFFECTIVE_DATE, EXPIRY_DATE, CREATE_USER, CREATE_DATE, UPDATE_USER, UPDATE_DATE, PAPER_TYPE, "LANGUAGE")
VALUES('FIND',
       'diplôme (French Immersion - Independent)',
       'diplôme de fin d’études secondaire en Colombie-Britannique - French Immersion Independent School.',
       140,
       to_date('25-05-15','YY-MM-DD'),
       NULL,
       'API_GRAD_REPORT',
       to_date('25-05-15','YY-MM-DD'),
       'API_GRAD_REPORT',
       to_date('25-05-15','YY-MM-DD'),
       'YEDR',
       'French');