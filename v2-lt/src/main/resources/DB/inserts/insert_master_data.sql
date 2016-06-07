--------------------------------------------------------
--  File created - Thursday-August-27-2015   
--------------------------------------------------------
REM INSERTING into TSFACTOR
Insert into TSFACTOR (ID,CREATEDBY,CREATEDDATE,DEPRECATED,DEPRECATEDBY,DEPRECATEDDATE,DESCRIPTION,LASTMODIFIEDBY,LASTMODIFIEDDATE,VERSION,AMTSFACTOR,EMPTSFACTOR,FACTORORDERINDEX,NAME,EMPTOEMPTSFACTORMULTIPLICITY,FACTORDESCRIPTION,ORGTOEMPTSFACTORMULTIPLICITY) values (28,null,null,null,null,null,null,null,null,0,'departments:name','departments:name',1,'Department','multiple','Depends on which department employee works.','multiple');
Insert into TSFACTOR (ID,CREATEDBY,CREATEDDATE,DEPRECATED,DEPRECATEDBY,DEPRECATEDDATE,DESCRIPTION,LASTMODIFIEDBY,LASTMODIFIEDDATE,VERSION,AMTSFACTOR,EMPTSFACTOR,FACTORORDERINDEX,NAME,EMPTOEMPTSFACTORMULTIPLICITY,FACTORDESCRIPTION,ORGTOEMPTSFACTORMULTIPLICITY) values (29,null,null,null,null,null,null,null,null,0,'roles:role','roles:role',3,'Role','multiple','Depends on what roles the employee plays.','multiple');
Insert into TSFACTOR (ID,CREATEDBY,CREATEDDATE,DEPRECATED,DEPRECATEDBY,DEPRECATEDDATE,DESCRIPTION,LASTMODIFIEDBY,LASTMODIFIEDDATE,VERSION,AMTSFACTOR,EMPTSFACTOR,FACTORORDERINDEX,NAME,EMPTOEMPTSFACTORMULTIPLICITY,FACTORDESCRIPTION,ORGTOEMPTSFACTORMULTIPLICITY) values (30,null,null,null,null,null,null,null,null,0,'departments:projects:projectName','projects:projectName',2,'Project','multiple','Depends on which project employee works on.','multiple');
Insert into TSFACTOR (ID,CREATEDBY,CREATEDDATE,DEPRECATED,DEPRECATEDBY,DEPRECATEDDATE,DESCRIPTION,LASTMODIFIEDBY,LASTMODIFIEDDATE,VERSION,AMTSFACTOR,EMPTSFACTOR,FACTORORDERINDEX,NAME,EMPTOEMPTSFACTORMULTIPLICITY,FACTORDESCRIPTION,ORGTOEMPTSFACTORMULTIPLICITY) values (31,null,null,null,null,null,null,null,null,0,'grades:grade','grade:grade',4,'Grade','single','Depends on grade of employee.','multiple');
--Insert into TSFACTOR (ID,CREATEDBY,CREATEDDATE,DEPRECATED,DEPRECATEDBY,DEPRECATEDDATE,DESCRIPTION,LASTMODIFIEDBY,LASTMODIFIEDDATE,VERSION,AMTSFACTOR,EMPTSFACTOR,FACTORORDERINDEX,NAME,EMPTOEMPTSFACTORMULTIPLICITY,FACTORDESCRIPTION,ORGTOEMPTSFACTORMULTIPLICITY) values (31,null,null,null,null,null,null,null,null,0,'employees:firstName','firstName',1,'Employee-FN','single','Depends on employee firstname.','multiple');



