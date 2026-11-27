<?php
// GET ver.php?id=3 → un plato en JSON
require_once __DIR__ . '/datos/ConexionBD.php';

header('Content-Type: application/json; charset=utf-8');

// El id tiene que ser un número entero; si no, error 400
$id = filter_var($_GET['id'] ?? '', FILTER_VALIDATE_INT);
if ($id === false) {
    http_response_code(400);
    echo json_encode(['error' => ERROR_FALTA_ID], JSON_UNESCAPED_UNICODE);
    exit;
}

try {
    $bd = new ConexionBD();

    // Consulta preparada: el id nunca se pega dentro del SQL
    $consulta = $bd->getConexion()->prepare('SELECT id, receta, tipo, imagen FROM platos WHERE id = ?');
    $consulta->execute([$id]);
    $fila = $consulta->fetch();

    // Si no hay fila, el plato no existe: error 404
    if ($fila === false) {
        http_response_code(404);
        echo json_encode(['error' => ERROR_NO_EXISTE], JSON_UNESCAPED_UNICODE);
    } else {
        echo json_encode($fila, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT);
    }
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => ERROR_CONEXION], JSON_UNESCAPED_UNICODE);
}