'use strict'

var moment = require('moment');

var Order = require('../models/order');


function test(req, res) {
    res.status(200).send({
        message: 'Prueba de la API conectando al controlador de OrderAndroid'
    });
}

function saveOrder(req, res) {
    var params = req.body;
    var order = new Order();

    if (!params.cliente) return res.status(404).send({
        message: 'Error: Debes de especificar un cliente'
    });

    if (!params.productos) return res.status(404).send({
        message: 'Error: Debes especificar un producto'
    });

    order.cliente = params.cliente;
    order.fecha = moment().unix();
    order.productos = params.productos;
    order.cantidades = params.cantidades;
    order.total = params.total;

    order.save((err, orderStored) => {
        if (err) return res.status(500).send({
            message: 'Error: no se ha podido guardar la orden',
            Error: err
        })

        if (!orderStored) return res.status(404).send({
            message: 'Error: La orden no ha sido guardado'
        });

        return res.status(200).send({
            order: orderStored
        });
    });

}

function obtenerOrdenID(req, res) {
    var orderID = req.params.id;

    Order.findById(orderID).populate('productos cliente', 'barcode nombre precio nombres apellidos direccion telefono dui').exec((err, order) => {
        if (err) return res.status(500).send({ message: 'Error: en la petición', Error: err });

        if (!order) return res.status(404).send({ message: 'Error: La orden no existe' });

        return res.status(200).send({ order });
    });
}

function listarOrdenes(req, res) {
    Order.find().populate('productos cliente').exec((err, orders) => {
        if (err) return res.status(500).send({ message: 'Error: en la petición', Error: err });

        if (!orders) return res.status(404).send({ message: 'Error: no hay listado de ordenes' });

        return res.status(200).send({ orders });
    });
}

module.exports = {
    test,
    saveOrder,
    obtenerOrdenID,
    listarOrdenes
}
