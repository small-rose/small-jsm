-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜数据', '2000', '1', '/js/rank', 'C', '0', 'js:rank:view', '#', 'admin', sysdate(), '', null, '上榜数据菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜数据查询', @parentId, '1',  '#',  'F', '0', 'js:rank:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜数据新增', @parentId, '2',  '#',  'F', '0', 'js:rank:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜数据修改', @parentId, '3',  '#',  'F', '0', 'js:rank:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜数据删除', @parentId, '4',  '#',  'F', '0', 'js:rank:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('上榜数据导出', @parentId, '5',  '#',  'F', '0', 'js:rank:export',       '#', 'admin', sysdate(), '', null, '');
