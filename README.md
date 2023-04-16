# Train Ticket Reservation System 
<!-- - Youtube video for local setup of similar project: https://www.youtube.com/watch?v=mLFPodZO8Iw -->
- Youtube video for Step by Step Guide on Local Setup: https://www.youtube.com/watch?v=Wd2GlEJJJlw
<!-- - Live Url: https://traintickets.herokuapp.com <br>  -->
<!-- - Login Credentials: admin/admin -->

### About:
This project is about the Train-Ticket-Reservation-System which is used to view Train Schedule, search trains, Seat availability, Train timings. We can also enquire about fare of different trains. We can get information about train between two stations. We can book seats online. This provides a safe and secure seat reservation system. 
### Online Train Information and Reservation
<span style="color:blue">**This Website is built for following purpose:-**</span>
- View Trains Schedule
- Search Trains
- Seat Availability
- Train Timings
- Fare Enquiry
- Trains Between Stations
- Booking seats online.
- Login and Logout Security
- Password Changes
- Payment Gateway
- Ticket Booking History

<span style="color:blue">**The Admin have the following access to this website:-**</span>
- Login
- Add Trains
- Update Trains
- Remove  or cancle Trains
- View Trains
- Profile Edit
- Logout

<span style="color:blue">**The Users have the following Access:-**</span>
- Register
- Login
- View Trains
- Check Seat Availability
- Search Trains
- Train Avaiablity and Fare Between Stations
- Books Tickets
- View Booking History
- View Profile
- Update Profile
- Change Password
- Logout

### Technologies used:-
1. Front-End Development:
- HTML
- CSS
- Bootstrap

2. Back-End Development
- Java [J2EE]
- JDBC
- Servlet
- Oracle ( SQL )

