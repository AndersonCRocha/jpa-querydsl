CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SEQUENCE sq_students;
CREATE TABLE students (
	id INTEGER PRIMARY KEY, 
	name VARCHAR(255) NOT NULL, 
	email VARCHAR(255) NOT NULL, 
	birth TIMESTAMP NOT NULL
);

CREATE SEQUENCE sq_courses;
CREATE TABLE courses (
	id INTEGER PRIMARY KEY, 
	title VARCHAR(255) NOT NULL, 
	description VARCHAR(255) NOT NULL, 
	student_limit INTEGER NOT NULL
);

CREATE SEQUENCE sq_enrollments;
CREATE TABLE enrollments (
	code VARCHAR(40) PRIMARY KEY, 
	course_id INTEGER NOT NULL,
	student_id INTEGER NOT NULL,
	active BOOLEAN NOT NULL,
	created_at TIMESTAMP NOT NULL,
	CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id),
	CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses(id)
);

INSERT INTO students (id, name, email, birth) VALUES (NEXTVAL('sq_students'), 'João', 'joao@email.com', '2002-05-11T22:00:21.451+00:00');
INSERT INTO students (id, name, email, birth) VALUES (NEXTVAL('sq_students'), 'Maria', 'maria@email.com', '2005-09-21T22:00:21.451+00:00');
INSERT INTO students (id, name, email, birth) VALUES (NEXTVAL('sq_students'), 'Pedro', 'pedro@email.com', '1992-04-22T22:00:21.451+00:00');
INSERT INTO students (id, name, email, birth) VALUES (NEXTVAL('sq_students'), 'Lucas', 'lucas@email.com', '2010-01-01T22:00:21.451+00:00');
INSERT INTO students (id, name, email, birth) VALUES (NEXTVAL('sq_students'), 'Carlos', 'carlos@email.com', '1985-11-30T22:00:21.451+00:00');

INSERT INTO courses (id, title, description, student_limit) 
			VALUES (NEXTVAL('sq_courses'), 'Curso 1', 'Descrição do curso 1', 15);
INSERT INTO courses (id, title, description, student_limit) 
			VALUES (NEXTVAL('sq_courses'), 'Curso 2', 'Descrição do curso 2', 20);
INSERT INTO courses (id, title, description, student_limit) 
			VALUES (NEXTVAL('sq_courses'), 'Curso 3', 'Descrição do curso 3', 25);

INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 1, 1, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 1, 2, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 1, 3, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 1, 4, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 2, 3, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 2, 4, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 2, 5, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 3, 1, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 3, 2, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 3, 3, TRUE, CURRENT_TIMESTAMP);
INSERT INTO enrollments (code, course_id, student_id, active, created_at) VALUES (UUID_GENERATE_V1(), 3, 4, TRUE, CURRENT_TIMESTAMP);
