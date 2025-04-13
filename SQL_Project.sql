use project;
-- Create the Departments table
CREATE TABLE Departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(100)
);

-- Create the Students table
CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

-- Create the Courses table
CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

-- Create the Enrollments table
CREATE TABLE Enrollments (
    student_id INT,
    course_id INT,
    grade VARCHAR(2),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

-- Insert departments
INSERT INTO Departments VALUES (1, 'Computer Science'), (2, 'Mathematics'), (3, 'Physics');

-- Insert students
INSERT INTO Students VALUES 
(101, 'Alice', 20, 'Female', 1),
(102, 'Bob', 22, 'Male', 2),
(103, 'Charlie', 21, 'Male', 1),
(104, 'Diana', 23, 'Female', 3);

-- Insert courses
INSERT INTO Courses VALUES 
(201, 'Data Structures', 1),
(202, 'Algebra', 2),
(203, 'Quantum Mechanics', 3);

-- Insert enrollments
INSERT INTO Enrollments VALUES 
(101, 201, 'A'),
(102, 202, 'B'),
(103, 201, 'A'),
(104, 203, 'A');

-- 1. List all students with their department names
SELECT s.student_id, s.name, d.department_name
FROM Students s
JOIN Departments d ON s.department_id = d.department_id;

-- 2. List all courses with enrolled student names
SELECT c.course_name, s.name AS student_name
FROM Enrollments e
JOIN Students s ON e.student_id = s.student_id
JOIN Courses c ON e.course_id = c.course_id;

-- 3. Find students not enrolled in any course
SELECT s.name
FROM Students s
LEFT JOIN Enrollments e ON s.student_id = e.student_id
WHERE e.course_id IS NULL;

-- 4. Show average grade (alphabetical) per course (simplified example)
SELECT course_id, COUNT(*) AS total_enrolled
FROM Enrollments
GROUP BY course_id;

-- 5. Update a student’s grade
UPDATE Enrollments
SET grade = 'A+'
WHERE student_id = 102 AND course_id = 202;

-- 6. Delete a student
DELETE FROM Students WHERE student_id = 104;


