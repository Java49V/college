delete from marks;
delete from subjects;
 delete from students_lecturers;
insert into students_lecturers (id, name, city, phone, birth_date, dtype)
values (123, 'Vasya', 'Rehovot', '054-1234567', '2000-10-10', 'Student'),
(124, 'Sara', 'Beersheva', '052-7654321', '1990-11-12','Student'),
(125, 'Yosef', 'Rehovot', '050-11223344', '2001-03-23','Student'),
(126, 'Rivka', 'Lod', '052-2311324', '2003-07-03','Student'),
(127, 'David', 'Beersheva', '050-7334455', '1997-05-20','Student'),
(128, 'Yakob', 'Rehovot', '051-6677889', '2000-10-10','Student'),
(1230, 'Abraham',  'Jerusalem','050-1111122', '1957-01-23', 'Lecturer'),
(1231, 'Mozes', 'Jerusalem','054-3334567', '1963-10-20', 'Lecturer'),
(1232, 'Sockratus', 'Rehovot', '057-7664821', '1960-03-15', 'Lecturer');
insert into subjects (id, name, hours, lecturer_id, type ) values(321, 'Java Core', 150, 1230, 'BACK_END'),
(322, 'Java Technologies', 75, 1230, 'BACK_END'),
(323, 'HTML/CSS', 60, 1231, 'FRONT_END'),
(324, 'JavaScript', 70, 1231, 'FRONT_END'),
(325, 'React', 65, 1232, 'FRONT_END');
insert into marks (stid, suid, score) values(123, 321, 75),
(123, 322, 60),
(123, 323, 95),
(123, 324, 85),
(123, 325, 100),
(124, 321, 70),
(124, 322, 65),
(124, 323, 90),
(124, 324, 95),
(125, 321, 80),
(125, 322, 75),
(126, 323, 100),
(126, 324, 90),
(127, 321, 100),
(127, 322, 95),
(127, 323, 90),
(127, 324, 100),

(127, 325, 95);