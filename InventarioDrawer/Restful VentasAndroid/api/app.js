'use strict'

var express = require('express');
var bodyParser = require('body-parser');

var app = express();

//Cargar rutas
var client_routes = require('./routes/client');
var order_routes = require('./routes/order');
var product_routes = require('./routes/product');


//Cargar Middlewares
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

//Cors

//Rutas
app.use('/client', client_routes);
app.use('/order', order_routes);
app.use('/product', product_routes);

//Exportar
module.exports = app;
