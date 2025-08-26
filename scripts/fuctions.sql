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
    p_tipo            IN VARCHAR2,
    p_titulo          IN VARCHAR2,
    p_descripcion     IN CLOB,
    p_fecha_evento    IN TIMESTAMP WITH TIME ZONE,
    p_prioridad       IN VARCHAR2,
    p_personal_id     IN RAW,
    p_proveedor_id    IN RAW,
    p_proyecto_id     IN RAW,
    p_datos_adicionales IN CLOB
) RETURN RAW
IS
    notification_id RAW(16);
BEGIN
    INSERT INTO NOTIFICACIONES (
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
        SYS_GUID(),
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