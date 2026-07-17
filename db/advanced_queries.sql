-- Alertas de FC fuera de rango (últimas 24 horas) 
SELECT * FROM lecturas_fc
WHERE (bpm < 60 OR bpm > 100)
AND created_at > NOW() - INTERVAL '24 hours' 
ORDER BY created_at DESC;

-- Promedio de FC por hora del día
SELECT EXTRACT(HOUR FROM created_at) AS hora_dia, ROUND(AVG(bpm)) AS promedio_bpm,
COUNT(*) AS total_lecturas FROM lecturas_fc
GROUP BY hora_dia 
ORDER BY hora_dia;

-- Lectura más reciente de cada dispositivo 
SELECT DISTINCT ON (dispositivo)
dispositivo, bpm, estado, hora, created_at FROM lecturas_fc
ORDER BY dispositivo, created_at DESC;

-- Detección de taquicardia sostenida (>100 bpm por 3+ lecturas seguidas) 
SELECT COUNT(*) AS lecturas_altas,
MIN(hora) AS desde, MAX(hora) AS hasta FROM lecturas_fc
WHERE bpm > 100
AND created_at > NOW() - INTERVAL '1 hour';
