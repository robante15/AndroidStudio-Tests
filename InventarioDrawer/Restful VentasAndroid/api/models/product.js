'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ProductSchema = Schema({
    barcode: Number,
    nombre: String,
    descripcion: String,
    precio: Number,
    stock: Number,
    margenUtilidad: Number,
    imagen: String
});



module.exports = mongoose.model('Product', ProductSchema);
