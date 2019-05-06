'use strict'

var bcrypt = require('bcrypt-nodejs');
var jwt = require('../services/jwt');

var Client = require('../models/client');

function test(req, res) {
    res.status(200).send({
        message: 'Prueba de la API conectando al controlador de ClientAndroid'
    });
}

function saveClient(req, res) {
    var params = req.body;
    var client = new Client();
        
    if (params.nombres && params.apellidos && params.telefono && params.dui) {
        client.nombres = params.nombres;
        client.apellidos = params.apellidos;
        client.direccion = params.direccion;
        client.telefono = params.telefono;
        client.email = params.email;
        client.dui = params.dui;
        client.nit = params.nit;
    } else {
        res.status(200).send({
            message: 'Error: Campos incompletos'
        });
    }

    //Controlar entradas duplicadas
    Client.find({
        $or: [
            { barcode: client.dui }
        ]
    }).exec((err, clients) => {
        if (err) return res.status(500).send({ message: 'Error: En la petición de clientes', Error: err });

        if (clients && clients.length >= 1) {
            return res.status(200).send({ message: 'Error: El cliente ya se encuentra registrado', Error: err });
        } else {
            client.save((err, clientStored) => {
                if (err) return res.status(500).send({ message: 'Error al guardar el client', Error: err });
                if (clientStored) {
                    res.status(200).send({ product: clientStored });
                } else {
                    res.status(404).send({ message: 'No se ha registrado el cliente' })
                }
            });
        }
    });
}

function obtenerClienteID(req, res) {
    var clienteID = req.params.id;

    Client.findById(clienteID, (err, client) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!client) return res.status(404).send({ message: 'Error, el cliente no existe' });

        return res.status(200).send({ client });
    });
}

function obtenerClienteDUI(req, res) {
    var clienteDUI = req.params.DUI;

    Client.find({dui: clienteDUI}).exec((err, cliente) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!cliente) return res.status(404).send({ message: 'Error: el cliente no existe' });

        return res.status(200).send({ cliente });
    });
}

function listarClientes(req, res) {
    Client.find().exec((err, clientes) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!clientes) return res.status(404).send({ message: 'Error: no hay listado de clientes' });

        return res.status(200).send({ clientes });
    });
}

function buscarCliente(req, res) {
    var params = req.body;
    var search;

    if (params.search) {
        search = params.search;
    }else{
        return res.status(404).send({ message: 'Error: Ingrese un parametro de busqueda' });
    }

    Client.find({
        "$or": [{
            nombres: {
                //$regex: search
                $regex: new RegExp("^" + search.toLowerCase(), "i")
            }
        }, {
            apellidos: {
                $regex: new RegExp("^" + search.toLowerCase(), "i")
            }
        }]
    }).exec((err, client) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!client) return res.status(404).send({ message: 'Error: No se encuentra el cliente' });

        return res.status(200).send({ client });
    });
}

module.exports = {
    test,
    saveClient,
    obtenerClienteID,
    obtenerClienteDUI,
    listarClientes,
    buscarCliente
    
}
