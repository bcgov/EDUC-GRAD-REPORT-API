-- This script is for ticket GRAD2-1464, since some new descriptions are long
-- need to increase the column description size from 255 to 400 in table REPORT_TYPE_CODE

ALTER TABLE REPORT_TYPE_CODE MODIFY (DESCRIPTION VARCHAR2(400));