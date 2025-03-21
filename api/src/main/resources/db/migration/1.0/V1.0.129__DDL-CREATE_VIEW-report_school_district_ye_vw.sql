create or replace view REPORT_SCHOOL_DISTRICT_YE_VW AS
select
    c.GRADUATION_STUDENT_RECORD_ID,
    c.CERTIFICATE_TYPE_CODE,
    t.PAPER_TYPE,
    'SCHOOL_AT_GRAD' as REPORTING_SCHOOL_TYPE_CODE
from student_certificate c
         join certificate_type_code t on c.CERTIFICATE_TYPE_CODE = t.CERTIFICATE_TYPE_CODE
where
    c.document_status_code='COMPL'
  and c.distribution_date is null
union
select
    t.GRADUATION_STUDENT_RECORD_ID,
    t.TRANSCRIPT_TYPE_CODE,
    'YED4' as PAPER_TYPE,
    'SCHOOL_AT_GRAD' as REPORTING_SCHOOL_TYPE_CODE
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
    'YED4' as PAPER_TYPE,
    'SCHOOL_OF_RECORD' as REPORTING_SCHOOL_TYPE_CODE
from student_transcript t
         join transcript_type_code c on c.TRANSCRIPT_TYPE_CODE = t.TRANSCRIPT_TYPE_CODE
where
    t.document_status_code='COMPL'
  and t.distribution_date is null
  AND NOT EXISTS (
    SELECT 1 FROM student_certificate sc
    WHERE sc.GRADUATION_STUDENT_RECORD_ID = t.GRADUATION_STUDENT_RECORD_ID
      AND sc.document_status_code = 'COMPL'
      AND sc.distribution_date IS NULL
);