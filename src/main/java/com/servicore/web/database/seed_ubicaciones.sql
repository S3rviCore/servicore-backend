INSERT INTO countries (name, created_at, updated_at)
VALUES ('Nicaragua', NOW(), NOW());

INSERT INTO departaments (name, country_id, created_at)
SELECT d.name, c.id, NOW()
FROM countries c
JOIN (
    SELECT 'Boaco' AS name UNION ALL SELECT 'Carazo' UNION ALL SELECT 'Chinandega'
    UNION ALL SELECT 'Chontales' UNION ALL SELECT 'Estelí' UNION ALL SELECT 'Granada'
    UNION ALL SELECT 'Jinotega' UNION ALL SELECT 'León' UNION ALL SELECT 'Madriz'
    UNION ALL SELECT 'Managua' UNION ALL SELECT 'Masaya' UNION ALL SELECT 'Matagalpa'
    UNION ALL SELECT 'Nueva Segovia' UNION ALL SELECT 'Río San Juan' UNION ALL SELECT 'Rivas'
    UNION ALL SELECT 'Costa Caribe Norte' UNION ALL SELECT 'Costa Caribe Sur'
) d
WHERE c.name = 'Nicaragua';

INSERT INTO municipalities (name, departament_id, created_at)
SELECT m.name, d.id, NOW()
FROM departaments d
JOIN (
    SELECT 'León' AS dep, 'León' AS name
    UNION ALL SELECT 'León', 'Achuapa'
    UNION ALL SELECT 'León', 'El Jicaral'
    UNION ALL SELECT 'León', 'El Sauce'
    UNION ALL SELECT 'León', 'La Paz Centro'
    UNION ALL SELECT 'León', 'Larreynaga'
    UNION ALL SELECT 'León', 'Nagarote'
    UNION ALL SELECT 'León', 'Quezalguaque'
    UNION ALL SELECT 'León', 'Santa Rosa del Peñón'
    UNION ALL SELECT 'León', 'Telica'
    UNION ALL SELECT 'Managua', 'Managua'
    UNION ALL SELECT 'Managua', 'Ciudad Sandino'
    UNION ALL SELECT 'Managua', 'El Crucero'
    UNION ALL SELECT 'Managua', 'Mateare'
    UNION ALL SELECT 'Managua', 'San Francisco Libre'
    UNION ALL SELECT 'Managua', 'San Rafael del Sur'
    UNION ALL SELECT 'Managua', 'Ticuantepe'
    UNION ALL SELECT 'Managua', 'Tipitapa'
    UNION ALL SELECT 'Managua', 'Villa El Carmen'
) m ON m.dep = d.name;