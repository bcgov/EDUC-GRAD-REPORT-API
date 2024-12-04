Insert into REPORT_TYPE_CODE (REPORT_TYPE_CODE, LABEL, DESCRIPTION, DISPLAY_ORDER, EFFECTIVE_DATE)
VALUES
    ('GRADPRJ','Projected Graduates - Summary Report (MM YYYY to MM YYYY)', 'A list of all current students reported on a graduation program, in grade 12 or AD, who are projected to graduate based on future course registrations or assessment registrations. Produced as part of TVR Batch Run.', 140, to_date('2024-05-01','RR-MM-DD'));

Insert into REPORT_TYPE_CODE (REPORT_TYPE_CODE, LABEL, DESCRIPTION, DISPLAY_ORDER, EFFECTIVE_DATE)
VALUES
    ('GRADPRJARC','Archived Projected Graduates - Summary Report (MM YYYY to MM YYYY)', 'The final list of all current students reported on a graduation program, in grade 12 or AD, who are projected to graduate based on future course registrations or assessment registrations.', 150, to_date('2024-05-01','RR-MM-DD'));

