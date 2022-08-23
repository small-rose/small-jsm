-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题数据', '2000', '1', '/js/subjectDataInfo', 'C', '0', 'js:subjectDataInfo:view', '#', 'admin', sysdate(), '', null, '专题数据菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题数据查询', @parentId, '1',  '#',  'F', '0', 'js:subjectDataInfo:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题数据新增', @parentId, '2',  '#',  'F', '0', 'js:subjectDataInfo:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题数据修改', @parentId, '3',  '#',  'F', '0', 'js:subjectDataInfo:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题数据删除', @parentId, '4',  '#',  'F', '0', 'js:subjectDataInfo:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('专题数据导出', @parentId, '5',  '#',  'F', '0', 'js:subjectDataInfo:export',       '#', 'admin', sysdate(), '', null, '');
