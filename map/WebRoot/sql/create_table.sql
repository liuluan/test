--�ߵµ�ͼ���ƶ���α�
create table polygon(
       id               number(6)           not null primary key,   --����������
       pointsCount      number(6)           not null,               --������
       arrPoints        varchar2(800)      not null,               --�ڵ���Ϣ
       type             varchar2(50)        not null               --��ǵ�����             
);
--�������������
create sequence pol_id;

--�ߵµ�ͼ����Բ�α�
create table circle(
       id               number(6)           not null primary key,   --����������
       centerPoint      varchar2(800)           not null,             --Բ��
       radius           varchar2(800)      not null,                  --�뾶
       type             varchar2(50)        not null                  --��ǵ�����             
);
--�������������
create sequence cir_id;