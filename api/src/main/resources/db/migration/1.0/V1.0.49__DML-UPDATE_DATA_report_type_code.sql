-- This script is for ticket GRAD2-1464
-- 1. disable foreign keys in these two child tables because they used report_type_code as foreign key
ALTER TABLE STUDENT_REPORT
DISABLE CONSTRAINT STUD_REPORT_REPORT_TYPE_CD_FK;

ALTER TABLE SCHOOL_REPORT
DISABLE CONSTRAINT SCH_REP_TYPE_CD_FK;

-- 2, UPDATE child tables' code FROM 'GRAD' to new value 'GRADDIST'
UPDATE STUDENT_REPORT
SET REPORT_TYPE_CODE = 'GRADDIST' WHERE REPORT_TYPE_CODE = 'GRAD';

UPDATE SCHOOL_REPORT
SET REPORT_TYPE_CODE = 'GRADDIST' WHERE REPORT_TYPE_CODE = 'GRAD';

-- 3, delete all child records linked to the old primary code "NONGRAD"
-- this part is no longer valid, see detail in GRAD2-1464


-- 4. UPDATE PARENT TABLE REPORT_TYPE_CODE FROM 'GRAD' to new value 'GRADDIST' for this primary key change
-- also update the label and description for this key
-- then delete the old primary code "NONGRAD"
ALTER TABLE REPORT_TYPE_CODE
DISABLE CONSTRAINT REPORT_TYPE_CODE_PK;

UPDATE REPORT_TYPE_CODE
SET REPORT_TYPE_CODE = 'GRADDIST',
LABEL = 'Students Satisfying Grad Requirements',
DESCRIPTION = 'Printed report run that lists students who have been issued transcripts and certificates in the batch distribution run.',
EFFECTIVE_DATE = to_date('21-07-19','RR-MM-DD')
WHERE REPORT_TYPE_CODE = 'GRAD';

-- 5. update label and description for existing keys and new keys
UPDATE REPORT_TYPE_CODE
SET LABEL = 'TVRs for Projected Graduating Students',
DESCRIPTION = 'On-demand PDF amalgamation of TVRs for all current students reported on a graduation program, in grade 12 or subgrade AD, who are projected to graduate (or have already graduated) based on course and assessment registrations or completions.',
EFFECTIVE_DATE = to_date('21-07-08','RR-MM-DD')
WHERE REPORT_TYPE_CODE = 'TVRGRAD';

UPDATE REPORT_TYPE_CODE
SET LABEL = 'TVRs for Projected Non-Graduating Students',
DESCRIPTION = 'On-demand PDF amalgamation of TVRs for all current students reported on a graduation program, in grade 12 or subgrade AD, who are not projected to graduate based on missing course or assessment registrations.',
EFFECTIVE_DATE = to_date('21-07-08','RR-MM-DD')
WHERE REPORT_TYPE_CODE = 'TVRNONGRAD';

UPDATE REPORT_TYPE_CODE
SET LABEL = 'Projected Non-Graduates - Summary Report',
DESCRIPTION = 'A list of all current students reported on a graduation program, in grade 12 or subgrade AD, who are not projected to graduate based on missing course registrations or assessment registrations.  Produced as part of TVR Batch Run.',
EFFECTIVE_DATE = to_date('21-07-19','RR-MM-DD')
WHERE REPORT_TYPE_CODE = 'NONGRADPRJ';

UPDATE REPORT_TYPE_CODE
SET LABEL = 'Transcript Verification Report',
DESCRIPTION = 'A student-level report that echoes back to most current course, exam and provincial assessment activity (as well as the student''s projected or actual graduation status) that GRAD has on file for the student, as has been reported to the Ministry TRAX system by schools.',
EFFECTIVE_DATE = to_date('21-07-19','RR-MM-DD')
WHERE REPORT_TYPE_CODE = 'ACHV';

UPDATE REPORT_TYPE_CODE
SET LABEL = 'Graduated Students (MM YY to MM YY)',
DESCRIPTION = 'A daily, cumulative list of student in the current cycle who have graduated, based on the latest information submitted by the school. Produced as part of the Batch Graduation Algorithm Run.',
EFFECTIVE_DATE = to_date('21-07-19','RR-MM-DD')
WHERE REPORT_TYPE_CODE = 'GRADREG';

-- insert new codes into this table
Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'NONGRADREG',
'Not-Yet Graduated Students (MM YY to MM YY)',
'A daily, cumulative list of student in the current cycle who have not-yet graduated, based on the latest information submitted by the school.  Produced as part of the Batch Graduation Algorithm Run.',
50,
to_date('21-07-19','RR-MM-DD'));

Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'XML',
'XML Preview',
'A PDF display of what is currently available to a Post-Secondary institution that has been authorized by a student to receive transcript updates via XML data transfer.
Shows the completed course and assessment data along with the in progress course and assessment data.',
55,
to_date('21-07-19','RR-MM-DD'));

Insert into REPORT_TYPE_CODE
(REPORT_TYPE_CODE,LABEL,DESCRIPTION,DISPLAY_ORDER,EFFECTIVE_DATE)
values (
'TRAN',
'Student Transcript',
'An official report of a students'' completed course and assessment data.',
60,
to_date('21-07-19','RR-MM-DD'));


--6. enable primary key in REPORT_TYPE_CODE again
ALTER TABLE REPORT_TYPE_CODE
ENABLE CONSTRAINT REPORT_TYPE_CODE_PK;

--7. enable foreign keys again in two child tables
ALTER TABLE STUDENT_REPORT
ENABLE CONSTRAINT STUD_REPORT_REPORT_TYPE_CD_FK;

ALTER TABLE SCHOOL_REPORT
ENABLE CONSTRAINT SCH_REP_TYPE_CD_FK;
