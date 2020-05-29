DROP TABLE S_COURSESFULL;
CREATE TABLE S_COURSESFULL(
id VARCHAR(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,week_day INTEGER,start INTEGER,end INTEGER,classroom VARCHAR(100),name VARCHAR(100),teacher VARCHAR(100),start_rgt INTEGER,end_rgt INTEGER); 

CREATE SEQUENCE ID_S_COURSESFULL_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger S_COURSESFULL
before insert on S_COURSESFULL
for each row
begin
select ID_S_COURSESFULL_SEQUENCE.nextval into :new.id from dual;
end;
