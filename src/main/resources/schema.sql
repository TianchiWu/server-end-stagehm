drop table if exists t_user;
drop table if exists t_login_log;

create table t_user(
    user_id identity,
    user_password varchar(30),
    password varchar(32),
    last_visit datetime,
    last_ip varchar(23)
)

create table t_login_log (
    login_log_id identity,
    user_id int,
    ip varchar(23),
    login_datetime datetime
)

insert into t_user(user_name, password) values('admin','123456');
commit;