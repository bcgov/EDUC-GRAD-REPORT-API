-- update codes
UPDATE REPORT_TYPE_CODE
SET DESCRIPTION = 'PDF amalgamation of TVRs for all current students reported on a graduation program, in grade 12 or AD, who are projected to graduate (or have already graduated) based on course and assessment registrations or completions.'
WHERE REPORT_TYPE_CODE = 'TVRGRAD';

UPDATE REPORT_TYPE_CODE
SET DESCRIPTION = 'PDF amalgamation of TVRs for all current students reported on a graduation program, in grade 12 or AD, who are not projected to graduate based on missing course or assessment registrations.'
WHERE REPORT_TYPE_CODE = 'TVRNONGRAD';

UPDATE REPORT_TYPE_CODE
SET DESCRIPTION = 'A list of all current students reported on a graduation program, in grade 12 or AD, who are not projected to graduate based on missing course registrations or assessment registrations. Produced as part of TVR Batch Run.'
WHERE REPORT_TYPE_CODE = 'NONGRADPRJ';

UPDATE REPORT_TYPE_CODE
SET LABEL = 'Graduated Students (MM YY to MM YY) Report',
DESCRIPTION = 'A daily, cumulative list of student in the current cycle who have graduated, based on the latest information submitted by the school. Produced as part of the Batch Graduation Algorithm Run.'
WHERE REPORT_TYPE_CODE = 'GRADREG';

UPDATE REPORT_TYPE_CODE
SET LABEL = 'Not-Yet Graduated Students (MM YY to MM YY) Report',
DESCRIPTION = 'A daily, cumulative list of student in the current cycle who have not-yet graduated, based on the latest information submitted by the school. Produced as part of the Batch Graduation Algorithm Run.'
WHERE REPORT_TYPE_CODE = 'NONGRADREG';

-- new codes
Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'DISTREP_SC',
'Credentials and Transcript Distribution Report',
'A list of students who have had a new graduation or completion certificate and transcript printed in the Credentials Distribution Run. Printed reports are included in certificate and transcript packages sent to schools.',
20,
to_date('21-07-19','RR-MM-DD'));

Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'DISTREP_YE_SC',
'Year-End Credentials and Transcript Distribution Report (School)',
'A list of students who have had a new graduation or completion certificate and transcript printed in the Credentials and Transcript Distribution Run. Printed reports are included in certificate and transcript packages sent to schools.',
25,
to_date('21-07-19','RR-MM-DD'));

Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'DISTREP_YE_SD',
'Year-End District Credentials and Transcript Distribution Report (District)',
'A count of credentials, by school and credential-type, as well as number of transcripts, issued in the year-end Credentials and Transcript Distribution run. Printed reports are included in the year-end certificate and transcript packages sent to districts.',
27,
to_date('21-07-19','RR-MM-DD'));

Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'NONGRADDISTREP_SC',
'Non-Graduation Transcript Distribution Report (School)',
'A list of students who have had a transcript printed in the Non-Graduate Transcript Distribution run. Printed reports are included in the transcript packages sent to districts and schools.',
30,
to_date('21-07-19','RR-MM-DD'));

Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'NONGRADDISTREP_SD',
'Non-Graduation Transcript Distribution Report (District)',
'A description of the Non-Graduate Transcript Distribution package and a count of transcripts by school. Printed reports are included in packages sent to districts.',
35,
to_date('21-07-19','RR-MM-DD'));

-- update the old codes in STUDENT_REPORT & SCHOOL_REPORT tables
UPDATE STUDENT_REPORT SET REPORT_TYPE_CODE = 'DISTREP_SC'
WHERE REPORT_TYPE_CODE = 'GRADDIST';

UPDATE SCHOOL_REPORT SET REPORT_TYPE_CODE = 'DISTREP_SC'
WHERE REPORT_TYPE_CODE = 'GRADDIST';

-- delete codes
DELETE FROM REPORT_TYPE_CODE
WHERE REPORT_TYPE_CODE = 'GRADDIST';



