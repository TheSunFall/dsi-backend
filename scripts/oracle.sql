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




