create or replace view REPORT_SCHOOL_DISTRICT_YE_VW AS
select
    c.GRADUATION_STUDENT_RECORD_ID
from student_certificate c
where
        c.document_status_code='COMPL'
  and c.distribution_date is null
union
select
    t.GRADUATION_STUDENT_RECORD_ID
from student_transcript t
where
    t.document_status_code='COMPL'
  and t.distribution_date is null
;


