INSERT INTO public.roles (role_id, role_name) VALUES (1, 'ROLE_SUPER');
INSERT INTO public.roles (role_id, role_name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO public.roles (role_id, role_name) VALUES (3, 'ROLE_USER');
INSERT INTO public.roles (role_id, role_name) VALUES (4, 'ROLE_NOPERMISSION');

INSERT INTO public.positions (position_id, position_title, position_create_date, position_update_date) VALUES (1, 'Superuser', '2020-12-06 11:13:39.817095', '2020-12-06 11:13:39.817095');

INSERT INTO public.employees (empl_id, empl_name, empl_lastname, empl_password, empl_mail, empl_position, empl_phone, empl_skype, empl_telegramm, empl_role, empl_fired, empl_create_date, empl_update_date) VALUES (1, 'super', 'super', 'super', 'super@gmail.com', 1, '+111', null, null, 1, false, '2020-12-24 13:18:42.059217', '2020-12-24 13:18:42.059217');