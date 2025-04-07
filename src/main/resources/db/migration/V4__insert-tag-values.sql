-- Insert tag values for Required Semesters
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '1 Semester' FROM tag_type WHERE name = 'Required Semesters';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '2 Semesters' FROM tag_type WHERE name = 'Required Semesters';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '3 Semesters' FROM tag_type WHERE name = 'Required Semesters';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '4 Semesters' FROM tag_type WHERE name = 'Required Semesters';

-- Insert tag values for Required Programs
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Business Intelligence' FROM tag_type WHERE name = 'Required Program';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Cloud Development and Operations' FROM tag_type WHERE name = 'Required Program';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Computer Programming' FROM tag_type WHERE name = 'Required Program';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Computer Engineering Technology' FROM tag_type WHERE name = 'Required Program';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Information Technology' FROM tag_type WHERE name = 'Required Program';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Interactive Media Design' FROM tag_type WHERE name = 'Required Program';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Mobile Application Design and Development' FROM tag_type WHERE name = 'Required Program';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, 'Web Development and Internet Applications' FROM tag_type WHERE name = 'Required Program';

-- Insert tag values for Student Count
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '2 Students' FROM tag_type WHERE name = 'Student Count';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '3 Students' FROM tag_type WHERE name = 'Student Count';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '4 Students' FROM tag_type WHERE name = 'Student Count';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '5 Students' FROM tag_type WHERE name = 'Student Count';
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '6 Students' FROM tag_type WHERE name = 'Student Count'; 
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '7 Students' FROM tag_type WHERE name = 'Student Count'; 
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '8 Students' FROM tag_type WHERE name = 'Student Count'; 
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '9 Students' FROM tag_type WHERE name = 'Student Count'; 
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '10 Students' FROM tag_type WHERE name = 'Student Count'; 
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '11 Students' FROM tag_type WHERE name = 'Student Count'; 
INSERT INTO tag_value (tag_type_id, value)
SELECT id, '12 Students' FROM tag_type WHERE name = 'Student Count'; 