### Just open the Oracle sql command prompt and login to administrator user and copy paste the following codes for creating dummy database:

```SQL
create user reservation identified by manager;

grant dba to reservation;

commit;

connect reservation/manager;

create table admin6(uname varchar2(40) primary key,name varchar2(40),
	pword varchar2(50),mail_id varchar2(60),phone_no varchar2(12));
	
create table train6(tr_no number(10) primary key,tr_name varchar2(70),
	from_stn varchar2(20),to_stn varchar2(20),available number(5),fare number(5));

create table register(uname varchar2(40) primary key,pword varchar2(50),
	fname varchar2(40),lname varchar2(40),
	addr varchar2(100), phno varchar2(12), mailid varchar2(60));

insert into admin6 values('admin','admin','admin','admin@train.com','9874561230');
insert into admin6 values('shashi','shashi','admin','shashi@train.com','98323561230');
insert into train6 values(10101,'Jodhpur Exp','Howrah','Jodhpur',152,450);
insert into train6 values(10102,'Mumbai Mail','Gaya','Mumbai',182,650);
insert into register values('shashi','shashi','Shashi','Raj','Tekari, Gaya, Bihar',954745222,'shashiraj.972@gmail.com');

commit;
```
