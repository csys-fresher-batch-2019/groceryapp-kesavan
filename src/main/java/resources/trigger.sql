
----Userid trigger func


CREATE OR REPLACE TRIGGER userid_tr
BEFORE INSERT ON usersdata
FOR EACH ROW
BEGIN
IF :new.user_id IS NULL THEN 
SELECT userid_seq.NEXTVAL 
INTO :new.user_id
FROM dual;
END IF;
END;


----Orderid trigger func

CREATE OR REPLACE TRIGGER orderid_tr
BEFORE INSERT ON orderdata
FOR EACH ROW
BEGIN
IF :new.order_id IS NULL THEN 
SELECT orderid_seq.NEXTVAL 
INTO :new.order_id
FROM dual;
END IF;
END;