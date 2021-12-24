use laps;

INSERT INTO laps.user_type
(type_id, type, leave_annual_total)
VALUES
(1,"admin",14),
(2,"staff",18),
(3,"manager",18);

INSERT INTO laps.public_holiday
(public_holiday.date_from, public_holiday.name)
VALUES
("2021-01-01", "New Year's Day"),
("2021-02-12", "Chinese New Year"),
("2021-02-13", "Chinese New Year"),
("2021-04-02", "Good Friday"),
("2021-05-01", "Labour Day"),
("2021-05-13", "Hari Raya Puasa"),
("2021-05-26", "Vesak Day"),
("2021-07-20", "Hari Raya Haji"),
("2021-08-09", "National Day"),
("2021-11-04", "Deepavali"),
("2021-12-25", "Christmas Day");

INSERT INTO laps.leave_type
VALUES
(1, "for annual leave", "annual leave"),
(2, "for medical leave", "medical leave"),
(3, "for compensation leave", "compensation leave");

INSERT INTO laps.employee
VALUES
(1, "admin@gmail.com", 14, 60, "manager", "admin", 20, "admin", "admin", 1),
(2, "staff@gmail.com", 18, 60, "manager", "staff", 20, "staff", "staff", 2),
(3, "manager@gmail.com", 18, 60, "manager", "manager", 20, "manager", "manager", 3),
(4, "alice@gmail.com", 18, 60, "manager", "staff", 20, "alice", "alice", 2),
(5, "bob@gmail.com", 18, 60, "manager", "staff", 20, "bob", "bob", 2),
(6, "john@gmail.com", 18, 60, "manager", "staff", 20, "john", "john", 2);

INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (1,'2022-01-04','2022-01-06','FULL',NULL,'Handed over to Jim','Personal','APPLIED',1,1);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (2,'2022-02-03','2022-02-07','FULL',NULL,'Handed over to Jim','Medical check up','APPLIED',1,2);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (3,'2022-01-04','2022-01-04','AM',NULL,'','Personal','APPLIED',2,3);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (4,'2022-07-05','2022-07-14','FULL',NULL,'Contact at 91234444','Vacation','APPLIED',2,1);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (5,'2022-01-11','2022-01-17','FULL',NULL,'Handed over to James. Contact number is 81234444','Knee surgery','APPLIED',4,2);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (6,'2022-03-14','2022-03-14','FULL',NULL,'','Personal','APPLIED',4,3);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (7,'2022-01-31','2022-02-09','FULL',NULL,'Handed over to Jack. Contact me at 81230000','Revising for CFA exam','APPLIED',5,1);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (8,'2022-10-04','2022-10-10','FULL',NULL,'Handed over to Kevin. Contact me at 92463535','Going for lasik surgery','APPLIED',5,2);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (9,'2022-02-07','2022-02-08','FULL',NULL,'','Celebrate CNY','APPLIED',6,3);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (10,'2022-04-18','2022-04-21','FULL',NULL,'','Personal','APPLIED',6,1);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (11,'2022-01-20','2022-01-25','FULL',NULL,'','Personal','APPLIED',3,1);
INSERT INTO `leave_application` (`leave_id`,`date_from`,`date_to`,`leave_time`,`manager_comments`,`memo`,`reason`,`status`,`employee_employee_id`,`leavetype_leave_type_id`) VALUES (12,'2022-03-18','2022-03-25','FULL',NULL,'Handed over to Kevin','Going for retreat','APPLIED',3,1);

INSERT INTO `overtime` (`overtime_id`,`date_time`,`hours`,`status`,`employee_employee_id`) VALUES (1,'2021-12-16',2.5,'APPLIED',2);
INSERT INTO `overtime` (`overtime_id`,`date_time`,`hours`,`status`,`employee_employee_id`) VALUES (2,'2022-01-03',6,'APPLIED',4);
INSERT INTO `overtime` (`overtime_id`,`date_time`,`hours`,`status`,`employee_employee_id`) VALUES (3,'2021-12-16',8,'APPLIED',5);
INSERT INTO `overtime` (`overtime_id`,`date_time`,`hours`,`status`,`employee_employee_id`) VALUES (4,'2021-12-07',3.5,'APPLIED',6);
