

INSERT INTO PERSONAL (codigo, nombres, apellidos, dni, telefono, correo_institucional, correo_personal, direccion, domicilio, fecha_nacimiento, lugar_nacimiento, estado_civil, sexo, foto_url, licencia_conducir, hobby)
VALUES ('EMP001','Angel Gabriel','Valdivia Arias','73037895','934494494','angel.valdiviaa@unmsm.edu.pe','angelariasus@gmail.com', 'Av. Universitaria 1234','Lima', TO_DATE('2025-09-01','YYYY-MM-DD'),'Lima','Soltero','Masculino','http://example.com/foto1.jpg','B','Futbol');

INSERT INTO PROYECTOS (codigo, nombre, descripcion, tipo, estado, prioridad, fecha_inicio, fecha_fin, presupuesto, progreso, cliente, ubicacion)
VALUES ('PROY001','Sistema de Gestión','Desarrollo de un sistema de gestión empresarial','Desarrollo','En Progreso','Alta', TO_DATE('2025-01-01','YYYY-MM-DD'), TO_DATE('2025-12-31','YYYY-MM-DD'), 50000, 50, 'Empresa XYZ', 'Lima');

INSERT INTO PROYECTOS (codigo, nombre, descripcion, tipo, estado, prioridad, fecha_inicio, fecha_fin, presupuesto, progreso, cliente, ubicacion)
VALUES ('PROY002','Sistema de Gestión','Desarrollo de un sistema de gestión empresarial','Desarrollo','En Progreso','Alta', TO_DATE('2025-01-01','YYYY-MM-DD'), TO_DATE('2025-12-31','YYYY-MM-DD'), 50000, 50, 'Empresa XYZ', 'Lima');

INSERT INTO PROYECTOS (codigo, nombre, descripcion, tipo, estado, prioridad, fecha_inicio, fecha_fin, presupuesto, progreso, cliente, ubicacion)
VALUES ('PROY003','Sistema de Gestión','Desarrollo de un sistema de gestión empresarial','Desarrollo','En Progreso','Alta', TO_DATE('2025-01-01','YYYY-MM-DD'), TO_DATE('2025-12-31','YYYY-MM-DD'), 50000, 50, 'Empresa XYZ', 'Lima');

INSERT INTO PERSONAL_PROYECTO (personal_id, proyecto_id, rol, area, fecha_asignacion, fecha_desasignacion, activo, horas_laborales_semana, salario_mensual, tipo_contrato, fecha_inicio_proyecto, fecha_fin_proyecto, contrato_proyecto_url, contrato_proyecto_nombre)
VALUES ((SELECT id FROM PERSONAL WHERE codigo='EMP001'), (SELECT id FROM PROYECTOS WHERE codigo='PROY001'), 'Desarrollador','Desarrollo', TO_DATE('2025-01-15','YYYY-MM-DD'), NULL, 1, 40, 3000, 'Tiempo Completo', TO_DATE('2025-01-15','YYYY-MM-DD'), TO_DATE('2025-12-31','YYYY-MM-DD'), 'http://example.com/contrato1.pdf', 'Contrato_EMP001_PROY001.pdf');

INSERT INTO PERSONAL_PROYECTO (personal_id, proyecto_id, rol, area, fecha_asignacion, fecha_desasignacion, activo, horas_laborales_semana, salario_mensual, tipo_contrato, fecha_inicio_proyecto, fecha_fin_proyecto, contrato_proyecto_url, contrato_proyecto_nombre)
VALUES ((SELECT id FROM PERSONAL WHERE codigo='EMP001'), (SELECT id FROM PROYECTOS WHERE codigo='PROY002'), 'Desarrollador','Desarrollo', TO_DATE('2025-01-15','YYYY-MM-DD'), NULL, 1, 40, 3000, 'Tiempo Completo', TO_DATE('2025-01-15','YYYY-MM-DD'), TO_DATE('2025-12-31','YYYY-MM-DD'), 'http://example.com/contrato1.pdf', 'Contrato_EMP001_PROY001.pdf');
