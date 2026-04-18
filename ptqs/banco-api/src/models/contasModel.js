const db = require('./db');

async function getContas() {
    const result = await db.query('SELECT id, titular, saldo, ativa FROM contas ORDER BY titular ASC');
    return result.rows;
}

async function getContaById(id) {
    const result = await db.query('SELECT id, titular, saldo, ativa FROM contas WHERE id = $1', [id]);
    return result.rows[0];
}

async function atualizarSaldo(id, valor) {
    await db.query('UPDATE contas SET saldo = saldo + $1 WHERE id = $2', [valor, id]);
}

module.exports = { 
    getContas,
    getContaById, 
    atualizarSaldo 
};