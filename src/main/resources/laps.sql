INSERT INTO laps.user_type
(type_id, type, leave_annual_total)
VALUES
(1,"admin",14),
(2,"staff",18),
(3,"manager",18);

INSERT INTO laps.employee
(employee_id, email, leave_annual_left, leave_medical_left, managed_by, name, ot_hours, password, username, user_type_id)
VALUES
(1, "first@gmail.com", 10, 5, "pupu", "bob", 20, "bob", "bob", 1)
(2, "second@gmail.com", 20, 23, "pupu", "pupu", 20, "pupu", "pupu", 3)
(3, "third@gmail.com", 20, 23, "pupu", "tony", 20, "tony", "tony", 2)

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
