'use strict'

var jwt = require('jwt-simple');
var moment = require('moment');

var secret = 'efnD24fUtb56jkWB';

exports.ensureAuth = function (req, res, next) {
    if (!req.headers.authorization) {
        return res.status(403).send({message: 'La petición no tiene la cabezera de autenticación'});
    }

    var token = req.headers.authorization.replace(/['"]+/g, '');

    try {
        var payload = jwt.decode(token, secret);
        if (payload.exp <= moment.unix()) {
            return res.status(401).send({message: 'Error: El token ya ha expirado'});
        }
    } catch (ex) {
        return res.status(404).send({message: 'Error: El token no es valido'});
    }

    req.admin = payload;

    next();
};
