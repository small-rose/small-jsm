-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题信息', '2000', '1', '/js/subjectInfo', 'C', '0', 'js:subjectInfo:view', '#', 'admin', sysdate(), '', null, '专题信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题信息查询', @parentId, '1',  '#',  'F', '0', 'js:subjectInfo:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题信息新增', @parentId, '2',  '#',  'F', '0', 'js:subjectInfo:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题信息修改', @parentId, '3',  '#',  'F', '0', 'js:subjectInfo:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题信息删除', @parentId, '4',  '#',  'F', '0', 'js:subjectInfo:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题信息导出', @parentId, '5',  '#',  'F', '0', 'js:subjectInfo:export',       '#', 'admin', sysdate(), '', null, '');
