set search_path to estate;

insert into role(code,name) values('MANAGER','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into users(username,password,fullname,status)
values('manager','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','manager',1);
insert into users(username,password,fullname,status)
values('user','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','user',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);

insert into configuration(type, name, key, value)
values('email_template', 'Template tạo tài khoản người dùng', 'template.user.create', '<p>Xin chào: ${username}</p><p>Mật khẩu của bạn là: ${password}</p><p>Chú ý đăng nhập để thay đổi mật khẩu</p>');
insert into configuration(type, name, key, value)
values('email_configuration', 'Cấu hình host mail server', 'mail.host', 'smtp.gmail.com');
insert into configuration(type, name, key, value)
values('email_configuration', 'Cấu hình port mail server', 'mail.port', 587);
insert into configuration(type, name, key, value)
values('email_configuration', 'Username Mail server', 'mail.username', 'lamjavaweb123@gmail.com');
insert into configuration(type, name, key, value)
values('email_configuration', 'Password mail server', 'mail.password', 'tunglamdoantrang');






