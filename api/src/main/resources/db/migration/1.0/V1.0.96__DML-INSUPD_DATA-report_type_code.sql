UPDATE REPORT_TYPE_CODE SET DISPLAY_ORDER=26 WHERE REPORT_TYPE_CODE='DISTREP_YE_SC';

UPDATE REPORT_TYPE_CODE SET DISPLAY_ORDER=37 WHERE REPORT_TYPE_CODE='TVRGRAD';

UPDATE REPORT_TYPE_CODE SET DISPLAY_ORDER=80 WHERE REPORT_TYPE_CODE='ADDRESS_LABEL_YE';

UPDATE REPORT_TYPE_CODE SET DISPLAY_ORDER=90 WHERE REPORT_TYPE_CODE='ADDRESS_LABEL_PSI';

UPDATE REPORT_TYPE_CODE SET DISPLAY_ORDER=100 WHERE REPORT_TYPE_CODE='ADDRESS_LABEL_SCHL';


INSERT INTO REPORT_TYPE_CODE (REPORT_TYPE_CODE, LABEL, DESCRIPTION, DISPLAY_ORDER, EFFECTIVE_DATE)
VALUES('GRADREGARC', 'Archived Graduated Students (MM YYYY to MM YYYY) Report', 'The final daily, cumulative list of student in the current cycle who have graduated, based on the latest information submitted by the school. Produced as part of the Batch Graduation Algorithm Run.', 110, TIMESTAMP '2023-09-01 00:00:00.000000');

INSERT INTO REPORT_TYPE_CODE (REPORT_TYPE_CODE, LABEL, DESCRIPTION, DISPLAY_ORDER, EFFECTIVE_DATE)
VALUES('NONGRADREGARC', 'Archived Not-Yet Graduated Students (MM YYYY to MM YYYY) Report', 'The final daily, cumulative list of student in the current cycle who have not-yet graduated, based on the latest information submitted by the school. Produced as part of the Batch Graduation Algorithm Run.', 120, TIMESTAMP '2023-09-01 00:00:00.000000');

INSERT INTO REPORT_TYPE_CODE (REPORT_TYPE_CODE, LABEL, DESCRIPTION, DISPLAY_ORDER, EFFECTIVE_DATE)
VALUES('NONGRADPRJARC', 'Archived Projected Non-Graduates - Summary Report (MM YYYY to MM YYYY)', 'The final list of grade 12 or AD students on a graduation program who were not projected to graduate based on missing course registrations or assessment registrations submitted by the school in the reporting cycle.', 130, TIMESTAMP '2023-09-01 00:00:00.000000');

