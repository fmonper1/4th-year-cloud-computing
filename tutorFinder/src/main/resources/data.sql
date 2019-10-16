INSERT INTO tutor (first_name, last_name, email_address, address, phone_number, bio) VALUES ('Rajeev', 'Galaiya', 'rgalaiya1@sheffield.ac.uk', '36 edward street', '07495878813', 'Hi, my name is Rajeev and I am a tutor based in sheffield specialising in several subjects. I am available throughout the week so please contact me to find out more and let me help improve your grades');
SET @tutor_id = LAST_INSERT_ID();
INSERT INTO subject (name) VALUES ('Maths');
SET @maths_id = LAST_INSERT_ID();
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @maths_id);
INSERT INTO subject (name) VALUES ('Physics');
SET @physics_id = LAST_INSERT_ID();
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @physics_id);
INSERT INTO subject (name) VALUES ('Chemistry');
SET @chemistry_id = LAST_INSERT_ID();
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @chemistry_id);

INSERT INTO tutor (first_name, last_name, email_address, address, phone_number, bio) VALUES ('Fernando', 'Montero','fmontero1@sheffield.ac.uk', '83 summer street', '0729749724', 'Hi, my name is Fernando and I am a tutor based in sheffield specialising in several subjects. I am available throughout the week so please contact me to find out more and let me help improve your grades');
SET @tutor_id = LAST_INSERT_ID();
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @chemistry_id);
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @physics_id);

INSERT INTO tutor (first_name, last_name, email_address, address, phone_number, bio) VALUES ('Daniel', 'Jones','djones1@sheffield.ac.uk', '79 west street', '0775928375', 'Hi, my name is Daniel and I am a tutor based in sheffield specialising in several subjects. I am available throughout the week so please contact me to find out more and let me help improve your grades');
SET @tutor_id = LAST_INSERT_ID();
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @physics_id);
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @maths_id);

INSERT INTO subject (name) VALUES ('Java');
SET @java_id = LAST_INSERT_ID();
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @java_id);

INSERT INTO tutor (first_name, last_name, email_address, address, phone_number, bio) VALUES ('Euan', 'Rochester', 'erochester1@sheffield.ac.uk', '4 allen street', '0725253523', 'Hi, my name is Euan and I am a tutor based in sheffield specialising in several subjects. I am available throughout the week so please contact me to find out more and let me help improve your grades');
SET @tutor_id = LAST_INSERT_ID();
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @java_id);
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @maths_id);
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @chemistry_id);
INSERT INTO tutor_subjects (tutor_id,subject_id) VALUES (@tutor_id, @physics_id);
