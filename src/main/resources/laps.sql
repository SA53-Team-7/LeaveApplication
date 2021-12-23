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
(leave_type_id, description, type)
VALUES
(1,"An employee has to take full day leave","Annual Leave"),
(2,"An employee has to take full day leave","Medical Leave"),
(3,"For compensation leave the granularity is half a day. Every four hours of overtime work makes an employee eligible for half-a day compensation.","Compensation Leave");

INSERT INTO laps.leave_application
(leave_id, date_from, date_to, memo, reason, status, employee_employeeid, leavetype_leave_type_id)
VALUES
(1, '2021-12-10', '2021-12-22', "nil", "Personal", "APPLIED", 1, 1 ),
(2, '2021-02-22', '2021-04-05', "nil", "Vacation", "APPROVED", 1, 1 ),
(3, '2021-07-21', '2021-08-22', "nil", "Overseas", "REJECTED", 3, 1 ),
(4, '2021-08-22', '2021-08-25', "nil", "Personal", "APPLIED", 3, 1 ),
(5, '2021-12-18', '2021-12-22', "nil", "Personal", "APPLIED", 3, 1 ),
(6, '2021-10-22', '2021-10-26', "nil", "Vacation", "APPLIED", 2, 1 );

