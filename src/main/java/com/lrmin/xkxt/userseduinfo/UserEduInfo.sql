DROP TABLE T_USEREDUINFO;
CREATE TABLE T_USEREDUINFO(
id VARCHAR2(100),create_at TIMESTAMP,last_update_at TIMESTAMP,version INTEGER,user_id VARCHAR2(100),student_num VARCHAR2(100),grade VARCHAR2(100),college VARCHAR2(100),magor VARCHAR2(100),_class VARCHAR2(100,); 

CREATE SEQUENCE ID_T_USEREDUINFO_SEQUENCE
INCREMENT BY 1                      
START WITH 1                         
NOMAXVALUE                    
MINVALUE 1                             
NOCYCLE                    
CACHE 20                               
ORDER   ; 

create or replace trigger T_USEREDUINFO
before insert on T_USEREDUINFO
for each row
begin
select ID_T_USEREDUINFO_SEQUENCE.nextval into :new.id from dual;
end;
