create or replace view REPORT_SCHOOL_DISTRICT_YE_VW AS
select
    c.GRADUATION_STUDENT_RECORD_ID,
    c.CERTIFICATE_TYPE_CODE,
    t.PAPER_TYPE
from student_certificate c
    join certificate_type_code t on c.CERTIFICATE_TYPE_CODE = t.CERTIFICATE_TYPE_CODE
where
    c.document_status_code='COMPL'
    and c.distribution_date is null
union
select
    t.GRADUATION_STUDENT_RECORD_ID,
    t.TRANSCRIPT_TYPE_CODE,
    'YED4' PAPER_TYPE
from student_transcript t
    join transcript_type_code c on c.TRANSCRIPT_TYPE_CODE = t.TRANSCRIPT_TYPE_CODE
where
  t.document_status_code='COMPL'
  and exists (
    select 'x'
    from student_certificate
    where
      GRADUATION_STUDENT_RECORD_ID = t.GRADUATION_STUDENT_RECORD_ID
      and document_status_code = 'COMPL'
      and distribution_date is null
)
union
select
    t.GRADUATION_STUDENT_RECORD_ID,
    t.TRANSCRIPT_TYPE_CODE,
    'YED4' PAPER_TYPE
from student_transcript t
    join transcript_type_code c on c.TRANSCRIPT_TYPE_CODE = t.TRANSCRIPT_TYPE_CODE
where
    t.document_status_code='COMPL'
    and t.distribution_date is null
;


