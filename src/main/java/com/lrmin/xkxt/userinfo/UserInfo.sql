DROP TABLE T_USERINFO;
CREATE TABLE T_USERINFO(
id VARCHAR(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,account_id VARCHAR(100),name VARCHAR(100),native_place VARCHAR(100),phone VARCHAR(100),email VARCHAR(100),address VARCHAR(100),avater VARCHAR(100)); 

CREATE SEQUENCE ID_T_USERINFO_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger T_USERINFO
before insert on T_USERINFO
for each row
begin
select ID_T_USERINFO_SEQUENCE.nextval into :new.id from dual;
end;
