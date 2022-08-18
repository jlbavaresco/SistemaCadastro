create table cidades (
codigo serial not null, 
nome varchar(50) not null, 
uf char(2) not null, 
primary key (codigo));

create table pessoas (
codigo serial not null, 
nome varchar(50) not null, 
salario decimal(10,2) not null, 
nascimento date not null, 
cidade integer not null, 
primary key(cidade), 
foreign key (cidade) references cidade(codigo));

insert into cidades (nome, uf) values 
                    ('Porto Alegre','RS') , 
                    ('Florianopolis','SC');

insert into pessoas (nome, salario, nascimento, cidade) values
    ('Joao',2500.89,'24/07/1983',1),
    ('Sergio',2750.37,'30/09/1990',2);
