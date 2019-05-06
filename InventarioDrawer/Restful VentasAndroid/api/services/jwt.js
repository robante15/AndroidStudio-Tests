'use strict'

var jwt = require('jwt-simple');
var moment = require('moment');
var secret = 'efnD24fUtb56jkWB';

exports.createToken = function (admin) {
  var payload = {
      sub: admin._id,
      nombres: admin.nombres,
      apellidos: admin.apellidos,
      usuario: admin.usuario,
      email: admin.email,
      institucion: admin.institucion,
      rol: admin.rol,
      imagen: admin.imagen,
      iat: moment().unix(),
      exp: moment().add(30,'days').unix
  };
  
  return jwt.encode(payload, secret);
  
};