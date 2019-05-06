'use strict'

var bcrypt = require('bcrypt-nodejs');
var jwt = require('../services/jwt');

var Product = require('../models/product');

function test(req, res) {
    res.status(200).send({
        message: 'Prueba de la API conectando al controlador de ProductAndroid'
    });
}

function saveProduct(req, res) {
    var params = req.body;
    var product = new Product();

    if (params.barcode && params.nombre && params.precio && params.stock) {
        product.barcode = params.barcode;
        product.nombre = params.nombre;
        product.descripcion = params.descripcion;
        product.precio = params.precio;
        product.stock = params.stock;
        product.margenUtilidad = params.margenUtilidad;
        product.imagen = null;
    } else {
        res.status(200).send({
            message: 'Error: Campos incompletos'
        });
    }

    //Controlar entradas duplicadas
    Product.find({
        $or: [
            { barcode: product.barcode }
        ]
    }).exec((err, institutions) => {
        if (err) return res.status(500).send({ message: 'Error: En la petición de productos', Error: err });

        if (institutions && institutions.length >= 1) {
            return res.status(200).send({ message: 'Error: El producto ya se encuentra registrado', Error: err });

        } else {
            product.save((err, productStored) => {
                if (err) return res.status(500).send({ message: 'Error al guardar el producto', Error: err });
                if (productStored) {
                    res.status(200).send({ product: productStored });
                } else {
                    res.status(404).send({ message: 'No se ha registrado el producto' })
                }
            });
        }
    });
}

function obtenerProductoID(req, res) {
    var productoID = req.params.id;

    Product.findById(productoID, (err, product) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!product) return res.status(404).send({ message: 'Error, el usuario no existe' });

        return res.status(200).send({ product });
    });
}

function obtenerProductoBCode(req, res) {
    var productoBCode = req.params.BCode;

    Product.find({barcode: productoBCode}).exec((err, product) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!product) return res.status(404).send({ message: 'Error, el usuario no existe' });

        return res.status(200).send({ product });
    });
}

function listarProductos(req, res) {
    Product.find().exec((err, product) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!product) return res.status(404).send({ message: 'Error, el usuario no existe' });

        return res.status(200).send({ product });
    });
}

function buscarProducto(req, res) {
    var params = req.body;
    var search;

    if (params.search) {
        search = params.search;
    }else{
        return res.status(404).send({ message: 'Error: Ingrese un parametro de busqueda' });
    }
    
    Product.find({
        "$or": [{
            nombre: {
                //$regex: search
                $regex: new RegExp("^" + search.toLowerCase(), "i")
            }
        }, {
            descripcion: {
                $regex: new RegExp("^" + search.toLowerCase(), "i")
            }
        }]
    }).exec((err, product) => {
        if (err) return res.status(500).send({ message: 'Error en la petición', Error: err });

        if (!product) return res.status(404).send({ message: 'Error: No se encuentra el producto' });

        return res.status(200).send({ product });
    });
}

module.exports = {
    test,
    saveProduct,
    obtenerProductoID,
    obtenerProductoBCode,
    listarProductos,
    buscarProducto
}
