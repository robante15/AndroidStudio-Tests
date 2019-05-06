'use strict'

var mongoose = require('mongoose');
var app = require('./app');
var port = 3800; //Puerto en el que va a cargar el API del sistema

//Conexion con la BD
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/APIAndroid', { useNewUrlParser: true }).then(() => {
    //Crear servidor
    app.listen(port, () => {
        console.log("El servidor del API creado correctamente en http://localhost:3800");
    });
    console.log("La conexión a la BD se ha realizado correctamente");
}).catch(err => console.log(err));