'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ClientSchema = Schema({
    nombres: String,
    apellidos: String,
    direccion: String,
    telefono: Number,
    email: String,
    dui: Number,
    nit: Number
});



module.exports = mongoose.model('Client', ClientSchema);
