-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('简书用户', '2000', '1', '/js/userInfo', 'C', '0', 'js:userInfo:view', '#', 'admin', sysdate(), '', null, '简书用户菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('简书用户查询', @parentId, '1',  '#',  'F', '0', 'js:userInfo:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('简书用户新增', @parentId, '2',  '#',  'F', '0', 'js:userInfo:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('简书用户修改', @parentId, '3',  '#',  'F', '0', 'js:userInfo:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('简书用户删除', @parentId, '4',  '#',  'F', '0', 'js:userInfo:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('简书用户导出', @parentId, '5',  '#',  'F', '0', 'js:userInfo:export',       '#', 'admin', sysdate(), '', null, '');
