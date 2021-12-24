INSERT INTO laps.user_type
(type_id, type, leave_annual_total)
VALUES
(1,"admin",14),
(2,"staff",18),
(3,"manager",18);

INSERT INTO laps.employee
VALUES
(1, "first@gmail.com", 10, 5, "pupu", "bob", 20, "bob", "bob", 1),
(2, "second@gmail.com", 20, 23, null, "pupu", 20, "pupu", "pupu", 3),
(3, "third@gmail.com", 20, 23, "pupu", "tony", 20, "tony", "tony", 2);

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

INSERT INTO laps.leave_application
(leave_id, date_from, date_to, memo, reason, status, employee_employee_id, leavetype_leave_type_id)
VALUES
(1, '2021-12-10', '2021-12-22', "nil", "Personal", "APPLIED", 1, 1 ),
(2, '2021-02-22', '2021-04-05', "nil", "Vacation", "APPROVED", 1, 1 ),
(3, '2021-07-21', '2021-08-22', "nil", "Overseas", "REJECTED", 3, 1 ),
(4, '2021-08-22', '2021-08-25', "nil", "Personal", "APPLIED", 3, 1 ),
(5, '2021-12-18', '2021-12-22', "nil", "Personal", "APPLIED", 3, 1 ),
(6, '2021-10-22', '2021-10-26', "nil", "Vacation", "APPLIED", 2, 1 );

INSERT INTO `leave_application` (`leave_id`, `date_from`, `date_to`, `manager_comments`, `memo`, `reason`, `status`, `employee_employee_id`, `leavetype_leave_type_id`) VALUES
(1, '2021-12-02', '1998-12-04', '', 'test memo 1', 'test reason 1', 'APPLIED', 3, 1),
(2, '2021-12-05', '1998-12-05', '', 'ttest memo 2', 'test reason 2', 'APPROVED', 3, 1),
(3, '2021-12-12', '2021-12-19', NULL, 'test memo 3', 'test reason 3', 'APPLIED', 3, 1),
(4, '2021-12-25', '2021-12-31', '', 'test memo 4', 'test reason 4', 'REJECTED', 3, 1);

