-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜抓取日志', '2000', '1', '/js/log', 'C', '0', 'js:log:view', '#', 'admin', sysdate(), '', null, '上榜抓取日志菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜抓取日志查询', @parentId, '1',  '#',  'F', '0', 'js:log:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜抓取日志新增', @parentId, '2',  '#',  'F', '0', 'js:log:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜抓取日志修改', @parentId, '3',  '#',  'F', '0', 'js:log:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜抓取日志删除', @parentId, '4',  '#',  'F', '0', 'js:log:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜抓取日志导出', @parentId, '5',  '#',  'F', '0', 'js:log:export',       '#', 'admin', sysdate(), '', null, '');
