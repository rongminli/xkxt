DROP TABLE T_USERSCOURSES;
CREATE TABLE T_USERSCOURSES(
id VARCHAR(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,account_id VARCHAR(100),course_id VARCHAR(100)); 

CREATE SEQUENCE ID_T_USERSCOURSES_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger T_USERSCOURSES
before insert on T_USERSCOURSES
for each row
begin
select ID_T_USERSCOURSES_SEQUENCE.nextval into :new.id from dual;
end;
