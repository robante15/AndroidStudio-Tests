'use strict'

var express = require('express');
var ProductController = require('../controllers/product');

var api = express.Router();

//GET
api.get('/test', ProductController.test);
api.get('/obtenerProductoID/:id', ProductController.obtenerProductoID)
api.get('/obtenerProductoBCode/:BCode', ProductController.obtenerProductoBCode)
api.get('/listarProductos', ProductController.listarProductos)

//POST
api.post('/registerProduct', ProductController.saveProduct);
api.post('/buscarProducto', ProductController.buscarProducto)



module.exports = api;
