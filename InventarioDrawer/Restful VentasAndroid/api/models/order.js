'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var OrderSchema = Schema({
    cliente: { type: Schema.ObjectId, ref: 'Client' },
    fecha: Number,
    productos:[{ type: Schema.ObjectId, ref: 'Product' }],
    cantidades: [Number],
    total: Number
});

module.exports = mongoose.model('Order', OrderSchema);
