const db = require('./db');

async function getUsuarioByCredenciais(username, senha) {
    const result = await db.query(
        'SELECT * FROM usuarios WHERE username = $1 AND senha = $2',
        [username, senha]
    );
    return result.rows[0];
}

module.exports = { 
    getUsuarioByCredenciais 
};