### ==== Software And Tools Required ======
- : Git [https://www.youtube.com/watch?v=gv7VPQ4LZ7g]
- : Java JDK 8+ [https://www.youtube.com/watch?v=O9PWH9SeTTE]
- : Eclipse EE [https://www.youtube.com/watch?v=8aDsEV7txXE]
- : Apache Maven [https://www.youtube.com/watch?v=jd2zx3dLjuw]
- : Tomcat v8.0+ [https://youtu.be/mLFPodZO8Iw?t=903]
- : Oracle (SQL) / SQL PLUS [https://www.youtube.com/watch?v=ZYOqykEDSqU]
- : Oracle SQL Developer [https://www.youtube.com/watch?v=2a1JKIGVtd0]

### ========== Dummy Database Initialization ===========

STEP 1: Open SQL Plus OR SQL Developer

STEP 2: Login and connect to database using administrator username and password

STEP 3 :Execute the below command first to create a new user:

```SQL

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;  
CREATE USER RESERVATION IDENTIFIED BY MANAGER;
GRANT DBA TO RESERVATION;
COMMIT;

```
NOTE: If the above command fails for alter session issues, try to remove first line and then execute it.

STEP 4: Now execute the below sql query in same terminal

```SQL

CONNECT RESERVATION/MANAGER;
CREATE TABLE "RESERVATION"."CUSTOMER" 
(	
"MAILID" VARCHAR2(40) PRIMARY KEY, 
"PWORD" VARCHAR2(20) NOT NULL, 
"FNAME" VARCHAR2(20) NOT NULL, 
"LNAME" VARCHAR2(20), 
"ADDR" VARCHAR2(100), 
"PHNO" NUMBER(12) NOT NULL
);

CREATE TABLE "RESERVATION"."ADMIN"
(	
"MAILID" VARCHAR2(40) PRIMARY KEY, 
"PWORD" VARCHAR2(20) NOT NULL, 
"FNAME" VARCHAR2(20) NOT NULL, 
"LNAME" VARCHAR2(20), 
"ADDR" VARCHAR2(100), 
"PHNO" NUMBER(12) NOT NULL
);


CREATE TABLE "RESERVATION"."TRAIN" 
(	
"TR_NO" NUMBER(10) PRIMARY KEY, 
"TR_NAME" VARCHAR2(70) NOT NULL, 
"FROM_STN" VARCHAR2(20) NOT NULL, 
"TO_STN" VARCHAR2(20) NOT NULL, 
"SEATS" NUMBER(4) NOT NULL, 
"FARE" NUMBER(6,2) NOT NULL 
);

CREATE TABLE "RESERVATION"."HISTORY" 
(	
"TRANSID" VARCHAR2(36) PRIMARY KEY, 
"MAILID" VARCHAR2(40) REFERENCES "RESERVATION"."CUSTOMER"(MAILID), 
"TR_NO" NUMBER(10),
"DATE" DATE,
"FROM_STN" VARCHAR2(20) NOT NULL, 
"TO_STN" VARCHAR2(20) NOT NULL, 
"SEATS" NUMBER(3) NOT NULL, 
"AMOUNT" NUMBER(8,2) NOT NULL
);

COMMIT;

INSERT INTO RESERVATION.ADMIN VALUES('admin@demo.com','admin','System','Admin','Demo Address 123 colony','9874561230');
INSERT INTO RESERVATION.CUSTOMER VALUES('shashi@demo.com','shashi','Shashi','Raj','Kolkata, West Bengal',954745222);

INSERT INTO RESERVATION.TRAIN VALUES(10001,'JODHPUR EXP','HOWRAH','JODHPUR', 152, 490.50);
INSERT INTO RESERVATION.TRAIN VALUES(10002,'YAMUNA EXP','GAYA','DELHI', 52, 550.50);
INSERT INTO RESERVATION.TRAIN VALUES(10003,'NILANCHAL EXP','GAYA','HOWRAH', 92, 451);
INSERT INTO RESERVATION.TRAIN VALUES(10004,'JAN SATABDI EXP','RANCHI','PATNA', 182, 550);
INSERT INTO RESERVATION.TRAIN VALUES(10005,'GANGE EXP','MUMBAI','KERALA', 12, 945);
INSERT INTO RESERVATION.TRAIN VALUES(10006,'GARIB RATH EXP','PATNA','DELHI', 1, 1450.75);

INSERT INTO RESERVATION.HISTORY VALUES('BBC374-NSDF-4673','shashi@demo.com',10001,TO_DATE('02-FEB-2024'), 'HOWRAH', 'JODHPUR', 2, 981);
INSERT INTO RESERVATION.HISTORY VALUES('BBC375-NSDF-4675','shashi@demo.com',10004,TO_DATE('12-JAN-2024'), 'RANCHI', 'PATNA', 1, 550);
INSERT INTO RESERVATION.HISTORY VALUES('BBC373-NSDF-4674','shashi@demo.com',10006,TO_DATE('22-JULY-2024'), 'PATNA', 'DELHI', 3, 4352.25);

COMMIT;
```
STEP 5: Now Execute the below query one by one to check if the tables are created successfully
```SQL
SELECT * FROM ADMIN;
SELECT * FROM CUSTOMER;
SELECT * FROM TRAIN;
SELECT * FROM HISTORY;

```
Note: If any of the above commands fails, please try to fix it first and then proceed to next step
	
### ====== Importing and Running the Project Through Eclipse EE ===========
Step 0: Open Eclipse Enterprise Edition. [Install if not available](https://www.youtube.com/watch?v=8aDsEV7txXE)

Step 1: Click On File > Import > Git > Projects From Git > Clone Uri  > Paste The Repository Url: ```https://github.com/shashirajraja/Train-Ticket-Reservation-System.git``` > Next > Select Master Branch > Next > Finish

Step 2.A: Right Click on Project > Run as > Maven Build > In the goals field enter "clean install" > apply > run

Step 2.B: Right Click On Project > Build Path > Configure Build Path > Libraries > Remove And Update Any Libraries With Red Mark > Finish

Step 3: [Only if Tomcat v8.0 is not Configured in Eclipse]: Right Click On Project > Run As > Run On Server > Select Tomcat v8.0 > (Select Tomcat V8.0 Installation Location If Asked) Next > Add <project-name> > Finish

Step 4: In The Server Tab > Double Click On Tomcat Server > Ports  > Change The Port Number For Http/1.1 To 8083 > Close And Save

Step 5: Right Click On Project > Run As > Run On Server > Select Tomcat V8.0 > Next > Add All> Done

Step 6: Check Running The Site At  <a Href="Http://localhost:8083/trainbook/">http://localhost:8083/trainbook/</a>

Step 7: Default Username And Password For Admin Is "admin@demo.com" And "admin"

Step 8: Default Username And Password For User Is "shashi@demo.com" And "shashi"



### The Screenshots of some of the  webPages of this project are Here:

1. Login Page
<img width="100%" alt="Login to Book Trains" src="https://user-images.githubusercontent.com/34605595/232219369-85b55a1d-6640-4821-941a-dcca08036fbe.png">

2. Register New User
<img width="100%" alt="Register New User" src="https://user-images.githubusercontent.com/34605595/232219485-2b00949a-be20-44f7-b6c1-107213221f94.png">

3. User Profile
<img width="100%" alt="View User Profile" src="https://user-images.githubusercontent.com/34605595/232219729-2720e50f-e14b-4253-831a-85c59e3054b3.png">

4. Search Trains Between Stations
<img width="100%" alt="Search Trains Between Stations" src="https://user-images.githubusercontent.com/34605595/232220357-54b634d6-afae-427c-b3af-57b372b70906.png">

5. View Trains
<img width="100%" alt="View Available Trains" src="https://user-images.githubusercontent.com/34605595/232219905-983eeefe-977b-40ad-a695-4ec577272dcc.png">

7. Book Trains
<img width="100%" alt="Book Trains Project" src="https://user-images.githubusercontent.com/34605595/232220107-415b251f-90b9-4e70-aff8-e94d370927f6.png">

8. Payment Gateway
<img width="100%" alt="Pay to Book Trains" src="https://user-images.githubusercontent.com/34605595/232220744-351c2c6d-e1f6-49ad-a11b-7680aa63dbe3.png">

9. Booked Ticket Information
<img width="100%" alt="Show Booked Ticket Details" src="https://user-images.githubusercontent.com/34605595/232220935-654bda38-cbde-4203-84b8-3078a32ac6ec.png">

10. Ticket Booking History
<img width="100%" alt="All Ticket Booking History" src="https://user-images.githubusercontent.com/34605595/232220491-3e7996cb-a54c-4375-a35a-6ab1d211a001.png">

11. Fare Enquiry
<img alt="Fare Enquiry between stations" src="https://github.com/shashirajraja/Train-Ticket-Reservation-System/blob/master/Screenshots/fareenquiry.png" width="100%">

12. Change Password
<img alt="Change user Password" src="https://github.com/shashirajraja/Train-Ticket-Reservation-System/blob/master/Screenshots/passwordchange.png" width="100%">

13. Add Trains By Admin
<img alt="Admin Home" src="https://github.com/shashirajraja/Train-Ticket-Reservation-System/blob/master/Screenshots/addtrains.png" width="100%">


#### "Suggestions and project Improvement are Invited"
#### Shashi Raj
##### Project Leader
