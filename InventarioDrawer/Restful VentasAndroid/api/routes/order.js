'use strict'

var express = require('express');
var OrderController = require('../controllers/order');

var api = express.Router();

//GET
api.get('/test', OrderController.test);
api.get('/obtenerOrdenID/:id', OrderController.obtenerOrdenID)
api.get('/listarOrdenes', OrderController.listarOrdenes)

//POST
api.post('/registerOrder', OrderController.saveOrder);

module.exports = api;
