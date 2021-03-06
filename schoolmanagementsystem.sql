CREATE TABLE  admininfo  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   Name  varchar(45) DEFAULT NULL,
   CNIC  varchar(45) DEFAULT NULL,
   PhoneNo  varchar(45) DEFAULT NULL,
   username  varchar(45) NOT NULL,
   password  varchar(45) NOT NULL,
   Image  longblob,
  PRIMARY KEY ( id ),
  UNIQUE KEY  username_UNIQUE  ( username )
) 
CREATE TABLE  admissionfee  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   AdmissionFee  int(11) DEFAULT '0',
   Security  int(11) DEFAULT '0',
  PRIMARY KEY ( id )
)
CREATE TABLE  booksprice  (
   Class  varchar(45) NOT NULL,
   section  varchar(45) NOT NULL,
   price  int(11) DEFAULT NULL,
  PRIMARY KEY ( Class , section )
)
CREATE TABLE  classfee  (
   ClassName  varchar(45) NOT NULL,
   Section  varchar(45) NOT NULL,
   ClassFee  int(11) DEFAULT '0',
   GeneratorFee  int(11) DEFAULT '0',
   FunctionFee  int(11) DEFAULT '0',
   ExamsFee  int(11) DEFAULT '0',
   PartyFee  int(11) DEFAULT '0',
   SportsFee  int(11) DEFAULT '0',
   TripFee  int(11) DEFAULT '0',
   OtherCharges  int(11) DEFAULT '0',
   BooksFee  int(11) DEFAULT '0',
   StationaryFee  int(11) DEFAULT '0',
   count  int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY ( count , ClassName , Section )
)
CREATE TABLE  classinfo  (
   ClassName  varchar(45) NOT NULL,
   Section  varchar(45) NOT NULL,
   Subject1Name  varchar(45) DEFAULT NULL,
   Subject2Name  varchar(45) DEFAULT NULL,
   Subject3Name  varchar(45) DEFAULT NULL,
   Subject4Name  varchar(45) DEFAULT NULL,
   Subject5Name  varchar(45) DEFAULT NULL,
   Subject6Name  varchar(45) DEFAULT NULL,
   Subject7Name  varchar(45) DEFAULT NULL,
   Subject8Name  varchar(45) DEFAULT NULL,
   Subject9Name  varchar(45) DEFAULT NULL,
   Subject10Name  varchar(45) DEFAULT NULL,
   Subject11Name  varchar(45) DEFAULT NULL,
   Subject12Name  varchar(45) DEFAULT NULL,
  PRIMARY KEY ( ClassName , Section )
)
CREATE TABLE  employededucation  (
   employeeid  varchar(45) NOT NULL,
   EmployeeName  varchar(45) DEFAULT NULL,
   Post  varchar(45) DEFAULT NULL,
   DeductionAmount  int(11) DEFAULT '0',
   SalaryAfterDeducation  int(11) DEFAULT '0',
   Reason  varchar(45) DEFAULT NULL,
   Month  varchar(45) NOT NULL,
   year  varchar(45) NOT NULL,
  PRIMARY KEY ( employeeid , Month , year )
)
CREATE TABLE  employeeallowance  (
   employeeid  varchar(45) NOT NULL,
   EmployeeName  varchar(45) DEFAULT NULL,
   Post  varchar(45) DEFAULT NULL,
   OverTime  int(11) DEFAULT '0',
   Medical  int(11) DEFAULT '0',
   Bounce  int(11) DEFAULT '0',
   HomeAllowance  int(11) DEFAULT '0',
   TransportAllowance  int(11) DEFAULT '0',
   Others  int(11) DEFAULT '0',
   TotalAllownace  int(11) DEFAULT '0',
   Month  varchar(45) NOT NULL,
   Year  varchar(45) NOT NULL,
   TotalSalary  int(11) DEFAULT '0',
  PRIMARY KEY ( employeeid , Month , Year )
)
CREATE TABLE  employeeinfo  (
   employeeid  varchar(45) NOT NULL,
   FirstName  varchar(45) DEFAULT NULL,
   LastName  varchar(45) DEFAULT NULL,
   CNIC  varchar(45) DEFAULT NULL,
   FatherOccupation  varchar(45) DEFAULT NULL,
   Email  varchar(45) DEFAULT NULL,
   CellNo  varchar(45) DEFAULT NULL,
   Telephone  varchar(45) DEFAULT NULL,
   DateofBirth  date DEFAULT NULL,
   BloodGroup  varchar(45) DEFAULT NULL,
   gender  varchar(45) DEFAULT NULL,
   Address  varchar(200) DEFAULT NULL,
   City  varchar(45) DEFAULT NULL,
   Province  varchar(45) DEFAULT NULL,
   MarriedStatus  varchar(45) DEFAULT NULL,
   Post  varchar(45) DEFAULT NULL,
   EmployeeStatus  varchar(45) DEFAULT NULL,
   JoiningDate  date DEFAULT NULL,
   LeavingDate  date DEFAULT NULL,
   Remarks  varchar(45) DEFAULT NULL,
   Image  longblob,
   AreaOfIntrest  varchar(45) DEFAULT NULL,
   LevelOfTeaching  varchar(45) DEFAULT NULL,
   DataStatus  varchar(45) DEFAULT NULL,
  PRIMARY KEY ( employeeid )
)
CREATE TABLE  employeepayment  (
   employeeid  int(11) NOT NULL,
   EmployeeName  varchar(45) DEFAULT NULL,
   Post  varchar(45) DEFAULT NULL,
   Salary  int(11) DEFAULT '0',
   PaidSalry  int(11) DEFAULT '0',
   Status  varchar(45) DEFAULT NULL,
   month  varchar(45) NOT NULL,
   year  varchar(45) NOT NULL,
   balance  int(11) DEFAULT '0',
   TotalSalary  int(11) DEFAULT '0',
  PRIMARY KEY ( employeeid , year , month )
)
CREATE TABLE  employeequalification  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   employeeid  varchar(45) DEFAULT NULL,
   MatricRollNo  varchar(45) DEFAULT NULL,
   MatricGrade  varchar(45) DEFAULT NULL,
   MatricSubject  varchar(45) DEFAULT NULL,
   MatricIns  varchar(45) DEFAULT NULL,
   Matricyear  date DEFAULT NULL,
   InterRollNo  varchar(45) DEFAULT NULL,
   InterGrade  varchar(45) DEFAULT NULL,
   InterSubject  varchar(45) DEFAULT NULL,
   InterIns  varchar(45) DEFAULT NULL,
   Interyear  date DEFAULT NULL,
   GraduationRollNo  varchar(45) DEFAULT NULL,
   GraduationGrade  varchar(45) DEFAULT NULL,
   GraduationSubject  varchar(45) DEFAULT NULL,
   GraduationIns  varchar(45) DEFAULT NULL,
   Graduationyear  date DEFAULT NULL,
   OtherRollNo  varchar(45) DEFAULT NULL,
   OtherGrade  varchar(45) DEFAULT NULL,
   OtherSubject  varchar(45) DEFAULT NULL,
   OtherIns  varchar(45) DEFAULT NULL,
   Otheryear  date DEFAULT NULL,
  PRIMARY KEY ( id )
)
CREATE TABLE  employeereferences  (
   id  int(11) NOT NULL AUTO_INCREMENT,
   RoName  varchar(45) DEFAULT NULL,
   RoRelationship  varchar(45) DEFAULT NULL,
   RoPhoneNo  varchar(45) DEFAULT NULL,
   RoOccupation  varchar(45) DEFAULT NULL,
   RoCnic  varchar(45) DEFAULT NULL,
   RtName  varchar(45) DEFAULT NULL,
   RtRelationship  varchar(45) DEFAULT NULL,
   RtPhoneNo  varchar(45) DEFAULT NULL,
   RtOccupation  varchar(45) DEFAULT NULL,
   RtCnic  varchar(45) DEFAULT NULL,
   employeeid  varchar(45) DEFAULT NULL,
  PRIMARY KEY ( id )
)
CREATE TABLE  employeesalary  (
   employeeid  varchar(45) NOT NULL,
   EmployeeName  varchar(45) DEFAULT NULL,
   WorkingHours  int(11) DEFAULT '0',
   salary  int(11) DEFAULT '0',
   Date  date DEFAULT NULL,
   post  varchar(45) DEFAULT NULL,
   Dateofjoing  varchar(45) DEFAULT NULL,
   count  int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY ( count , employeeid )
)
CREATE TABLE  feedetails  (
   feecode  int(11) DEFAULT NULL,
   StudentID  varchar(45) NOT NULL,
   StudentName  varchar(45) DEFAULT NULL,
   Month  varchar(45) NOT NULL,
   Year  varchar(45) NOT NULL,
   Classfee  int(11) DEFAULT '0',
   GeneratorFee  int(11) DEFAULT '0',
   FunctionFee  int(11) DEFAULT '0',
   ExamsFee  int(11) DEFAULT '0',
   BooksFee  int(11) DEFAULT '0',
   PartyFee  int(11) DEFAULT '0',
   SportsFee  int(11) DEFAULT '0',
   TripFee  int(11) DEFAULT '0',
   StationaryFee  int(11) DEFAULT '0',
   Others  int(11) DEFAULT '0',
   TotalDues  int(11) DEFAULT '0',
   Balance  int(11) DEFAULT '0',
   ClassName  varchar(45) DEFAULT NULL,
   Section  varchar(45) DEFAULT NULL,
   Paid  int(11) DEFAULT '0',
   AdmissionFee  int(11) DEFAULT '0',
   SecurityFee  int(11) DEFAULT '0',
   Lastmonthdues  int(11) DEFAULT '0',
  PRIMARY KEY ( StudentID , Month , Year )
)
CREATE TABLE  studentinfo  (
   studentid  varchar(45) NOT NULL,
   FirstName  varchar(45) DEFAULT NULL,
   LastName  varchar(45) DEFAULT NULL,
   Bform  varchar(45) DEFAULT NULL,
   Cnic  varchar(45) DEFAULT NULL,
   Occupation  varchar(45) DEFAULT NULL,
   Email  varchar(45) DEFAULT NULL,
   cellno  varchar(45) DEFAULT NULL,
   Telephone  varchar(45) DEFAULT NULL,
   Gender  varchar(45) DEFAULT NULL,
   DateofBirth  date DEFAULT NULL,
   BloodGroup  varchar(45) DEFAULT NULL,
   Address  varchar(45) DEFAULT NULL,
   City  varchar(45) DEFAULT NULL,
   Province  varchar(45) DEFAULT NULL,
   DateofAdmission  date DEFAULT NULL,
   Class  varchar(45) DEFAULT NULL,
   Section  varchar(45) DEFAULT NULL,
   Reference  varchar(45) DEFAULT NULL,
   Remarks  varchar(45) DEFAULT NULL,
   Status  varchar(45) DEFAULT NULL,
   Image  longblob,
   count  int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY ( count , studentid )
) 
CREATE TABLE  subjectinfo  (
   SubjectCode  varchar(45) NOT NULL,
   SubjectName  varchar(45) DEFAULT NULL,
   BookName  varchar(45) DEFAULT NULL,
   TeacherId  varchar(45) DEFAULT NULL,
   SubjectMarks  int(11) DEFAULT '0',
   count  int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY ( count , SubjectCode )
)