create table customers
(
    custom_id bigserial not null
        constraint customer_pk
            primary key,
    custom_name varchar(100) not null,
    custom_address varchar(100) not null,
    custom_mail varchar(20) not null,
    custom_phone varchar(100) not null,
    custom_skype varchar(20),
    custom_telegramm varchar(20),
    custom_create_date timestamp default CURRENT_TIMESTAMP not null,
    custom_update_date timestamp default CURRENT_TIMESTAMP not null,
    customer_lastname varchar(100) not null
);

alter table customers owner to postgres;

create unique index customer_custom_id_uindex
    on customers (custom_id);

create table roles
(
    role_id bigserial not null
        constraint roles_pk
            primary key,
    role_name varchar(20) not null
);

alter table roles owner to postgres;

create unique index roles_role_id_uindex
    on roles (role_id);

create unique index roles_role_name_uindex
    on roles (role_name);

create table positions
(
    position_id bigserial not null
        constraint positions_pk
            primary key,
    position_title varchar(100) not null,
    position_create_date timestamp default CURRENT_TIMESTAMP not null,
    position_update_date timestamp default CURRENT_TIMESTAMP not null
);

alter table positions owner to postgres;

create unique index positions_position_id_uindex
    on positions (position_id);

create table employees
(
    empl_id bigserial not null
        constraint employee_pk
            primary key,
    empl_name varchar(100) not null,
    empl_lastname varchar(100) not null,
    empl_password varchar(200) not null,
    empl_mail varchar(100) not null,
    empl_position bigint not null
        constraint employees_positions_position_id_fk
            references positions
            on update cascade,
    empl_phone varchar(100) not null,
    empl_skype varchar(100),
    empl_telegramm varchar(100),
    empl_role bigint not null
        constraint employees_roles_role_id_fk
            references roles
            on update cascade,
    empl_fired boolean default false not null,
    empl_create_date timestamp default CURRENT_TIMESTAMP not null,
    empl_update_date timestamp default CURRENT_TIMESTAMP not null
);

alter table employees owner to postgres;

create unique index employee_empl_id_uindex
    on employees (empl_id);

create unique index employee_empl_mail_uindex
    on employees (empl_mail);

create unique index employee_empl_password_uindex
    on employees (empl_password);

create table messages
(
    message_id bigserial not null
        constraint messages_pk
            primary key,
    message_sender bigint not null
        constraint messages_employees_empl_id_fk
            references employees
            on update cascade on delete cascade,
    message_content varchar(500) not null,
    message_create_date timestamp default CURRENT_TIMESTAMP not null,
    message_update_date timestamp default CURRENT_TIMESTAMP not null
);

alter table messages owner to postgres;

create unique index messages_message_id_uindex
    on messages (message_id);

create table attachments
(
    file_id bigserial not null
        constraint files_pk
            primary key,
    file_url varchar(50) not null,
    file_create_date timestamp default CURRENT_TIMESTAMP not null,
    file_update_date timestamp default CURRENT_TIMESTAMP not null
);

alter table attachments owner to postgres;

create unique index files_file_id_uindex
    on attachments (file_id);

create table tasks
(
    task_id bigserial not null
        constraint tasks_pk
            primary key,
    task_name varchar(20) not null,
    task_deskr varchar(200),
    task_create_date timestamp default CURRENT_TIMESTAMP not null,
    task_update_date timestamp default CURRENT_TIMESTAMP not null,
    task_temporation_plan_date timestamp not null,
    task_temporation_fact_date timestamp,
    task_closed boolean default false not null
);

alter table tasks owner to postgres;

create unique index tasks_task_id_uindex
    on tasks (task_id);

create table projects
(
    proj_id bigserial not null
        constraint project_pk
            primary key,
    proj_name varchar(100) not null,
    proj_customer bigint not null
        constraint projects_customers_custom_id_fk
            references customers
            on update cascade,
    proj_director bigint not null
        constraint projects_employees_empl_id_fk
            references employees
            on update cascade,
    proj_descr varchar(200),
    proj_create_date timestamp default CURRENT_TIMESTAMP not null,
    proj_update_date timestamp default CURRENT_TIMESTAMP not null,
    proj_termonation_plan_date timestamp not null,
    proj_termonation_fact_date timestamp,
    project_closed boolean default false not null
);

