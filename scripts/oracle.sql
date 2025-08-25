-- ============================================================================
-- ELIMINACIÓN DE TABLAS EXISTENTES
-- ============================================================================

-- Eliminar tablas existentes si existen (en orden correcto para evitar errores de FK)
BEGIN
    -- Ignorar errores si la tabla no existe
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PERSONAL_TAREAS CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PERSONAL_PROYECTO CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PAGOS_PROVEEDOR CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PAGOS_PERSONAL CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE NOTIFICACIONES CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PROYECTO_AREAS CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PROVEEDOR_SERVICIOS CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PROVEEDOR_PROYECTO CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PROVEEDOR_DOCUMENTOS CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PROVEEDOR_ACTIVIDADES CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PERSONAL CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE DATOS_LABORALES CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE ESPECIALIDADES CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE GRADOS_ACADEMICOS CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE EXPERIENCIA_LABORAL CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE EVALUACIONES_DESEMPENO CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PROVEEDORES CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
    BEGIN EXECUTE IMMEDIATE 'DROP TABLE PROYECTOS CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
END;
/

-- ============================================================================
-- CREACIÓN DE TABLAS
-- ============================================================================

CREATE TABLE PROYECTOS (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    codigo VARCHAR2(20 CHAR) UNIQUE NOT NULL,
    nombre VARCHAR2(200 CHAR) NOT NULL,
    descripcion CLOB,
    tipo VARCHAR2(100 CHAR) NOT NULL,
    estado VARCHAR2(50 CHAR) DEFAULT 'Planificación',
    prioridad VARCHAR2(20 CHAR) DEFAULT 'Media',
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    presupuesto NUMBER(12,2),
    progreso NUMBER(3) DEFAULT 0,
    cliente VARCHAR2(200 CHAR),
    ubicacion VARCHAR2(200 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE PROVEEDORES (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    codigo VARCHAR2(20 CHAR) UNIQUE NOT NULL,
    razon_social VARCHAR2(200 CHAR) NOT NULL,
    nombre_comercial VARCHAR2(200 CHAR),
    ruc VARCHAR2(11 CHAR) UNIQUE NOT NULL,
    direccion CLOB,
    telefono VARCHAR2(15 CHAR),
    rubro VARCHAR2(100 CHAR) NOT NULL,
    tipo VARCHAR2(50 CHAR) NOT NULL,
    estado VARCHAR2(20 CHAR) DEFAULT 'Activo',
    descripcion CLOB,
    fecha_registro DATE,
    contacto_nombre VARCHAR2(100 CHAR),
    contacto_cargo VARCHAR2(100 CHAR),
    contacto_telefono VARCHAR2(15 CHAR),
    contacto_email VARCHAR2(100 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE PERSONAL (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    codigo VARCHAR2(20 CHAR) UNIQUE NOT NULL,
    nombres VARCHAR2(100 CHAR) NOT NULL,
    apellidos VARCHAR2(100 CHAR) NOT NULL,
    dni VARCHAR2(8 CHAR) UNIQUE NOT NULL,
    telefono VARCHAR2(15 CHAR),
    correo_institucional VARCHAR2(100 CHAR) UNIQUE,
    correo_personal VARCHAR2(100 CHAR),
    direccion CLOB,
    domicilio CLOB,
    fecha_nacimiento DATE,
    lugar_nacimiento VARCHAR2(100 CHAR),
    estado_civil VARCHAR2(20 CHAR),
    sexo VARCHAR2(10 CHAR),
    foto_url CLOB,
    licencia_conducir VARCHAR2(20 CHAR),
    hobby CLOB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE AUDITORIA (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    fecha_hora TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    usuario VARCHAR2(255 CHAR) DEFAULT 'Sistema' NOT NULL,
    tabla VARCHAR2(255 CHAR) NOT NULL,
    registro_id VARCHAR2(255 CHAR) NOT NULL,
    campo VARCHAR2(255 CHAR) NOT NULL,
    valor_anterior CLOB,
    valor_nuevo CLOB,
    operacion VARCHAR2(20 CHAR) NOT NULL, -- INSERT, UPDATE, DELETE
    ip_address VARCHAR2(100 CHAR),
    user_agent VARCHAR2(500 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE DATOS_LABORALES (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    personal_id RAW(16) NOT NULL,
    area VARCHAR2(100 CHAR),
    cargo VARCHAR2(100 CHAR),
    fecha_inicio DATE,
    fecha_fin DATE,
    tipo_contrato VARCHAR2(50 CHAR),
    horas_laborales NUMBER(10,0),
    salario NUMBER(10,2),
    contrato_url CLOB,
    contrato_nombre VARCHAR2(255 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_datoslaborales_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE
);

CREATE TABLE EVALUACIONES_DESEMPENO (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    personal_id RAW(16) NOT NULL,
    proyecto_id RAW(16) NOT NULL,
    calidad_trabajo NUMBER(3,0),
    habilidades_comunicacion NUMBER(3,0),
    personalidad NUMBER(3,0),
    conocimiento_trabajo NUMBER(3,0),
    confiabilidad NUMBER(3,0),
    productividad NUMBER(3,0),
    cumplimiento_metas NUMBER(3,0),
    contribucion_equipo NUMBER(3,0),
    puntualidad NUMBER(3,0),
    comentarios CLOB,
    fecha_evaluacion DATE DEFAULT SYSDATE,
    evaluador VARCHAR2(255 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_eval_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_eval_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE,
    CONSTRAINT uq_eval_personal_proyecto
        UNIQUE (personal_id, proyecto_id)
);

CREATE TABLE EXPERIENCIA_LABORAL (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    personal_id RAW(16) NOT NULL,
    institucion VARCHAR2(200 CHAR) NOT NULL,
    area VARCHAR2(100 CHAR) NOT NULL,
    especialidad VARCHAR2(200 CHAR) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    anos_experiencia NUMBER(3,0),
    certificado_url CLOB,
    certificado_nombre VARCHAR2(255 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_exp_laboral_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE
);

CREATE TABLE GRADOS_ACADEMICOS (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    personal_id RAW(16) NOT NULL,
    carrera VARCHAR2(200 CHAR) NOT NULL,
    universidad VARCHAR2(200 CHAR) NOT NULL,
    fecha_graduacion DATE,
    certificado_url CLOB,
    certificado_nombre VARCHAR2(255 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_gradoacad_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE
);

CREATE TABLE NOTIFICACIONES (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    tipo VARCHAR2(50 CHAR) NOT NULL,
    titulo VARCHAR2(255 CHAR) NOT NULL,
    descripcion CLOB,
    fecha_evento TIMESTAMP WITH TIME ZONE NOT NULL,
    fecha_creacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    leida NUMBER(1) DEFAULT 0 NOT NULL, -- Booleano: 0 = false, 1 = true
    prioridad VARCHAR2(20 CHAR) DEFAULT 'media' NOT NULL,
    personal_id RAW(16),
    proveedor_id RAW(16),
    proyecto_id RAW(16),
    datos_adicionales CLOB, -- Oracle no tiene JSONB nativo, usamos CLOB
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_notif_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_notif_proveedor
        FOREIGN KEY (proveedor_id)
        REFERENCES PROVEEDORES(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_notif_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE
);

CREATE TABLE PAGOS_PERSONAL (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    personal_id RAW(16) NOT NULL,
    proyecto_id RAW(16),
    fecha_hora TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    numero_operacion VARCHAR2(100 CHAR) NOT NULL,
    cantidad NUMBER(10,2) NOT NULL,
    boleta_url CLOB,
    boleta_nombre VARCHAR2(255 CHAR),
    boleta_pdf_url CLOB,
    boleta_pdf_nombre CLOB,
    concepto VARCHAR2(200 CHAR),
    metodo_pago VARCHAR2(50 CHAR),
    observaciones CLOB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_pagos_personal_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_pagos_personal_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE
);

CREATE TABLE PAGOS_PROVEEDOR (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    proveedor_id RAW(16) NOT NULL,
    proyecto_id RAW(16),
    fecha_hora TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    numero_operacion VARCHAR2(100 CHAR) NOT NULL,
    cantidad NUMBER(10,2) NOT NULL,
    boleta_url CLOB,
    boleta_nombre VARCHAR2(255 CHAR),
    boleta_pdf_url CLOB,
    boleta_pdf_nombre CLOB,
    concepto VARCHAR2(200 CHAR),
    metodo_pago VARCHAR2(50 CHAR),
    observaciones CLOB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_pagos_proveedor_proveedor
        FOREIGN KEY (proveedor_id)
        REFERENCES PROVEEDORES(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_pagos_proveedor_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE
);

CREATE TABLE PERSONAL_PROYECTO (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    personal_id RAW(16) NOT NULL,
    proyecto_id RAW(16) NOT NULL,
    rol VARCHAR2(100 CHAR) NOT NULL,
    area VARCHAR2(100 CHAR),
    fecha_asignacion DATE DEFAULT SYSDATE,
    fecha_desasignacion DATE,
    activo NUMBER(1) DEFAULT 1 NOT NULL,
    horas_laborales_semana NUMBER DEFAULT 40,
    salario_mensual NUMBER(10,2),
    tipo_contrato VARCHAR2(50 CHAR),
    fecha_inicio_proyecto DATE,
    fecha_fin_proyecto DATE,
    contrato_proyecto_url CLOB,
    contrato_proyecto_nombre VARCHAR2(255 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT uq_personal_proyecto UNIQUE (personal_id, proyecto_id),
    CONSTRAINT fk_personal_proyecto_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_personal_proyecto_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE
);

CREATE TABLE PERSONAL_TAREAS (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    personal_id RAW(16) NOT NULL,
    proyecto_id RAW(16) NOT NULL,
    tarea VARCHAR2(200 CHAR) NOT NULL,
    descripcion CLOB,
    fecha_asignacion DATE DEFAULT SYSDATE,
    fecha_entrega DATE,
    fecha_finalizacion DATE,
    estado VARCHAR2(50 CHAR) DEFAULT 'Pendiente',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_personal_tareas_personal
        FOREIGN KEY (personal_id)
        REFERENCES PERSONAL(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_personal_tareas_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE
);

CREATE TABLE PROVEEDOR_ACTIVIDADES (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    proveedor_proyecto_id RAW(16) NOT NULL,
    descripcion CLOB NOT NULL,
    fecha_ejecucion DATE NOT NULL,
    estado VARCHAR2(50 CHAR) DEFAULT 'En proceso' NOT NULL,
    observaciones CLOB,
    documento_pdf_url VARCHAR2(255 CHAR),
    documento_pdf_nombre VARCHAR2(255 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_proveedor_actividades_proveedor_proyecto
        FOREIGN KEY (proveedor_proyecto_id)
        REFERENCES PROVEEDOR_PROYECTO(id)
        ON DELETE CASCADE
);

CREATE TABLE PROVEEDOR_DOCUMENTOS (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    proveedor_id RAW(16) NOT NULL,
    nombre VARCHAR2(200 CHAR) NOT NULL,
    tipo VARCHAR2(100 CHAR),
    fecha_vencimiento DATE,
    archivo_url CLOB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_proveedor_documentos_proveedor
        FOREIGN KEY (proveedor_id)
        REFERENCES PROVEEDORES(id)
        ON DELETE CASCADE
);

CREATE TABLE PROVEEDOR_PROYECTO (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    proveedor_id RAW(16) NOT NULL,
    proyecto_id RAW(16) NOT NULL,
    servicio VARCHAR2(200 CHAR),
    servicio_descripcion CLOB,
    fecha_inicio DATE,
    fecha_fin DATE,
    monto NUMBER(10,2),
    monto_total_contratado NUMBER(12,2),
    fecha_contrato DATE,
    estado VARCHAR2(50 CHAR) DEFAULT 'Activo',
    contrato_pdf_url CLOB,
    contrato_pdf_nombre VARCHAR2(255 CHAR),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_proveedor_proyecto_proveedor
        FOREIGN KEY (proveedor_id)
        REFERENCES PROVEEDORES(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_proveedor_proyecto_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE
);

CREATE TABLE PROVEEDOR_SERVICIOS (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    proveedor_id RAW(16) NOT NULL,
    nombre VARCHAR2(200 CHAR) NOT NULL,
    descripcion CLOB,
    categoria VARCHAR2(100 CHAR),
    precio NUMBER(10,2),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT fk_proveedor_servicios_proveedor
        FOREIGN KEY (proveedor_id)
        REFERENCES PROVEEDORES(id)
        ON DELETE CASCADE
);

CREATE TABLE PROYECTO_AREAS (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    proyecto_id RAW(16) NOT NULL,
    area VARCHAR2(100 CHAR) NOT NULL,
    cargo VARCHAR2(100 CHAR) NOT NULL,
    cantidad NUMBER DEFAULT 1 NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT uq_proyecto_area_cargo UNIQUE (proyecto_id, area, cargo),
    CONSTRAINT fk_proyecto_areas_proyecto
        FOREIGN KEY (proyecto_id)
        REFERENCES PROYECTOS(id)
        ON DELETE CASCADE
);

-- ============================================================================
-- CREACIÓN DE ÍNDICES PARA MEJORAR RENDIMIENTO
-- ============================================================================
CREATE INDEX idx_personal_codigo ON personal(codigo);
CREATE INDEX idx_personal_dni ON personal(dni);
CREATE INDEX idx_personal_nombres ON personal(nombres);
CREATE INDEX idx_personal_apellidos ON personal(apellidos);

CREATE INDEX idx_datos_laborales_personal_id ON datos_laborales(personal_id);

CREATE INDEX idx_especialidades_personal_id ON especialidades(personal_id);

CREATE INDEX idx_grados_academicos_personal_id ON grados_academicos(personal_id);

CREATE INDEX idx_experiencia_laboral_personal_id ON experiencia_laboral(personal_id);

CREATE INDEX idx_proveedores_codigo ON proveedores(codigo);
CREATE INDEX idx_proveedores_ruc ON proveedores(ruc);
CREATE INDEX idx_proveedores_razon_social ON proveedores(razon_social);

CREATE INDEX idx_proyectos_codigo ON proyectos(codigo);
CREATE INDEX idx_proyectos_nombre ON proyectos(nombre);
CREATE INDEX idx_proyectos_estado ON proyectos(estado);
CREATE INDEX idx_proyectos_tipo ON proyectos(tipo);

CREATE INDEX idx_proyecto_areas_proyecto_id ON proyecto_areas(proyecto_id);
CREATE INDEX idx_proyecto_areas_area ON proyecto_areas(area);
CREATE INDEX idx_proyecto_areas_cargo ON proyecto_areas(cargo);

CREATE INDEX idx_personal_proyecto_personal_id ON personal_proyecto(personal_id);
CREATE INDEX idx_personal_proyecto_proyecto_id ON personal_proyecto(proyecto_id);
CREATE INDEX idx_personal_proyecto_activo ON personal_proyecto(activo);
CREATE INDEX idx_personal_proyecto_area ON personal_proyecto(area);
CREATE INDEX idx_personal_proyecto_area_rol ON personal_proyecto(area, rol);
CREATE INDEX idx_personal_proyecto_fechas ON personal_proyecto(fecha_inicio_proyecto, fecha_fin_proyecto);
CREATE INDEX idx_personal_proyecto_tipo_contrato ON personal_proyecto(tipo_contrato);

CREATE INDEX idx_proveedor_proyecto_proveedor_id ON proveedor_proyecto(proveedor_id);
CREATE INDEX idx_proveedor_proyecto_proyecto_id ON proveedor_proyecto(proyecto_id);
CREATE INDEX idx_proveedor_proyecto_fecha_inicio ON proveedor_proyecto(fecha_inicio);
CREATE INDEX idx_proveedor_proyecto_fecha_fin ON proveedor_proyecto(fecha_fin);
CREATE INDEX idx_proveedor_proyecto_monto ON proveedor_proyecto(monto_total_contratado);

CREATE INDEX idx_pagos_personal_personal_id ON pagos_personal(personal_id);
CREATE INDEX idx_pagos_personal_proyecto_id ON pagos_personal(proyecto_id);
CREATE INDEX idx_pagos_personal_fecha_hora ON pagos_personal(fecha_hora);
CREATE INDEX idx_pagos_personal_numero_operacion ON pagos_personal(numero_operacion);

CREATE INDEX idx_pagos_proveedor_proveedor_id ON pagos_proveedor(proveedor_id);
CREATE INDEX idx_pagos_proveedor_proyecto_id ON pagos_proveedor(proyecto_id);
CREATE INDEX idx_pagos_proveedor_fecha_hora ON pagos_proveedor(fecha_hora);
CREATE INDEX idx_pagos_proveedor_numero_operacion ON pagos_proveedor(numero_operacion);

CREATE INDEX idx_evaluaciones_desempeno_personal_id ON evaluaciones_desempeno(personal_id);
CREATE INDEX idx_evaluaciones_desempeno_proyecto_id ON evaluaciones_desempeno(proyecto_id);
CREATE INDEX idx_evaluaciones_desempeno_fecha ON evaluaciones_desempeno(fecha_evaluacion);

CREATE INDEX idx_personal_tareas_personal_id ON personal_tareas(personal_id);
CREATE INDEX idx_personal_tareas_proyecto_id ON personal_tareas(proyecto_id);
CREATE INDEX idx_personal_tareas_estado ON personal_tareas(estado);

CREATE INDEX idx_proveedor_actividades_proveedor_proyecto ON proveedor_actividades(proveedor_proyecto_id);
CREATE INDEX idx_proveedor_actividades_fecha ON proveedor_actividades(fecha_ejecucion);
CREATE INDEX idx_proveedor_actividades_estado ON proveedor_actividades(estado);

CREATE INDEX idx_notificaciones_fecha_evento ON notificaciones(fecha_evento);
CREATE INDEX idx_notificaciones_leida ON notificaciones(leida);
CREATE INDEX idx_notificaciones_tipo ON notificaciones(tipo);
CREATE INDEX idx_notificaciones_prioridad ON notificaciones(prioridad);
CREATE INDEX idx_notificaciones_personal_id ON notificaciones(personal_id);
CREATE INDEX idx_notificaciones_proveedor_id ON notificaciones(proveedor_id);
CREATE INDEX idx_notificaciones_proyecto_id ON notificaciones(proyecto_id);

-- ============================================================================
-- FUNCIONES Y PROCEDIMIENTOS
-- ============================================================================

CREATE OR REPLACE TRIGGER trg_personal_updated_at
BEFORE UPDATE ON PERSONAL
FOR EACH ROW
BEGIN
    :NEW.updated_at := CURRENT_TIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER trg_calculate_anos_experiencia
BEFORE INSERT OR UPDATE ON EXPERIENCIA_LABORAL  
FOR EACH ROW
BEGIN
    IF :NEW.fecha_inicio IS NOT NULL THEN
        IF :NEW.fecha_fin IS NOT NULL THEN
            :NEW.anos_experiencia := EXTRACT(YEAR FROM :NEW.fecha_fin) - EXTRACT(YEAR FROM :NEW.fecha_inicio);
        ELSE
            :NEW.anos_experiencia := EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM :NEW.fecha_inicio);
        END IF;
    END IF;
END;
/

CREATE OR REPLACE FUNCTION generate_personal_code
RETURN VARCHAR2
IS
    next_number NUMBER;
    new_code    VARCHAR2(20);
BEGIN
    -- Obtener el siguiente número secuencial
    SELECT NVL(MAX(TO_NUMBER(REGEXP_SUBSTR(codigo, '[0-9]+'))), 0) + 1
    INTO next_number
    FROM personal
    WHERE REGEXP_LIKE(codigo, '^EMP[0-9]+$');

    -- Generar el nuevo código con formato EMP###
    new_code := 'EMP' || LPAD(next_number, 3, '0');

    RETURN new_code;
END;
/

CREATE OR REPLACE FUNCTION generate_proveedor_code
RETURN VARCHAR2
IS
    next_number NUMBER;
    new_code    VARCHAR2(20);
BEGIN
    -- Obtener el siguiente número secuencial
    SELECT NVL(MAX(TO_NUMBER(REGEXP_SUBSTR(codigo, '[0-9]+'))), 0) + 1
    INTO next_number
    FROM proveedores
    WHERE REGEXP_LIKE(codigo, '^PROV[0-9]+$');

    -- Generar el nuevo código con formato PROV###
    new_code := 'PROV' || LPAD(next_number, 3, '0');

    RETURN new_code;
END;
/

CREATE OR REPLACE FUNCTION generate_proyecto_code
RETURN VARCHAR2
IS
    next_number NUMBER;
    new_code    VARCHAR2(20);
BEGIN
    -- Obtener el siguiente número secuencial
    SELECT NVL(MAX(TO_NUMBER(REGEXP_SUBSTR(codigo, '[0-9]+'))), 0) + 1
    INTO next_number
    FROM proyectos
    WHERE REGEXP_LIKE(codigo, '^CONST-[0-9]+$');

    -- Generar el nuevo código con formato CONST-###
    new_code := 'CONST-' || LPAD(next_number, 3, '0');

    RETURN new_code;
END;
/

CREATE OR REPLACE PROCEDURE update_tareas_atrasadas
IS
BEGIN
    UPDATE personal_tareas
    SET estado = 'Atrasada'
    WHERE fecha_entrega < TRUNC(SYSDATE)
      AND estado NOT IN ('Completada', 'Cancelada');
END;
/

CREATE OR REPLACE FUNCTION crear_notificacion (
    p_tipo            VARCHAR2,
    p_titulo          VARCHAR2,
    p_descripcion     CLOB,
    p_fecha_evento    TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP,
    p_prioridad       VARCHAR2 DEFAULT 'media',
    p_personal_id     RAW(16) DEFAULT NULL,
    p_proveedor_id    RAW(16) DEFAULT NULL,
    p_proyecto_id     RAW(16) DEFAULT NULL,
    p_datos_adicionales CLOB DEFAULT NULL
) RETURN RAW
IS
    notification_id RAW(16);
BEGIN
    INSERT INTO notificaciones (
        id,
        tipo,
        titulo,
        descripcion,
        fecha_evento,
        prioridad,
        personal_id,
        proveedor_id,
        proyecto_id,
        datos_adicionales
    ) VALUES (
        SYS_GUID(),          -- genera el UUID en Oracle
        p_tipo,
        p_titulo,
        p_descripcion,
        NVL(p_fecha_evento, SYSTIMESTAMP),
        NVL(p_prioridad, 'media'),
        p_personal_id,
        p_proveedor_id,
        p_proyecto_id,
        p_datos_adicionales
    )
    RETURNING id INTO notification_id;

    RETURN notification_id;
END;
/

CREATE OR REPLACE FUNCTION limpiar_notificaciones_antiguas (
    dias_antiguedad IN NUMBER DEFAULT 90
) RETURN NUMBER
IS
    registros_eliminados NUMBER;
BEGIN
    DELETE FROM notificaciones
    WHERE fecha_creacion < SYSTIMESTAMP - dias_antiguedad
      AND leida = 1; -- suponiendo que 1 = true

    registros_eliminados := SQL%ROWCOUNT;

    RETURN registros_eliminados;
END;
/

CREATE OR REPLACE TRIGGER tr_auditoria_personal
AFTER INSERT OR UPDATE OR DELETE ON personal
FOR EACH ROW
DECLARE
    v_usuario VARCHAR2(100) := USER; -- o variable de aplicación
BEGIN
    -- INSERT
    IF INSERTING THEN
        INSERT INTO auditoria(
            tabla,
            registro_id,
            campo,
            valor_anterior,
            valor_nuevo,
            operacion,
            usuario,
            fecha_hora
        ) VALUES ('PERSONAL', :NEW.id, NULL, NULL, NULL, 'INSERT', v_usuario, SYSTIMESTAMP);
    END IF;

    -- DELETE
    IF DELETING THEN
        INSERT INTO auditoria(
            tabla,
            registro_id,
            campo,
            valor_anterior,
            valor_nuevo,
            operacion,
            usuario,
            fecha_hora
        ) VALUES ('PERSONAL', :OLD.id, NULL, NULL, NULL, 'DELETE', v_usuario, SYSTIMESTAMP);
    END IF;

    -- UPDATE
    IF UPDATING THEN
        -- Ejemplo para campos específicos
        IF NVL(:OLD.nombres,'') != NVL(:NEW.nombres,'') THEN
            INSERT INTO auditoria(
                tabla,
                registro_id,
                campo,
                valor_anterior,
                valor_nuevo,
                operacion,
                usuario,
                fecha_hora
            ) VALUES ('PERSONAL', :NEW.id, 'nombres', :OLD.nombres, :NEW.nombres, 'UPDATE', v_usuario, SYSTIMESTAMP);
        END IF;

        IF NVL(:OLD.apellidos,'') != NVL(:NEW.apellidos,'') THEN
            INSERT INTO auditoria(
                tabla,
                registro_id,
                campo,
                valor_anterior,
                valor_nuevo,
                operacion,
                usuario,
                fecha_hora
            ) VALUES ('PERSONAL', :NEW.id, 'apellidos', :OLD.apellidos, :NEW.apellidos, 'UPDATE', v_usuario, SYSTIMESTAMP);
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_update_evaluaciones_desempeno_updated_at
BEFORE UPDATE ON evaluaciones_desempeno
FOR EACH ROW
BEGIN
    :NEW.updated_at := SYSTIMESTAMP;
END;
/

CREATE OR REPLACE TRIGGER trg_update_proveedor_actividades_updated_at
BEFORE UPDATE ON proveedor_actividades
FOR EACH ROW
BEGIN
    :NEW.updated_at := SYSTIMESTAMP;
END;
/

-- ============================================================================
-- COMENTARIOS EN LAS TABLAS
-- ============================================================================
COMMENT ON TABLE personal IS 'Información básica del personal de la empresa';
COMMENT ON TABLE datos_laborales IS 'Información laboral general del personal (no específica de proyectos)';
COMMENT ON TABLE especialidades IS 'Especialidades y certificaciones del personal';
COMMENT ON TABLE grados_academicos IS 'Grados académicos del personal';
COMMENT ON TABLE experiencia_laboral IS 'Experiencia laboral previa del personal';
COMMENT ON TABLE proveedores IS 'Información de proveedores y contratistas';
COMMENT ON TABLE proveedor_servicios IS 'Servicios ofrecidos por los proveedores';
COMMENT ON TABLE proveedor_documentos IS 'Documentos de los proveedores';
COMMENT ON TABLE proyectos IS 'Información de proyectos de construcción';
COMMENT ON TABLE proyecto_areas IS 'Áreas y cargos disponibles en cada proyecto';
COMMENT ON TABLE personal_proyecto IS 'Asignación de personal a proyectos específicos con roles';
COMMENT ON TABLE proveedor_proyecto IS 'Asignación de proveedores a proyectos';
COMMENT ON TABLE pagos_personal IS 'Historial de pagos realizados al personal';
COMMENT ON TABLE pagos_proveedor IS 'Historial de pagos realizados a proveedores';
COMMENT ON TABLE evaluaciones_desempeno IS 'Evaluaciones de desempeño del personal por proyecto';
COMMENT ON TABLE personal_tareas IS 'Tareas asignadas al personal en proyectos específicos';
COMMENT ON TABLE proveedor_actividades IS 'Registro de actividades y hitos de avance de proveedores en proyectos';
COMMENT ON TABLE auditoria IS 'Tabla de auditoría que registra todos los cambios realizados en el sistema';
COMMENT ON TABLE notificaciones IS 'Sistema de notificaciones del sistema';

-- Comentarios específicos en columnas importantes
COMMENT ON COLUMN personal_proyecto.rol IS 'Cargo específico que desempeña en este proyecto';
COMMENT ON COLUMN personal_proyecto.area IS 'Área específica del proyecto donde trabaja el personal';
COMMENT ON COLUMN personal_proyecto.activo IS 'Indica si la asignación está activa';
COMMENT ON COLUMN personal_proyecto.horas_laborales_semana IS 'Horas de trabajo por semana en este proyecto específico';
COMMENT ON COLUMN personal_proyecto.salario_mensual IS 'Salario mensual en soles para este proyecto';
COMMENT ON COLUMN personal_proyecto.tipo_contrato IS 'Tipo de contrato: indefinido, temporal, por-obra, practicas, consultoria';
COMMENT ON COLUMN personal_proyecto.fecha_inicio_proyecto IS 'Fecha de inicio del personal en este proyecto específico';
COMMENT ON COLUMN personal_proyecto.fecha_fin_proyecto IS 'Fecha de fin del personal en este proyecto (opcional)';
COMMENT ON COLUMN personal_proyecto.contrato_proyecto_url IS 'URL del archivo PDF del contrato específico del proyecto';
COMMENT ON COLUMN personal_proyecto.contrato_proyecto_nombre IS 'Nombre del archivo del contrato del proyecto';

COMMENT ON COLUMN proyecto_areas.cantidad IS 'Número de personas necesarias para este cargo en el área';

COMMENT ON COLUMN pagos_personal.numero_operacion IS 'Número de operación bancaria o referencia del pago';
COMMENT ON COLUMN pagos_personal.cantidad IS 'Monto del pago en soles';
COMMENT ON COLUMN pagos_personal.boleta_url IS 'URL del archivo PDF de la boleta';
COMMENT ON COLUMN pagos_personal.concepto IS 'Concepto o motivo del pago';
COMMENT ON COLUMN pagos_personal.metodo_pago IS 'Método utilizado para el pago';
COMMENT ON COLUMN pagos_personal.proyecto_id IS 'ID del proyecto asociado al pago del personal';
COMMENT ON COLUMN pagos_personal.boleta_pdf_url IS 'URL del archivo PDF de la boleta de pago';
COMMENT ON COLUMN pagos_personal.boleta_pdf_nombre IS 'Nombre original del archivo PDF de la boleta';

COMMENT ON COLUMN pagos_proveedor.numero_operacion IS 'Número de operación bancaria o referencia del pago';
COMMENT ON COLUMN pagos_proveedor.cantidad IS 'Monto del pago en soles';
COMMENT ON COLUMN pagos_proveedor.boleta_url IS 'URL del archivo PDF de la boleta';
COMMENT ON COLUMN pagos_proveedor.concepto IS 'Concepto o motivo del pago';
COMMENT ON COLUMN pagos_proveedor.metodo_pago IS 'Método utilizado para el pago';
COMMENT ON COLUMN pagos_proveedor.proyecto_id IS 'ID del proyecto asociado al pago del proveedor';
COMMENT ON COLUMN pagos_proveedor.boleta_pdf_url IS 'URL del archivo PDF de la boleta de pago';
COMMENT ON COLUMN pagos_proveedor.boleta_pdf_nombre IS 'Nombre original del archivo PDF de la boleta';

COMMENT ON COLUMN proveedor_proyecto.servicio_descripcion IS 'Descripción detallada del servicio contratado';
COMMENT ON COLUMN proveedor_proyecto.fecha_inicio IS 'Fecha de inicio del contrato del proveedor en el proyecto';
COMMENT ON COLUMN proveedor_proyecto.fecha_fin IS 'Fecha de fin del contrato del proveedor en el proyecto';
COMMENT ON COLUMN proveedor_proyecto.monto_total_contratado IS 'Monto total contratado para el servicio';
COMMENT ON COLUMN proveedor_proyecto.contrato_pdf_url IS 'URL del archivo PDF del contrato';
COMMENT ON COLUMN proveedor_proyecto.contrato_pdf_nombre IS 'Nombre del archivo PDF del contrato';

COMMENT ON COLUMN evaluaciones_desempeno.calidad_trabajo IS 'Calificación de 1-5 para calidad del trabajo';
COMMENT ON COLUMN evaluaciones_desempeno.habilidades_comunicacion IS 'Calificación de 1-5 para habilidades de comunicación';
COMMENT ON COLUMN evaluaciones_desempeno.personalidad IS 'Calificación de 1-5 para personalidad';
COMMENT ON COLUMN evaluaciones_desempeno.conocimiento_trabajo IS 'Calificación de 1-5 para conocimiento del trabajo';
COMMENT ON COLUMN evaluaciones_desempeno.confiabilidad IS 'Calificación de 1-5 para confiabilidad';
COMMENT ON COLUMN evaluaciones_desempeno.productividad IS 'Calificación de 1-5 para productividad';
COMMENT ON COLUMN evaluaciones_desempeno.cumplimiento_metas IS 'Calificación de 1-5 para cumplimiento de metas';
COMMENT ON COLUMN evaluaciones_desempeno.contribucion_equipo IS 'Calificación de 1-5 para contribución al equipo';
COMMENT ON COLUMN evaluaciones_desempeno.puntualidad IS 'Calificación de 1-5 para puntualidad';

COMMENT ON COLUMN personal_tareas.estado IS 'Estados: Pendiente, En Progreso, Completada, Cancelada, Atrasada';

COMMENT ON COLUMN proveedor_actividades.proveedor_proyecto_id IS 'Referencia al proveedor en el proyecto';
COMMENT ON COLUMN proveedor_actividades.descripcion IS 'Descripción detallada de la actividad realizada';
COMMENT ON COLUMN proveedor_actividades.fecha_ejecucion IS 'Fecha en que se ejecutó o programó la actividad';
COMMENT ON COLUMN proveedor_actividades.estado IS 'Estado actual de la actividad';
COMMENT ON COLUMN proveedor_actividades.observaciones IS 'Observaciones adicionales sobre la actividad';
COMMENT ON COLUMN proveedor_actividades.documento_pdf_url IS 'URL del documento PDF relacionado';
COMMENT ON COLUMN proveedor_actividades.documento_pdf_nombre IS 'Nombre del archivo PDF';

COMMENT ON COLUMN auditoria.fecha_hora IS 'Fecha y hora exacta del cambio';
COMMENT ON COLUMN auditoria.usuario IS 'Usuario que realizó el cambio';
COMMENT ON COLUMN auditoria.tabla IS 'Nombre de la tabla modificada';
COMMENT ON COLUMN auditoria.registro_id IS 'ID del registro modificado';
COMMENT ON COLUMN auditoria.campo IS 'Campo específico que fue modificado';
COMMENT ON COLUMN auditoria.valor_anterior IS 'Valor antes del cambio';
COMMENT ON COLUMN auditoria.valor_nuevo IS 'Valor después del cambio';
COMMENT ON COLUMN auditoria.operacion IS 'Tipo de operación: INSERT, UPDATE, DELETE';

-- Comentarios en funciones
COMMENT ON FUNCTION audit_trigger_function() IS 'Función genérica para registrar cambios en auditoría';
COMMENT ON FUNCTION limpiar_notificaciones_antiguas(INTEGER) IS 'Elimina notificaciones leídas más antiguas que el número de días especificado';
