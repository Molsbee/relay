'use strict';

const rest = require('rest');
const mime = require('rest/interceptor/mime');
const errorCode = require('rest/interceptor/errorCode');
const baseRegistry = require('rest/mime/registry');

const registry = baseRegistry.child();

module.exports = rest
    .wrap(mime, {registry: registry})
    .wrap(errorCode);