drop database if exists dcgmsn;
create database dcgmsn charset utf8;

grant all on dcgmsn.* to 'read'@'localhost' identified by 'read';

use dcgmsn;

