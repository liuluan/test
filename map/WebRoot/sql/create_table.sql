--高德地图绘制多边形表
create table polygon(
       id               number(6)           not null primary key,   --主键自增长
       pointsCount      number(6)           not null,               --几个边
       arrPoints        varchar2(800)      not null,               --节点信息
       type             varchar2(50)        not null               --标记的类型             
);
--创建多边形序列
create sequence pol_id;

--高德地图绘制圆形表
create table circle(
       id               number(6)           not null primary key,   --主键自增长
       centerPoint      varchar2(800)           not null,             --圆心
       radius           varchar2(800)      not null,                  --半径
       type             varchar2(50)        not null                  --标记的类型             
);
--创建多边形序列
create sequence cir_id;