--1.创建用户并授权
sqlplus / as sysdba
create user mapdb identified by mapdb;
grant dba to mapdb; 
quit

--2.连接数据库
sqlplus mapdb/mapdb

