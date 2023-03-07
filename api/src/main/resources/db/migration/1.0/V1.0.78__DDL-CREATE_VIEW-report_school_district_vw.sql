create or replace view REPORT_SCHOOL_DISTRICT_YE_VW AS
select
    CAST(c.GRADUATION_STUDENT_RECORD_ID AS VARCHAR2(100)) as GRADUATION_STUDENT_RECORD_ID
from student_certificate c
where
    c.document_status_code='COMPL'
    and c.distribution_date is null
union
select
    CAST(t.GRADUATION_STUDENT_RECORD_ID AS VARCHAR2(100)) as GRADUATION_STUDENT_RECORD_ID
from student_transcript t
where
    t.document_status_code='COMPL'
    and ( exists (
        select 'x' from student_certificate where GRADUATION_STUDENT_RECORD_ID = t.GRADUATION_STUDENT_RECORD_ID and document_status_code='COMPL' and distribution_date is null
    )
    or (t.document_status_code='COMPL' and t.distribution_date is not null and t.update_date is not null and t.distribution_date < t.update_date))
;



create or replace view REPORT_SCHOOL_DISTRICT_VW AS
select
    CAST(c.GRADUATION_STUDENT_RECORD_ID AS VARCHAR2(100)) as GRADUATION_STUDENT_RECORD_ID
from student_certificate c
where
    c.document_status_code='COMPL'
    and c.distribution_date is null
union
select
    CAST(t.GRADUATION_STUDENT_RECORD_ID AS VARCHAR2(100)) as GRADUATION_STUDENT_RECORD_ID
from student_transcript t
where
    t.document_status_code='COMPL'
    and exists (
        select 'x' from student_certificate where GRADUATION_STUDENT_RECORD_ID = t.GRADUATION_STUDENT_RECORD_ID and document_status_code='COMPL' and distribution_date is null
    )
;