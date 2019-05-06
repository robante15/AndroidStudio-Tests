'use strict'

var express = require('express');
var ClientController = require('../controllers/client');

var api = express.Router();

//GET
api.get('/test', ClientController.test);
api.get('/obtenerClienteID/:id', ClientController.obtenerClienteID)
api.get('/obtenerClienteDUI/:DUI', ClientController.obtenerClienteDUI)
api.get('/listarClientes', ClientController.listarClientes)

//POST
api.post('/registerClient', ClientController.saveClient);
api.post('/buscarCliente', ClientController.buscarCliente)

module.exports = api;
