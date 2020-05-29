DROP TABLE S_ACCOUNTWX;
CREATE TABLE S_ACCOUNTWX(
id VARCHAR(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,open_id VARCHAR(100),account_id VARCHAR(100)); 

CREATE SEQUENCE ID_S_ACCOUNTWX_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger S_ACCOUNTWX
before insert on S_ACCOUNTWX
for each row
begin
select ID_S_ACCOUNTWX_SEQUENCE.nextval into :new.id from dual;
end;
