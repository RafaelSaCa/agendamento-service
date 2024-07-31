alter table agenda
alter column descricao type integer using descricao::integer;

alter table agenda
add constraint fk_procedimento_id
foreign key (descricao) references procedimento(id);

alter table agenda
rename column descricao to procedimento_id;
