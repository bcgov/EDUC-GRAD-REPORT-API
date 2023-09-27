UPDATE REPORT_TYPE_CODE 
SET DESCRIPTION='The final cumulative list of students who graduated, based on the last information submitted by the school in the reporting cycle.' 
WHERE REPORT_TYPE_CODE='GRADREGARC';

UPDATE REPORT_TYPE_CODE 
SET DESCRIPTION='The final list of grade 12 or AD students on a graduation program who were not projected to graduate based on missing course registrations or assessment registrations submitted by the school in the reporting cycle.' 
WHERE REPORT_TYPE_CODE='NONGRADPRJARC';

UPDATE REPORT_TYPE_CODE 
SET DESCRIPTION='The final cumulative list of students who had not-yet graduated, based on the last information submitted by the school in the reporting cycle.' 
WHERE REPORT_TYPE_CODE='NONGRADREGARC';

