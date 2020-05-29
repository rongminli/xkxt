DROP TABLE T_COURSE;
CREATE TABLE T_COURSE(
id VARCHAR(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,name VARCHAR(100),teacher VARCHAR(100),time_frame VARCHAR(100)); 

CREATE SEQUENCE ID_T_COURSE_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger T_COURSE
before insert on T_COURSE
for each row
begin
select ID_T_COURSE_SEQUENCE.nextval into :new.id from dual;
end;
