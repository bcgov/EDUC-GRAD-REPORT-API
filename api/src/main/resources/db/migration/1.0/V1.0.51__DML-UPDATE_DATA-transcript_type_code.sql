UPDATE TRANSCRIPT_TYPE_CODE SET DESCRIPTION = REPLACE(DESCRIPTION, '1996', '1995')
WHERE TRANSCRIPT_TYPE_CODE IN ('BC1996-PUB','YU1996-PUB','BC1996-IND');