alter table projects owner to postgres;

create unique index project_proj_id_uindex
    on projects (proj_id);

create table task_employees_mapping
(
    task_employees_task_id bigint not null
        constraint task_employees_tasks_task_id_fk
            references tasks
            on update cascade on delete cascade,
    task_employees_executor_id bigint not null
        constraint task_employees_employees_empl_id_fk
            references employees
            on update cascade on delete cascade
);

alter table task_employees_mapping owner to postgres;

create table project_tasks_mapping
(
    project_tasks_project_id bigint not null
        constraint project_tasks_projects_proj_id_fk
            references projects
            on update cascade on delete cascade,
    project_tasks_task_id bigint not null
        constraint project_tasks_tasks_task_id_fk
            references tasks
            on update cascade on delete cascade
);

alter table project_tasks_mapping owner to postgres;

create unique index project_tasks_mapping_project_tasks_taskid_uindex
    on project_tasks_mapping (project_tasks_task_id);

create table tasks_mapping
(
    tasks_mapping_task_id bigserial not null
        constraint tasks_mapping_tasks_task_id_fk
            references tasks
            on update cascade on delete cascade,
    tasks_mapping_maintask_id bigserial not null
        constraint tasks_mapping_tasks_task_id_fk_2
            references tasks
            on update cascade on delete cascade
);

alter table tasks_mapping owner to postgres;

create unique index tasks_mapping_tasks_mapping_tascid_uindex
    on tasks_mapping (tasks_mapping_task_id);

create table att_project_mapping
(
    att_proj_mapping_project_id bigserial not null
        constraint attachment_project_mapping_projects_proj_id_fk
            references projects
            on update cascade on delete cascade,
    att_proj_mapping_att_id bigserial not null
        constraint attachment_project_mapping_attachments_file_id_fk
            references attachments
            on update cascade on delete cascade
);

alter table att_project_mapping owner to postgres;

create unique index attachment_project_mapping_att_proj_mapping_attid_uindex
    on att_project_mapping (att_proj_mapping_att_id);

create table att_task_mapping
(
    att_task_att_id bigserial not null
        constraint att_task_mapping_attachments_file_id_fk
            references attachments
            on update cascade on delete cascade,
    att_task_task_id bigserial not null
        constraint att_task_mapping_tasks_task_id_fk
            references tasks
            on update cascade on delete cascade
);

alter table att_task_mapping owner to postgres;

create unique index att_task_mapping_att_task_attid_uindex
    on att_task_mapping (att_task_att_id);

create table message_proj_mapping
(
    mess_proj_proj_id bigserial not null
        constraint message_proj_mapping_projects_proj_id_fk
            references projects
            on update cascade on delete cascade,
    mess_proj_message_id bigserial not null
        constraint message_proj_mapping_messages_message_id_fk
            references messages
            on update cascade on delete cascade
);

alter table message_proj_mapping owner to postgres;

create unique index message_proj_mapping_mess_proj_messageid_uindex
    on message_proj_mapping (mess_proj_message_id);

create table message_task_mapping
(
    mess_task_mess_id bigserial not null
        constraint message_task_mapping_messages_message_id_fk
            references messages
            on update cascade on delete cascade,
    mess_task_task_id bigserial not null
        constraint message_task_mapping_tasks_task_id_fk
            references tasks
            on update cascade on delete cascade
);

alter table message_task_mapping owner to postgres;

create unique index message_task_mapping_mess_task_messid_uindex
    on message_task_mapping (mess_task_mess_id);

create table invitations
(
    token varchar(600) not null
        constraint invitations_pk
            primary key
);

alter table invitations owner to postgres;

create unique index invitations_token_uindex
    on invitations (token);

