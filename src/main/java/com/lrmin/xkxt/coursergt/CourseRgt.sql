DROP TABLE T_COURSERGT;
CREATE TABLE T_COURSERGT(
id VARCHAR(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,course_id VARCHAR(100),week_day INTEGER,start INTEGER,end INTEGER,classroom VARCHAR(100)); 

CREATE SEQUENCE ID_T_COURSERGT_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger T_COURSERGT
before insert on T_COURSERGT
for each row
begin
select ID_T_COURSERGT_SEQUENCE.nextval into :new.id from dual;
end;
