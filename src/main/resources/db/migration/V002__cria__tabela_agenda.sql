CREATE TABLE agenda (
	id serial PRIMARY KEY,
	descricao varchar(50),
	data_hora timestamp,
	paciente_id integer,
	CONSTRAINT fk_agenda_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id)
);