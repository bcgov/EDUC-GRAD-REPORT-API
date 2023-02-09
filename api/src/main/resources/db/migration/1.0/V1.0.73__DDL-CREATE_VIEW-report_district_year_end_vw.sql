create or replace view REPORT_DISTRICT_YEAR_END_VW as
select
    count(c.graduation_student_record_id) cert_count
    ,c.certificate_type_code 
    ,t.trans_count 
from 
    student_certificate c, 
    (
        select count(t.graduation_student_record_id) trans_count from student_transcript t where exists (
            select 'x' from student_certificate 
            where graduation_student_record_id = t.graduation_student_record_id 
            and distribution_date is null 
        )
    ) t 
where 
    c.distribution_date is null 
group by 
    c.certificate_type_code
    ,t.trans_count;

