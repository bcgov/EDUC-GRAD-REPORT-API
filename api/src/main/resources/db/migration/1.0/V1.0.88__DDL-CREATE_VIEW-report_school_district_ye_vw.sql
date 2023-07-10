create or replace view REPORT_SCHOOL_DISTRICT_YE_VW AS
select
    c.GRADUATION_STUDENT_RECORD_ID,
    t.PAPER_TYPE
from student_certificate c
    join certificate_type_code t on c.CERTIFICATE_TYPE_CODE = t.CERTIFICATE_TYPE_CODE
where
    c.document_status_code='COMPL'
    and c.distribution_date is null
union
select
    t.GRADUATION_STUDENT_RECORD_ID,
    'YED4' PAPER_TYPE
from student_transcript t
where
    t.document_status_code='COMPL'
    and t.distribution_date is null
;


