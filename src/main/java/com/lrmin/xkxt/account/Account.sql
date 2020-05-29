DROP TABLE S_ACCOUNT;
CREATE TABLE S_ACCOUNT(
id VARCHAR2(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,name VARCHAR2(100),nickname VARCHAR2(100),password VARCHAR2(100),registration_at TIMESTAM,); 

CREATE SEQUENCE ID_S_ACCOUNT_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger S_ACCOUNT
before insert on S_ACCOUNT
for each row
begin
select ID_S_ACCOUNT_SEQUENCE.nextval into :new.id from dual;
end;
